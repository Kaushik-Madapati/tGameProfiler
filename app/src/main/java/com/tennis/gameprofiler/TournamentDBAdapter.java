
package com.tennis.gameprofiler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nmadapati on 7/3/2016.
 */
public class TournamentDBAdapter {
    static  final String DATABASE_NAME = "tournaments.db";

    static  final int DATABASE_VERSION = 1;

    private static final String TENNIS_TOURNAMENT = "tournament";

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.


    static final String DATABASE_CREATE = "create table "+ TENNIS_TOURNAMENT +
            "( " +"ID"+" integer primary key autoincrement,"+
            "NAME  text, PLACE text, COURT_TYPE  text ); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private Context context;
    // Database open/upgrade helper
    private TournamentDBHelper tournamentDbHelper;
    SQLiteDatabase.CursorFactory  cursorFactory = null;
    public TournamentDBAdapter(Context _context)
    {
        context = _context;
        tournamentDbHelper = new TournamentDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  TournamentDBAdapter open() throws SQLException
    {
        db = tournamentDbHelper.getWritableDatabase();
        return this;
    }

    public  TournamentDBAdapter read() throws SQLException
    {
        db = tournamentDbHelper.getReadableDatabase();
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


    public void insertEntry(String name,
                            String place,
                            String courtType)


    {


        ContentValues newValues = new ContentValues();

        int result = 0;

        // Assign values for each column.
        newValues.put("NAME", name);
        newValues.put("PLACE", place);
        newValues.put("COURT_TYPE", courtType);
              // Insert the row into your table
       try {
           db.insert(TENNIS_TOURNAMENT, null, newValues);
    ///       db.close();
       }
       catch(SQLException e ) {
           e.printStackTrace();
       }
       // Toast.makeText(context, "TOURNAMENT NAME not found ", Toast.LENGTH_LONG).show();
       }



    // method to get the password  of userName
    public String getSinlgeEntry(String tournamentName)
    {

        String password = null;

       try {
           Cursor cursor = db.query(TENNIS_TOURNAMENT, null, " NAME=?", new String[]{tournamentName}, null, null, null);
           if (cursor.getCount() < 1) // UserName Not Exist
               return "NOT EXIST";
           cursor.moveToFirst();
           password = cursor.getString(cursor.getColumnIndex("NAME"));
       }
       catch(SQLException e ) {
           e.printStackTrace();
       }
        return password;


    }

    // Method to Update an Existing Record
    public void  updateEntry(String name,
                             String place,
                             String  courtType)

    {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.

        updatedValues.put("NAME", name);
        updatedValues.put("PLACE",place);
        updatedValues.put("COURT_TYPE", courtType);
       String where="NAME = ?";

        try {
            db.update(TENNIS_TOURNAMENT, updatedValues, where, new String[]{name});
        }
            catch(SQLException e ) {
                e.printStackTrace();
            }

    }

    /**
     *
     * @return all Tournmaent names
     */

    public void getAllTournaments(ArrayList<String> arrayList)
    {
        arrayList.add(" Tournament Name ");
        String selectQuery = "SELECT * FROM " + TENNIS_TOURNAMENT;
         try {
             Cursor res = db.rawQuery(selectQuery, null);

            res.moveToFirst();

            while(res.isAfterLast() == false){
                arrayList.add(res.getString(res.getColumnIndex("NAME")));
                res.moveToNext();
            }
         }
         catch(SQLException e ) {
             e.printStackTrace();
         }


    }


}
