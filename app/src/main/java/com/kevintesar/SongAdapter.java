package com.kevintesar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.kevintesar.model.Song;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private final List<Song> Song;

    public SongAdapter(List<Song> SongArrayList) {
        this.Song=SongArrayList;
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_song, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.artistName.setText(Song.get(i).getArtistName());
        viewHolder.trackName.setText(Song.get(i).getTrackName());
        viewHolder.releaseDate.setText(Song.get(i).getReleaseDate());
        viewHolder.primaryGenreName.setText(Song.get(i).getPrimaryGenreName());
        viewHolder.trackPrice.setText(Song.get(i).getTrackPrice());


        // This is how we use Picasso to load images from the internet.
       Picasso.get()
            .load(Song.get(i).getImageUrl())
            .placeholder(R.drawable.load)
            .into(viewHolder.imageView);

    }
    @Override
    public int getItemCount() {
        return Song.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView artistName, trackName, releaseDate, primaryGenreName, trackPrice;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            artistName = view.findViewById(R.id.artistName);
            trackName = view.findViewById(R.id.trackName);
            releaseDate = view.findViewById(R.id.releaseDate);
            primaryGenreName = view.findViewById(R.id.primaryGenreName);
            trackPrice = view.findViewById(R.id.trackPrice);
            imageView = view.findViewById(R.id.image);

        }
    }

}