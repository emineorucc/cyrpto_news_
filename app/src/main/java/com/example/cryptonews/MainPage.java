package com.example.cryptonews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.NoCopySpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cryptonews.Models.CryptoNewsApiResponse;
import com.example.cryptonews.Models.CryptoNewsHeadlines;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainPage extends AppCompatActivity implements SelectListener, View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of" + query);
                dialog.show();




                RequestManager manager = new RequestManager(MainPage.this);
                manager.getCryptoNewsHeadlines(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog= new ProgressDialog(this);
        dialog.setTitle("Fetching news articles..");
        dialog.show();


        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);



        RequestManager manager = new RequestManager(this);
        manager.getCryptoNewsHeadlines(listener,  "crypto");
    }

    private final OnFetchDataListener<CryptoNewsApiResponse> listener = new OnFetchDataListener<CryptoNewsApiResponse>() {
        @Override
        public void onFetchData(List<CryptoNewsHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainPage.this, "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainPage.this, "An Error Occured!!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showNews(List<CryptoNewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(CryptoNewsHeadlines headlines) {
        startActivity(new Intent(MainPage.this, DetailsActivity.class)
        .putExtra("data", headlines));

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String  query= button.getText().toString();
        dialog.setTitle("Fetching news articles" + query);
        RequestManager manager = new RequestManager(this);
        manager.getCryptoNewsHeadlines(listener, query);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainPage.this, settings.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.share) {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainPage.this, share.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}