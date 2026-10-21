package com.example.quanlysach.util;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;
import com.example.quanlysach.data.BookRepository;

public class UiUtils {

    public static void applyStatusChip(TextView chip, String status) {
        int background;
        int foreground;

        if (BookRepository.READ.equals(status)) {
            background = Color.parseColor("#E8F5E9");
            foreground = Color.parseColor("#2E7D32");
        } else if (BookRepository.READING.equals(status)) {
            background = Color.parseColor("#FFF3E0");
            foreground = Color.parseColor("#E65100");
        } else {
            background = Color.parseColor("#EEEEEE");
            foreground = Color.parseColor("#616161");
        }

        chip.setText(status);
        chip.setTextColor(foreground);
        chip.setBackgroundTintList(ColorStateList.valueOf(background));
    }
}