package com.darksunTechnologies.HeadlineHaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,imageUrl1,url;
    ImageView bannerImg;
    TextView titleTV,contentTV;
    Button fullArticalBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        bannerImg =findViewById(R.id.newsBanner_ID);
        titleTV = findViewById(R.id.newsTitle_ID);
        contentTV = findViewById(R.id.newsContent_ID);
        fullArticalBTN = findViewById(R.id.fullArticalBTN_ID);

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl1 = getIntent().getStringExtra("imageUrl");
        url = getIntent().getStringExtra("url");



        Picasso.get().load(imageUrl1).into(bannerImg);
        titleTV.setText(title);
        contentTV.setText(content);

        fullArticalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullArtical = new Intent(getApplicationContext(), FullArticleWebView.class);
                fullArtical.putExtra("urlforAtrical",url);
                startActivity(fullArtical);

            }
        });


    }
}