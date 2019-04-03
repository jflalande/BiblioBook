package com.example.bibliobook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.concurrent.Semaphore;

public class ResultList extends AppCompatActivity {

    private RequestQueue queue;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Livre> livreArrayList = new ArrayList<>();
    private Semaphore s = new Semaphore(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        queue = Volley.newRequestQueue(this);
        final TextView text = (TextView) findViewById(R.id.TopBarSearchText);
        String searchedTerms = getIntent().getStringExtra("SEARCHED");
        text.setText(searchedTerms);
        ImageView imageview1 = findViewById(R.id.returnToSearchArrow);
        imageview1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                finish();

            }

        });
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + searchedTerms;
        TextView deb = (TextView) findViewById(R.id.debugLog);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            //deb.setText("");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject livre = jsonArray.getJSONObject(i);
                                JSONObject info = livre.getJSONObject("volumeInfo");
                                String titre = info.getString("title");
                                JSONArray auteurArray = info.getJSONArray("authors");
                                String auteur = (String)auteurArray.get(0);
                                JSONArray genreArray = info.getJSONArray("categories");
                                String genre = (String)genreArray.get(0);
                                JSONObject images = info.getJSONObject("imageLinks");
                                String miniature = images.getString("thumbnail");
                                String date = info.getString("publishedDate");
                                //deb.append(titre + " de " + auteur +"\n\n");
                                Livre livreLoop = new Livre(titre,auteur,genre,date,miniature);
                                livreArrayList.add(livreLoop);
                                //deb.append(titre + livreArrayList.size());
                            }
                            mAdapter = new LivreAdapter(livreArrayList);
                            mRecyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //textCenter.setText("JSON Parsing Failed");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        //textCenter.setText("Erreur lors de l'appel API");
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
    }


}
