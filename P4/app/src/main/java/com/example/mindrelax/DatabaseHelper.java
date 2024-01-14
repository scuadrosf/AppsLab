package com.example.mindrelax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Constantes para el nombre de la base de datos y la versión
    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    // Constantes para el nombre de la tabla y las columnas
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tabla si existía
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Crear tabla nuevamente
        onCreate(db);
    }

    // Método para insertar un nuevo usuario
    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_USER_PASSWORD, password);

        // Insertar fila
        db.insert(TABLE_USERS, null, values);
        db.close(); // Cerrar conexión a la base de datos
    }

    // Método para verificar el usuario y la contraseña
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_NAME + " =? AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USERS, //Tabla a consultar
                columns,                    //columnas a retornar
                selection,                 //columnas para la cláusula WHERE
                selectionArgs,             //valores para la cláusula WHERE
                null,                      //group by
                null,                      //having
                null);                     //order by

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public void updateUserPassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword); // Asume que tu columna de contraseña se llama "password"
        db.update("users", values, "username = ?", new String[]{username}); // Asume que tu columna de nombre de usuario se llama "username"
        db.close();
    }
}