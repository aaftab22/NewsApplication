package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements categoryAdapter.categoryClickInterface{
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<categoryModel> categoryModelArrayList;
    private categoryAdapter categoryAdapter;
    private newsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRV = findViewById(R.id.RV_news_ID);

        categoryRV = findViewById(R.id.RV_category_ID);

        loadingBar = findViewById(R.id.PB_ID);
        articlesArrayList = new ArrayList<>();
        categoryModelArrayList = new ArrayList<>();
        newsAdapter = new newsAdapter(articlesArrayList,this);
        categoryAdapter = new categoryAdapter(categoryModelArrayList,this,this::onCategoryClick);

        newsRV.setAdapter(newsAdapter);
        categoryRV.setAdapter(categoryAdapter);
        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }

    private void getCategories() {

        categoryModelArrayList.add(new categoryModel("All","https://resize.indiatvnews.com/en/resize/newbucket/715_-/2022/05/breaking-news-template-2-1653872647.jpg"));
        categoryModelArrayList.add(new categoryModel("Technology","https://images.unsplash.com/photo-1535223289827-42f1e9919769?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        categoryModelArrayList.add(new categoryModel("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoryModelArrayList.add(new categoryModel("Business","https://images.unsplash.com/photo-1579532537598-459ecdaf39cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoryModelArrayList.add(new categoryModel("Science","https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        categoryModelArrayList.add(new categoryModel("Sports","https://images.unsplash.com/photo-1471295253337-3ceaaedca402?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1168&q=80"));
        categoryModelArrayList.add(new categoryModel("Entertainment","https://images.unsplash.com/photo-1481328101413-1eef25cc76c0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        categoryAdapter.notifyDataSetChanged();
    }

    private void getNews(String category)
    {
        loadingBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=54b0b2b128794dfdaa759429868f692a";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBypublishedAt&language=en&apiKey=54b0b2b128794dfdaa759429868f692a";

        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<newsModelClass> call;


        if (category.equals("All"))
        {
            call = retrofitAPI.getAllNews(url);
        }
        else
        {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<newsModelClass>() {
            @Override
            public void onResponse(Call<newsModelClass> call, Response<newsModelClass> response) {
                newsModelClass newsModelClass = response.body();
                loadingBar.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModelClass.getArticles();
                for(int i=0;i<articles.size();i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrl(),articles.get(i).getUrlToImage(),articles.get(i).getContent()));

                }
                newsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<newsModelClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryModelArrayList.get(position).getCategory();
        getNews(category);
    }
}