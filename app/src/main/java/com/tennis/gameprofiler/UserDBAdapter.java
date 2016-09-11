package com.tennis.gameprofiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nmadapati on 7/18/2016.
 */
public class UserDBAdapter {
    static  final String DATABASE_NAME = "userDB.db";

    static  final int DATABASE_VERSION = 1;

    private static final String TENNIS_USER = "tennis_user";

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.


    static final String DATABASE_CREATE = "create table "+ TENNIS_USER +
            "( " +"ID"+" integer primary key autoincrement,"+
            "USER_NAME  text, PASSWORD text, EMAIL  text ); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private Context context;
    // Database open/upgrade helper
    private UserDBHelper userDBHelper;
    SQLiteDatabase.CursorFactory  cursorFactory = null;
    public UserDBAdapter(Context _context)
    {
        context = _context;
        userDBHelper = new UserDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  UserDBAdapter open() throws SQLException
    {
        db = userDBHelper.getWritableDatabase();
        return this;
    }

    public  UserDBAdapter read() throws SQLException
    {
        db = userDBHelper.getReadableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table


    public void insertEntry(String user_name,
                            String password,
                            String email)


    {


        ContentValues newValues = new ContentValues();

        int result = 0;

        // Assign values for each column.
        newValues.put("USER_NAME", user_name);
        newValues.put("PASSWORD", password);
        newValues.put("EMAIL", email);


        // Insert the row into your table
        try {
            db.insert(TENNIS_USER, null, newValues);
            ///       db.close();
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        // Toast.makeText(context, "TOURNAMENT NAME not found ", Toast.LENGTH_LONG).show();
    }



    // method to get the password  of userName
    public String getSinlgeEntry(String userName)
    {

        String password = null;

        try {
            Cursor cursor = db.query(TENNIS_USER, null, " USER_NAME=?", new String[]{userName}, null, null, null);
            if (cursor.getCount() < 1) // UserName Not Exist
                return "NOT EXIST";
            cursor.moveToFirst();
            password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return password;


    }



    // Method to Update an Existing Record
    public void  updateEntry(String user_name,
                             String password,
                             String  email)


    {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.

        updatedValues.put("USER_NAME", user_name);
        updatedValues.put("PASSWORD",password);
        updatedValues.put("EMAIL", email);
        String where="USER_NAME = ?";

        try {
            db.update(TENNIS_USER, updatedValues, where, new String[]{user_name});
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }

    }

}
