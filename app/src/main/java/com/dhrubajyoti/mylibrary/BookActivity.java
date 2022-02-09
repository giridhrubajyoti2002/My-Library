package com.dhrubajyoti.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToCurrentlyReading, btnAddToAlreadyRead, btnAddToWishlist, btnAddToFavourites;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        //opt Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null){
            int bookId = intent.getIntExtra("bookId", -1);
            if(bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);
                    handleAlreadyReadBooks(incomingBook);
                    handleWishlistBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
//            Book book = (Book)intent.getExtras().get("book");      // using parcelable
//            setData(book);
        }

    }

    private void handleFavouriteBooks(final Book book) {
        if(Utils.getInstance(this).isInFavouriteBooks(book)){
            btnAddToFavourites.setEnabled(false);
        }else{
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavourites(book)){
                        Intent intent = new Intent(BookActivity.this,FavouritesActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this, book.getName()+" added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        if(Utils.getInstance(this).isInCurrentlyReadingBooks(book)){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this, book.getName()+" added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishlistBooks(final Book book) {
        if(Utils.getInstance(this).isInWishlistBooks(book)){
            btnAddToWishlist.setEnabled(false);
        }else{
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWishlist(book)){
                        Intent intent = new Intent(BookActivity.this,WishlistActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this, book.getName()+" added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyReadBooks(final Book book) {
        if(Utils.getInstance(this).isInAlreadyReadBooks(book)){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Intent intent = new Intent(BookActivity.this,AlreadyReadBooksActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this, book.getName()+" added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

        bookImage = findViewById(R.id.bookImage);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}