package com.example.trivial;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.DeniedByServerException;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "trivial.db";
    public static final int DATABASE_VERSION = 1;

    // Definir la estructura de la tabla de preguntas
    public static final String TABLE_NAME = "questions";
    public static final String COLUMN_ID = "_id";
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
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_OPTIONS_TEXT + " TEXT, " +
                COLUMN_OPTIONS_IMAGES + " TEXT, " +
                COLUMN_CORRECT_ANSWER + " INTEGER)";
        db.execSQL(createTableQuery);

//        //insertSampleData(db);
//        ContentValues values  = new ContentValues();
//        values.put(COLUMN_QUESTION, "question");
//        values.put(COLUMN_OPTIONS_TEXT, "optionsText");
//        values.put(COLUMN_OPTIONS_IMAGES, "optionsImage");
//        values.put(COLUMN_CORRECT_ANSWER, 1);
//
//        ContentValues values2  = new ContentValues();
//
//        values2.put(COLUMN_QUESTION, "questio2n");
//        values2.put(COLUMN_OPTIONS_TEXT, "optionsText2");
//        values2.put(COLUMN_OPTIONS_IMAGES, "optionsImage2");
//        values2.put(COLUMN_CORRECT_ANSWER, 2);
//
//
//        db.insert(TABLE_NAME, null, values);
//        db.insert(TABLE_NAME, null, values2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_NAME);
        onCreate(db);
    }

    public void deleteData(DbQuestions db){

    }

//    public void insertSampleData(SQLiteDatabase db) {
//        // Insert sample questions
//        insertData(db,"¿Cuál es el país de origen del fútbol?", "Inglaterra,España,Alemania,Francia", null, 0);
//        insertData(db,"¿Qué equipo ha ganado la Copa del Mundo más veces?", null, "R.drawable.alemania,R.drawable.argentina,R.drawable.brasil,R.drawable.italia", 2);
//        insertData(db,"¿Qué Copa de Europa ganó el Real Madrid gracias a la volea de Zidane?", "Séptima,Novena,Octava,Décima", null, 1);
//        insertData(db,"¿Quién ha ganado más Balones de Oro en toda la historia?", "Messi,Cristiano Ronaldo,Ronaldo Nazario,Maradona", null, 0);
//        insertData(db,"¿Cuál es el equipo de fútbol más antiguo del mundo?", "Manchester United,Sheffield,Sevilla,Boca Juniors", null, 1);
//    }
//
//    public void insertData (SQLiteDatabase db, String question, String optionsText, String optionsImage, int correctAnswer){
//        try{
//            ContentValues values  = new ContentValues();
//            values.put(COLUMN_QUESTION, question);
//            values.put(COLUMN_OPTIONS_TEXT, optionsText);
//            values.put(COLUMN_OPTIONS_IMAGES, optionsImage);
//            values.put(COLUMN_CORRECT_ANSWER, correctAnswer);
//
//            db.insert(TABLE_NAME, null, values);
//            db.close();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
}
