package com.example.trivial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "trivial.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "questions";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPTIONS_TEXT = "options_text";
    public static final String COLUMN_OPTIONS_IMAGES = "options_images";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de preguntas
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +

                COLUMN_QUESTION + " TEXT, " +
                COLUMN_OPTIONS_TEXT + " TEXT, " +
                COLUMN_OPTIONS_IMAGES + " TEXT, " +
                COLUMN_CORRECT_ANSWER + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

}
