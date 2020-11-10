package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class WebApi extends AppCompatActivity {
    String url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=20&resultsFrom=0&companyRegistrationFrom=2014-02-28&name=editText";
    private static final String TAG = "WebApi activity";

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
        RequestQueue requestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        //String url ="http://www.example.com";

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
        // ... requestQueue.add(Request<T>request);

    }

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    //textView.setText("Response: " + response.toString());
                    Log.e("response", response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error

                }
            });

    /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
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
            });*/
}