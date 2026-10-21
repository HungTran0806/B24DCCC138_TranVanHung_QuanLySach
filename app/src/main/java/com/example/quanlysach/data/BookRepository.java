package com.example.quanlysach.data;

import com.example.quanlysach.model.Book;
import java.util.ArrayList;

public class BookRepository {
    public static final String UNREAD = "Chưa đọc";
    public static final String READING = "Đang đọc";
    public static final String READ = "Đã đọc";

    private static final ArrayList<Book> books = new ArrayList<>();

    static {
        books.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", READING, 4, ""));
        books.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", READ, 5, ""));
        books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", UNREAD, 0, ""));
    }

    public static ArrayList<Book> getBooks() { return books; }

    public static void add(Book book) { books.add(book); }

    public static void remove(int position) { books.remove(position); }

    public static int countRead() {
        int n = 0;
        for (Book b : books) if (READ.equals(b.getStatus())) n++;
        return n;
    }

    public static int countUnfinished() { return books.size() - countRead(); }
}