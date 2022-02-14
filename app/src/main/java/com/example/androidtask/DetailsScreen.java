package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsScreen extends AppCompatActivity {
    ImageView img;
    TextView title,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        title=(TextView)findViewById(R.id.title_details);
        description=(TextView)findViewById(R.id.description_text);
        img=(ImageView) findViewById(R.id.notes_img);
        Intent intent=getIntent();
        String title1=intent.getStringExtra("title");
        String description1=intent.getStringExtra("descri");
        String image=intent.getStringExtra("imgurl");
        title.setText(title1);
        description.setText(description1);
        Picasso.with(getApplicationContext()).load(image).into(img);

    }
}