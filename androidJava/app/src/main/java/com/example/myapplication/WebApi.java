package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebApi extends AppCompatActivity {
    String url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=20&resultsFrom=0&companyRegistrationFrom=2014-02-28&name=editText";
    private static final String TAG = "WebApi activity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RequestQueue requestQueue;
    ArrayList<Item>myDataset;

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
        retrieveJSON();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void retrieveJSON(){
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray myArray = response.getJSONArray("results");
                            for (int i = 0; i < myArray.length(); i++) {
                                JSONObject currentObj = myArray.getJSONObject(i);
                                String name = currentObj.getString("name");
                                /*String businessId = currentObj.getString("businessId");
                                String registrationDate = currentObj.getString("businessId");
                                String companyForm = currentObj.getString("companyForm");*/

                                Item item = new Item(name);
                                item.setName(name);
                                myDataset.add(item);
                                Log.e(TAG, "name");
                            }

                        } catch (JSONException e) {
                            Log.e(TAG, e.getMessage());
                        }
                        mAdapter = new RecyclerAdapter(myDataset);
                        recyclerView.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
    }
}