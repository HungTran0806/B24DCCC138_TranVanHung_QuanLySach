package com.example.quanlysach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.quanlysach.R;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.util.UiUtils;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        Book book = getItem(position);

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvAuthor);
        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("Tác giả: " + book.getAuthor());
        tvCategory.setText(book.getCategory());
        UiUtils.applyStatusChip(tvStatus, book.getStatus());
        ratingBar.setRating(book.getRating());

        return convertView;
    }
}