package com.example.trivial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DbQuestions extends DatabaseHelper{
    Context context;

    public DbQuestions(Context context) {
        super(context);
        this.context = context;
    }

    public long insertData (String question, String optionsText, String optionsImage, int correctAnswer){
        long id = 0;

        try{

            DatabaseHelper dbHelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values  = new ContentValues();
            values.put(COLUMN_QUESTION, question);
            values.put(COLUMN_OPTIONS_TEXT, optionsText);
            values.put(COLUMN_OPTIONS_IMAGES, optionsImage);
            values.put(COLUMN_CORRECT_ANSWER, correctAnswer);

            id = db.insert(TABLE_NAME, null, values);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return id;
    }

    public ArrayList<Question> getQuestionsList(){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Question> questionsList = new ArrayList<>();
        Question question = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do{
                question = new Question();
                question.setQuestion(cursor.getString(0));
                question.setOptionsText(cursor.getString(1));
                question.setOptionsImage(cursor.getString(2));
                question.setCorrectAnswerIndex(cursor.getInt(3));
                questionsList.add(question);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return questionsList;
    }
}
