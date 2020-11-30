package com.example.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    String url = "https://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=10&resultsFrom=0&companyRegistrationFrom=2014-02-28&name=";
    private static final String TAG = "WebApi activity";
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    RequestQueue requestQueue;
    ArrayList<Item> myDataset;
    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapi);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }
        String editText = extras.getString("EditText");
        if (editText!= null) {
          url +=editText;
            Log.e(TAG, url);
        }

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveJSON();
        handleIntent(getIntent());
    }

    private void retrieveJSON(){
        simpleProgressBar.setVisibility(View.VISIBLE);
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        myDataset = new ArrayList<Item>();

                        try {
                            JSONArray myArray = response.getJSONArray("results");
                            Log.e(TAG, String.valueOf(myArray.length()));
                            for (int i = 0; i < myArray.length(); i++) {
                                JSONObject currentObj = myArray.getJSONObject(i);
                                String name = currentObj.getString("name");
                                String businessId = currentObj.getString("businessId");
                                String registrationDate = currentObj.getString("registrationDate");
                                String companyForm = currentObj.getString("companyForm");

                                Item item = new Item();
                                item.setName(name);
                                item.setBusinessId(businessId);
                                item.setRegistrationDate(registrationDate);
                                item.setCompanyForm(companyForm);
                                myDataset.add(item);
                                //Log.e(TAG, "-------------"+name);
                            }

                        } catch (JSONException e) {
                            Log.e(TAG, e.getMessage());
                        }

                        simpleProgressBar.setVisibility(View.INVISIBLE);

                        mAdapter = new RecyclerAdapter(myDataset);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,error.getMessage());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //mAdapter.getFilter().filter(query);
                Log.i(TAG, "a) ETSITÄÄN: "+ query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                //mAdapter.getFilter().filter(query);
                Log.i(TAG, "b) ETSITÄÄN: "+ query);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
}