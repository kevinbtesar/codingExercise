package com.kevintesar.controller;

import android.app.ProgressDialog;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kevintesar.R;
import com.kevintesar.SongAdapter;
import com.kevintesar.api.JSONParser;
import com.kevintesar.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Song> songArrayList;
    private ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(() -> {
            DownloadTask dlTask = new DownloadTask(MainActivity.this);
            dlTask.execute();
            Toast.makeText(MainActivity.this,"Song List Refreshed.", Toast.LENGTH_SHORT).show();
        });

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
            DownloadTask dlTask = new DownloadTask(MainActivity.this);
            dlTask.execute();
        });
    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Songs...");
        pd.setCancelable(false);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        editText = findViewById(R.id.artistName);

    }


    static public class DownloadTask extends AsyncTask<Void, Void, Boolean> {

        private final WeakReference<MainActivity> activityReference;

        DownloadTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            activityReference.get().runOnUiThread(() -> {

                // Show the progress spinner
                activityReference.get().pd.show();

                // Hide the keyboard
                InputMethodManager mgr = (InputMethodManager) activityReference.get().getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(mgr).hideSoftInputFromWindow(activityReference.get().editText.getWindowToken(), 0);
            });


            // Get the current text input
            String searchString = activityReference.get().editText.getText().toString();

            JSONParser jParser = new JSONParser();
            JSONObject jObject;

            try {
                jObject = jParser.parseJSON("https://itunes.apple.com/search?term=" + searchString);
                if(jObject != null) {

                    JSONArray settingsArray = jObject.getJSONArray("results");
                    activityReference.get().songArrayList = new ArrayList<>();

                    for (int i = 0; i < settingsArray.length(); i++)
                    {
                        JSONObject dataObject = settingsArray.getJSONObject(i);

                        Song song = new Song();
                        song.setArtistName(dataObject.optString("artistName", "Artist Name"));
                        song.setTrackName(dataObject.optString("trackName", "Track Name"));
                        song.setReleaseDate(dataObject.optString("releaseDate", "Release Date"));
                        song.setPrimaryGenreName(dataObject.optString("primaryGenreName", "Primary Genre Name"));
                        song.setTrackPrice(dataObject.optString("trackPrice", "Track Price"));
                        song.setImageUrl(dataObject.optString("artworkUrl60"));

                        activityReference.get().songArrayList.add(song);
                    }

                    return true;
                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if(success) {

                activityReference.get().recyclerView.setAdapter(new SongAdapter(activityReference.get().songArrayList));
                activityReference.get().recyclerView.smoothScrollToPosition(0);
                activityReference.get().swipeContainer.setRefreshing(false);
                activityReference.get().pd.hide();

            } else {

                AlertDialog alertDialog = new AlertDialog.Builder(activityReference.get())
                        .setTitle("Error")
                        .setMessage("There was an error downloading app data. If problem persists, please check your internet connection. Retry?")
                        .setPositiveButton("Retry", (dialogInterface, i) -> {
                            DownloadTask dlTask = new DownloadTask(activityReference.get());
                            dlTask.execute();
                        })
                        .setNegativeButton("Cancel", (dialogInterface, i) -> activityReference.get().finish()).create();

                activityReference.get().runOnUiThread(alertDialog::show);
            }

        }
    }
}
