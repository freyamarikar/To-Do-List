package com.example.to_do_listapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "TodoDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "task TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addTask(String task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("task", task);

        long result = db.insert("Tasks", null, values);

        return result != -1;
    }

    public String getAllTasks() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT task FROM Tasks ORDER BY id ASC", null);

        StringBuilder tasks = new StringBuilder();
        int count = 1;

        try {
            while (cursor.moveToNext()) {
                tasks.append(count++)
                        .append(". ")
                        .append(cursor.getString(0))
                        .append("\n\n");
            }
        } finally {
            cursor.close();
        }

        if (tasks.length() == 0) {
            return "No tasks found";
        }

        return tasks.toString();
    }
}
