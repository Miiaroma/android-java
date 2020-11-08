package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class WebApi extends AppCompatActivity {
    String url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=20&resultsFrom=0&name=+editText+&companyRegistrationFrom=2014-02-28";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapi);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        // get data via the key
        String editText = extras.getString(Intent.EXTRA_TEXT);
        if (editText!= null) {
            // do something with the data
        }

        //requestQueue = Volley.newRequestQueue(context)
    }


    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    textView.setText("Response: " + response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error

                }
            });

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            // the HTTP method to use
            url,
            // url
            null,
            // null indicates, that no parameters will be posted along with request
            new Response.Listener<JSONArray>() {
                // listener to receive the Json response
                @Override
                public void onResponse(JSONArray response) {
                }
            },
            new Response.ErrorListener() { // error listener
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

}