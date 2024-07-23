package com.darksunTechnologies.HeadlineHaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.HeadlineHaven.Adapters.CategoryAdapter;
import com.example.HeadlineHaven.Adapters.NewsAdapter;
import com.example.HeadlineHaven.Models.Articles;
import com.example.HeadlineHaven.Models.Category;
import com.example.HeadlineHaven.Models.News;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.categoryClickInterface{

    private ProgressBar loadingBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<Category> categoryArrayList;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newsRV = findViewById(R.id.RV_news_ID);
        RecyclerView categoryRV = findViewById(R.id.RV_category_ID);
        Toolbar toolbar = findViewById(R.id.toolbar_ID);
        loadingBar = findViewById(R.id.PB_ID);
        articlesArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        newsAdapter = new NewsAdapter(articlesArrayList,this);
        categoryAdapter = new CategoryAdapter(categoryArrayList,this);

        setSupportActionBar(toolbar);
        newsRV.setAdapter(newsAdapter);
        categoryRV.setAdapter(categoryAdapter);
        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategories() {
        categoryArrayList.add(new Category("All","https://resize.indiatvnews.com/en/resize/newbucket/715_-/2022/05/breaking-news-template-2-1653872647.jpg"));
        categoryArrayList.add(new Category("Technology","https://images.unsplash.com/photo-1535223289827-42f1e9919769?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        categoryArrayList.add(new Category("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoryArrayList.add(new Category("Business","https://images.unsplash.com/photo-1579532537598-459ecdaf39cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoryArrayList.add(new Category("Science","https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        categoryArrayList.add(new Category("Sports","https://images.unsplash.com/photo-1471295253337-3ceaaedca402?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1168&q=80"));
        categoryArrayList.add(new Category("Entertainment","https://images.unsplash.com/photo-1481328101413-1eef25cc76c0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        categoryAdapter.notifyDataSetChanged();
    }

    private void getNews(String category)
    {
        loadingBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        String BASE_URL = "https://newsapi.org/";
        String categoryURL = BASE_URL + "v2/top-headlines?country=in&category="+category+"&apiKey=54b0b2b128794dfdaa759429868f692a";
        String url =  BASE_URL + "v2/top-headlines?country=in&sortBypublishedAt&language=en&apiKey=54b0b2b128794dfdaa759429868f692a";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<News> call;

        if (category.equals("All")) {
            call = retrofitAPI.getAllNews(url);
        }
        else {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<News>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                News News = response.body();
                loadingBar.setVisibility(View.GONE);
                assert News != null;
                ArrayList<Articles> articles = News.getArticles();

                for(int i=0;i<articles.size();i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrl(),articles.get(i).getUrlToImage(),articles.get(i).getContent()));
                }
                newsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryArrayList.get(position).getCategory();
        getNews(category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option_home:
                return true;

            case R.id.option_aboutUs:
                // Send to about us screen
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}




