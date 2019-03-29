package com.example.bibliobook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultList extends AppCompatActivity {

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
        TextView text = (TextView)findViewById(R.id.TopBarSearchText);
        text.setText(getIntent().getStringExtra("SEARCHED"));
        ImageView imageview1 = findViewById(R.id.returnToSearchArrow);
        imageview1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), Search.class);
                startActivity(intent);

            }

        });


    }
}
