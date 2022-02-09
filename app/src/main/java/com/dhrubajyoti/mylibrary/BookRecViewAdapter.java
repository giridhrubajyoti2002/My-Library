package com.dhrubajyoti.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BooksRecViewHolder";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.collapsedRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra("bookId", books.get(holder.getAdapterPosition()).getId());
//                intent.putExtra("book", (Parcelable) books.get(holder.getAdapterPosition()));        // using parcelable
                mContext.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDesc());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView imgBook;
        private TextView txtBookName;
        private RelativeLayout collapsedRelLayout, expandedRelLayout;

        private ImageView upArrow,downArrow;
        private TextView txtAuthor,txtShortDesc,btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.bookImage);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionManager.beginDelayedTransition(parent);
                    downArrow.setVisibility(View.GONE);
                    expandedRelLayout.setVisibility(View.VISIBLE);
                    if(parentActivity.equals("AllBooksActivity")){
                        btnRemove.setVisibility(View.GONE);
                    }else{
                        btnRemove.setVisibility(View.VISIBLE);
                    }
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionManager.beginDelayedTransition(parent);
                    downArrow.setVisibility(View.VISIBLE);
                    expandedRelLayout.setVisibility(View.GONE);
                }
            });
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book;
                    AlertDialog.Builder builder;
                    switch(parentActivity){
                                case "AlreadyReadBooksActivity":
                                    book = Utils.getInstance(mContext).getAlreadyReadBooks().get(getAdapterPosition());
                                    builder = new AlertDialog.Builder(mContext);
                                    builder.setMessage("Are you sure you want to delete "+ book.getName()+" ?");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Utils.getInstance(mContext).removeFromAlreadyRead(getAdapterPosition())) {
                                                mContext.startActivity(new Intent(mContext,mContext.getClass()));
//                                                notifyDataSetChanged();
                                                Toast.makeText(mContext, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(mContext, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    builder.create().show();
                                    break;
                                case "CurrentlyReadingActivity":
                                    book = Utils.getInstance(mContext).getCurrentlyReadingBooks().get(getAdapterPosition());
                                    builder = new AlertDialog.Builder(mContext);
                                    builder.setMessage("Are you sure you want to delete "+ book.getName()+" ?");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Utils.getInstance(mContext).removeFromCurrentlyReading(getAdapterPosition())) {
                                                Intent intent = new Intent(mContext,mContext.getClass());
                                                mContext.startActivity(intent);
//                                                notifyDataSetChanged();
                                                Toast.makeText(mContext, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(mContext, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    builder.create().show();
                                    break;
                                case "FavouritesActivity":
                                    book = Utils.getInstance(mContext).getFavouriteBooks().get(getAdapterPosition());
                                    builder = new AlertDialog.Builder(mContext);
                                    builder.setMessage("Are you sure you want to delete "+ book.getName()+" ?");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Utils.getInstance(mContext).removeFromFavourites(getAdapterPosition())) {
                                                mContext.startActivity(new Intent(mContext,mContext.getClass()));
//                                                notifyDataSetChanged();
                                                Toast.makeText(mContext, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(mContext, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    builder.create().show();
                                    break;
                                case "WishlistActivity":
                                    book = Utils.getInstance(mContext).getWishlistBooks().get(getAdapterPosition());
                                    builder = new AlertDialog.Builder(mContext);
                                    builder.setMessage("Are you sure you want to delete "+ book.getName()+" ?");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Utils.getInstance(mContext).removeFromWishlist(getAdapterPosition())) {
                                                mContext.startActivity(new Intent(mContext,mContext.getClass()));
//                                                notifyDataSetChanged();
                                                Toast.makeText(mContext, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(mContext, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    builder.create().show();
                                    break;
                            }
                }


            });
        }
    }
}
