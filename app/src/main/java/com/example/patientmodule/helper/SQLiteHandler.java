package com.example.patientmodule.helper;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
import java.util.HashMap;
 
public class SQLiteHandler extends SQLiteOpenHelper {
 
    private static final String TAG = SQLiteHandler.class.getSimpleName();
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "android_api";
 
    // Login table name
    private static final String TABLE_USER = "user";
 
    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NO = "phone_no";
    private static final String KEY_UID = "uid";
    private static final String KEY_AGE = "age";
    private static final String KEY_LOGIN_ID = "login_id";

   // private static final String KEY_CREATED_AT = "created_at";
 
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_UID + " TEXT," + KEY_PHONE_NO + " TEXT UNIQUE,"
                + KEY_AGE + " TEXT," + KEY_LOGIN_ID + " INTEGER"+ ")";
        db.execSQL(CREATE_LOGIN_TABLE);
 
        Log.d(TAG, "Database tables created");
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
    public void addUser(String id, String fname, String lname, String phone_no,String email, String uid, String age, String login_id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_FNAME, fname); //First Name
        values.put(KEY_LNAME, lname); //Last Name
        values.put(KEY_PHONE_NO, phone_no); //First Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email
        values.put(KEY_AGE, age); // Age
        values.put(KEY_LOGIN_ID, login_id);

 
        // Inserting Row
        long log_id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
 
        Log.d(TAG, "New user inserted into sqlite: " + log_id);
    }
 
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("fname", cursor.getString(1));
            user.put("lname", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("uid", cursor.getString(4));
            user.put("phone_no", cursor.getString(5));
            user.put("age", cursor.getString(6));
            user.put("login_id", cursor.getString(7));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
 
        return user;
    }
 
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
 
        Log.d(TAG, "Deleted all user info from sqlite");
    }
 
}