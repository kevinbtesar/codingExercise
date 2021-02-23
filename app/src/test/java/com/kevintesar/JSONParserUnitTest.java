package com.kevintesar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;

import com.kevintesar.api.JSONParser;
import com.kevintesar.controller.MainActivity;
import com.kevintesar.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.app.AlertDialog;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JSONParserUnitTest {

    JSONParser SUT; // System Under Test

    @Before
    public void setup() {
        SUT = new JSONParser();

    }


    @Test
    public void test1() throws JSONException {
        JSONObject jObject = null;

        jObject = SUT.parseJSON("https://itunes.apple.com/search?term=phish");

        assertThat(jObject.get("key"), is("value"));

        Map<String, Object> expectedData = Collections.singletonMap("key", "value");
        assertThat(jObject, is(expectedData));

    }



}
