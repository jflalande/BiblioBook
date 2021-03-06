package com.example.bibliobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageView imageview1 = findViewById(R.id.searchIconImage);
        imageview1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Toast.makeText(Search.this, "Recherche lancée",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), ResultList.class);
                EditText edit = (EditText)findViewById(R.id.searchField);
                intent.putExtra("SEARCHED", edit.getText().toString());
                startActivity(intent);

            }

        });
    }


}
