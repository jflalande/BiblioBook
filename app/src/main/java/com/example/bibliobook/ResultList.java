package com.example.bibliobook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ResultList extends AppCompatActivity {

    private RequestQueue queue;
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

                Intent intent = new Intent(getBaseContext(), Search.class);
                startActivity(intent);

            }

        });
        final TextView textCenter = (TextView) findViewById(R.id.textView3);
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + searchedTerms;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textCenter.setText("");
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject livre = jsonArray.getJSONObject(i);
                                JSONObject info = livre.getJSONObject("volumeInfo");
                                String titre = info.getString("title");
                                JSONArray auteurArray = info.getJSONArray("authors");
                                String auteur = (String)auteurArray.get(0);
                                textCenter.append(titre + " de " + auteur + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            textCenter.setText("JSON Parsing Failed");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textCenter.setText("Erreur lors de l'appel API");
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);

    }


}
