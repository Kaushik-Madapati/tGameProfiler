package com.tennis.gameprofiler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nmadapati on 7/1/2016.
 */
public class StatisticDBAdapter {

       static  final String DATABASE_NAME = "tennis_statistics.db";

        static  final int DATABASE_VERSION = 2;

        // TODO: Create public field for each column in your table.
        // SQL Statement to create a new database.


        static final String DATABASE_CREATE = "create table "+"TENNIS_STATS"+
                "( " +"ID"+" integer primary key autoincrement,"+
                "USER_NAME text , TOURNAMENT  text, DATE date, FOREHAND integer, BACKHAND integer, SERVE integer, VOLLEY integer, SCORE integer, TOTAL_BALLS integer, MATCH_NAME text, COMMENTS text ); ";

        // Variable to hold the database instance
        public SQLiteDatabase db;
        // Context of the application using the database.
        private Context context;
        // Database open/upgrade helper
        private StatisticDBHelper mStatisticDBHelper;
        SQLiteDatabase.CursorFactory  cursorFactory = null;

        private GameProfilerApp mApp = null;
        public StatisticDBAdapter(Context _context)
        {
            context = _context;
            mStatisticDBHelper = new StatisticDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            mApp = (GameProfilerApp)(context.getApplicationContext());
        }

        // Method to openthe Database
        public  StatisticDBAdapter open() throws SQLException
        {
            db = mStatisticDBHelper.getWritableDatabase();
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

    /**
     *
     * @param userName : name of the user
     * @param tournamentName : tournament name
     * @param date : date of the tournament
     * @param forehand : no. of forehand errors
     * @param backend : no. of backend errors
     * @param serve : no. of server errors
     * @param volley : no. of volly errors
     * @param score : final score
     * @param totalBalls : total balls
     * @param matchName : Name of the match
     * @param comments : Any comments
     */


        public void insertEntry( String userName,
                                String tournamentName,
                                int date,
                                int forehand,
                                int backend,
                                int serve,
                                int volley,
                                String score,
                                int totalBalls,
                                String matchName,
                                String comments
        )
        {


            ContentValues newValues = new ContentValues();

            // Assign values for each column.
            newValues.put("USER_NAME", userName);
            newValues.put("TOURNAMENT", tournamentName);
            newValues.put("DATE",date);
            newValues.put("FOREHAND",forehand);
            newValues.put("BACKHAND",backend);
            newValues.put("SERVE",serve);
            newValues.put("VOLLEY",volley);
            newValues.put("SCORE",score);
            newValues.put("TOTAL_BALLS",totalBalls);
            newValues.put("MATCH_NAME",matchName);
            newValues.put("COMMENTS",comments);

            // Insert the row into your table
            try {
                db.insert("TENNIS_STATS", null, newValues);
  //              Toast.makeText(context, "Tournament not found ", Toast.LENGTH_LONG).show();
                db.close();
            }
            catch(SQLException e ) {
                e.printStackTrace();
            }


        }



        // method to get the password  of userName
        public String getSinlgeEntry(String tournamentName)
        {
            String password = null;
            try {
                Cursor cursor = db.query("TENNIS_STATS", null, " TOURNAMENT=?", new String[]{tournamentName}, null, null, null);
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

    /**
     *
     * @param userName : name of the user
     * @param tournamentName : tournament name
     * @param date : date of the tournament
     * @param forehand : no. of forehand errors
     * @param backend : no. of backend errors
     * @param serve : no. of server errors
     * @param volley : no. of volly errors
     * @param score : final score
     * @param totalBalls : total balls
     * @param matchName : Name of the match
     * @param comments : Any comments
     */

        // Method to Update an Existing Record
        public void  updateEntry( String userName,
                                  String tournamentName,
                                  int date,
                                  int  forehand,
                                  int backend,
                                  int serve,
                                  int  volley,
                                  String score,
                                  int totalBalls,
                                  String matchName,
                                  String comments)
        {
            //  create object of ContentValues
            ContentValues updatedValues = new ContentValues();
            // Assign values for each Column.

            updatedValues.put("USER_NAME", tournamentName);
            updatedValues.put("TOURNAMENT", tournamentName);
            updatedValues.put("DATE",date);
            updatedValues.put("FOREHAND",forehand);
            updatedValues.put("BACKHAND",backend);
            updatedValues.put("SERVE",serve);
            updatedValues.put("VOLLEY",volley);
            updatedValues.put("SCORE",score);
            updatedValues.put("TOTAL_BALLS",totalBalls);
            updatedValues.put("MATCH_NAME",matchName);
            updatedValues.put("COMMENTS", comments);



            String where="TOURNAMENT = ?";

            try {
                db.update("TENNIS_STATS", updatedValues, where, new String[]{tournamentName});
            }
            catch(SQLException e ) {
                e.printStackTrace();
            }


        }

    public  StatisticDBAdapter read() throws SQLException
    {
        try {
            db = mStatisticDBHelper.getReadableDatabase();
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     *
     * @param tournament : tournament name
     * @return : no. of recs in the input tournament ;
     */

    public int getTotalRecInTournament(final String tournament ){

        try {
            String input = "'" + tournament + "'";
            String name = "'" + mApp.getmUserName() + "'" ;
            Cursor res = db.rawQuery("select * from  TENNIS_STATS WHERE TOURNAMENT = " + input+ " AND USER_NAME = " + name, null);
            return res.getCount();
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param tournament : name
     * @return
     */

    public ArrayList<StatisticData> getAllMatchStatistic(final String tournament)
    {
        ArrayList<StatisticData> array_list = new ArrayList<StatisticData>();

        try {

            String input = "'" + tournament + "'";
            Cursor res = db.rawQuery("select * from TENNIS_STATS WHERE TOURNAMENT = " + input, null);

            if( res.moveToFirst()) {

                do {

                    StatisticData statisticData = new StatisticData();
                    statisticData.setmTounmaent(res.getString(res.getColumnIndex("DATE")));
                    statisticData.setmForehand(res.getInt(res.getColumnIndex("FOREHAND")));
                    statisticData.setmBackhand(res.getInt(res.getColumnIndex("BACKHAND")));
                    statisticData.setmServe(res.getInt(res.getColumnIndex("SERVE")));
                    statisticData.setmVolley(res.getInt(res.getColumnIndex("VOLLEY")));
                    statisticData.setmScore(res.getInt(res.getColumnIndex("SCORE")));
                    statisticData.setmScore(res.getInt(res.getColumnIndex("TOTAL_BALLS")));
                    statisticData.setmMatchName(res.getString(res.getColumnIndex("MATCH_NAME")));
                    statisticData.setmComments(res.getString(res.getColumnIndex("COMMENTS")));
                    array_list.add(statisticData);
                    res.moveToNext();
                } while(res.moveToNext());
            }
           return array_list;
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param tournament name
     * @return list of score and ID  in that tournament
     */
    public ArrayList<TournamentScore> getAllScoresForGivenTournament(final String tournament ){

        ArrayList<TournamentScore> listScore = new ArrayList<TournamentScore>();


        try {
            String input = "'" + tournament + "'";
            String name = "'" + mApp.getmUserName() + "'" ;
            Cursor res = db.rawQuery("select SCORE, ID from  TENNIS_STATS WHERE TOURNAMENT = " + input + " AND USER_NAME = " + name, null);
            if( res.moveToFirst()) {

                do {

                    TournamentScore scoreData = new TournamentScore();
                    scoreData.setmScore(res.getString(res.getColumnIndex("SCORE")));
                    scoreData.setmID(res.getInt(res.getColumnIndex("ID")));
                    listScore.add(scoreData);
                } while(res.moveToNext());
            }
           return listScore;

        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param dBID
     * @return
     */
    public StatisticData getDetailStatistic( final int dBID ){

        try {
            Cursor res = db.rawQuery("select * from TENNIS_STATS WHERE  ID=" + dBID, null);
          //  Cursor res = db.rawQuery("select * from  TENNIS_STATS", null);
            res.moveToFirst();

            StatisticData statisticData = new StatisticData();
            statisticData.setmForehand(res.getInt(res.getColumnIndex("FOREHAND")));
            statisticData.setmBackhand(res.getInt(res.getColumnIndex("BACKHAND")));
            statisticData.setmVolley(res.getInt(res.getColumnIndex("VOLLEY")));
            statisticData.setmServe(res.getInt(res.getColumnIndex("SERVE")));
            statisticData.setmTotalBalls(res.getInt(res.getColumnIndex("TOTAL_BALLS")));
            statisticData.setmTounmaent(res.getString(res.getColumnIndex("TOURNAMENT")));
            statisticData.setmMatchName(res.getString(res.getColumnIndex("MATCH_NAME")));
            statisticData.setmComments(res.getString(res.getColumnIndex("COMMENTS")));

            return statisticData;
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public StatisticData getPracticeStatistics(final String tournament ){

        StatisticData statisticData = new  StatisticData();

       int totalBalls = 0;
       int forehand = 0;
       int backhand = 0;
       int serve = 0;
       int volley = 0;
       String strTournament = null;

       try {
           String input = "'" + tournament + "'";
           String name = "'" + mApp.getmUserName() + "'" ;
           Cursor res = db.rawQuery("select * from  'TENNIS_STATS' WHERE TOURNAMENT = " + input + "AND USER_NAME = " + name, null);
         //  Cursor res = db.rawQuery("select * from  TENNIS_STATS", null);
           if( res.moveToFirst()) {

                do {

                   strTournament = res.getString(res.getColumnIndex("TOURNAMENT"));
                   forehand += res.getInt(res.getColumnIndex("FOREHAND"));
                   backhand += res.getInt(res.getColumnIndex("BACKHAND"));
                   volley += res.getInt(res.getColumnIndex("VOLLEY"));
                   serve += res.getInt(res.getColumnIndex("SERVE"));
                   totalBalls += res.getInt(res.getColumnIndex("TOTAL_BALLS"));

               } while(res.moveToNext());
           }

           statisticData.setmForehand(forehand);
           statisticData.setmBackhand(backhand);
           statisticData.setmVolley(volley);
           statisticData.setmServe(serve);
           statisticData.setmTounmaent(strTournament);
           statisticData.setmTotalBalls(totalBalls);


           return statisticData;
       }
       catch(SQLException e ) {
           e.printStackTrace();
       }
      return null;
    }
    public StatisticData getUserStatistics(final String username ){

        StatisticData statisticData = new  StatisticData();

        int totalBalls = 0;
        int forehand = 0;
        int backhand = 0;
        int serve = 0;
        int volley = 0;
        String strTournament = null;

        try {
             String name = "'" + mApp.getmUserName() + "'" ;
            Cursor res = db.rawQuery("select * from  'TENNIS_STATS' WHERE USER_NAME = " + name, null);
            //  Cursor res = db.rawQuery("select * from  TENNIS_STATS", null);
            if( res.moveToFirst()) {

                do {

                    strTournament = res.getString(res.getColumnIndex("TOURNAMENT"));
                    forehand += res.getInt(res.getColumnIndex("FOREHAND"));
                    backhand += res.getInt(res.getColumnIndex("BACKHAND"));
                    volley += res.getInt(res.getColumnIndex("VOLLEY"));
                    serve += res.getInt(res.getColumnIndex("SERVE"));
                    totalBalls += res.getInt(res.getColumnIndex("TOTAL_BALLS"));
                } while(res.moveToNext());
            }

            statisticData.setmForehand(forehand);
            statisticData.setmBackhand(backhand);
            statisticData.setmVolley(volley);
            statisticData.setmServe(serve);
            statisticData.setmTounmaent(strTournament);
            statisticData.setmTotalBalls(totalBalls);


            return statisticData;
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }
    public StatisticData getAllTournamentStatistics( ){

        StatisticData statisticData = new  StatisticData();

        int totalBalls = 0;
        int forehand = 0;
        int backhand = 0;
        int serve = 0;
        int volley = 0;
        String strTournament = null;

        try {
            String input = "'Practice'";
            String name = "'" + mApp.getmUserName() + "'" ;
            Cursor res = db.rawQuery("select * from  'TENNIS_STATS' WHERE TOURNAMENT != " + input + "AND USER_NAME = " + name, null);
            //  Cursor res = db.rawQuery("select * from  TENNIS_STATS", null);
            if( res.moveToFirst()) {

                do {

                    strTournament = res.getString(res.getColumnIndex("TOURNAMENT"));
                    forehand += res.getInt(res.getColumnIndex("FOREHAND"));
                    backhand += res.getInt(res.getColumnIndex("BACKHAND"));
                    volley += res.getInt(res.getColumnIndex("VOLLEY"));
                    serve += res.getInt(res.getColumnIndex("SERVE"));
                    totalBalls += res.getInt(res.getColumnIndex("TOTAL_BALLS"));
                } while(res.moveToNext());
            }

            statisticData.setmForehand(forehand);
            statisticData.setmBackhand(backhand);
            statisticData.setmVolley(volley);
            statisticData.setmServe(serve);
            statisticData.setmTounmaent(strTournament);
            statisticData.setmTotalBalls(totalBalls);


            return statisticData;
        }
        catch(SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
