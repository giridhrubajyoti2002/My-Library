package com.dhrubajyoti.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private  RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

//        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);   // for Animation

        //opt Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        booksRecView = findViewById(R.id.booksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "AllBooksActivity");
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAllBooks());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    //  for Animation

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
//    }
}