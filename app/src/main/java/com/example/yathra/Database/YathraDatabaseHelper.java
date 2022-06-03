package com.example.yathra.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yathra.Model.StationModel;
import com.example.yathra.Model.TicketModel;
import com.example.yathra.Model.TimetableModel;
import com.example.yathra.Model.TrainModel;

import java.util.ArrayList;

public class YathraDatabaseHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;
    private static String DATABASE_NAME = "Yathra.db";

    //Timetable Table
    private static final String Timetable_TABLE_NAME = "Train_Timetable";
    private static final String ID = "ID";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String DATE = "Date";
    private static final String ARRIVE_TIME = "Arrive_Time";
    private static final String DEPART_TIME = "Depart_Time";
    private static final String TRAIN_NAME = "Train_Name";

    //Train Table
    private static final String TRAIN_TABLE_NAME = "Train";
    private static final String TRAIN_ID_TT = "Train_ID";
    private static final String TRAIN_NAME_TT = "Train_Name";
    private static final String NO_OF_PASSENGERS_TT = "No_Of_Passengers";
    private static final String TRAIN_CATEGORY_TT = "Train_Category";

    //Train Station
    private static final String STATION_TABLE_NAME = "Station";
    private static final String STATION_ID_ST = "Station_ID";
    private static final String STATION_NAME_ST = "Station_Name";
    private static final String STATION_MASTER_ST = "Station_Master";
    private static final String STATION_CITY_ST = "City";

    //Ticket Table
    private static final String TICKET_TABLE_NAME = "Ticket";
    private static final String TICKET_ID = "Ticket_ID";
    private static final String TICKET_DATE = "Ticket_Date";
    private static final String TICKET_TIME = "Ticket_Time";
    private static final String TICKET_CLASS = "Ticket_Class";
    private static final String TICKET_FROM = "Ticket_From";
    private static final String TICKET_TO = "Ticket_To";
    private static final String TICKET_PRICE = "Ticket_Price";

    public YathraDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create Timetable Table
        String TABLE_CREATE_QUERY = "CREATE TABLE " + Timetable_TABLE_NAME + " "+
                " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROUTE_NAME + " TEXT, "
                + DATE + " TEXT, "
                + ARRIVE_TIME + " TEXT, "
                + DEPART_TIME + " TEXT, "
                + TRAIN_NAME + " TEXT"+
                ");";


        //Create Train Table
        String TABLE_CREATE_ADD_TRAIN = "CREATE TABLE " + TRAIN_TABLE_NAME + " "+
                " ("
                + TRAIN_ID_TT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRAIN_NAME_TT + " TEXT, "
                + NO_OF_PASSENGERS_TT + " TEXT, "
                + TRAIN_CATEGORY_TT + " TEXT"+
                ");";

        //Create Station Table
        String TABLE_CREATE_ADD_STATION = " CREATE TABLE " + STATION_TABLE_NAME + " "+
                " ("
                + STATION_ID_ST + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STATION_NAME_ST + " TEXT, "
                + STATION_MASTER_ST + " TEXT, "
                + STATION_CITY_ST + " TEXT"+
                ");";

        //Create Ticket Table
        String TABLE_CREATE_ADD_TICKET = "CREATE TABLE " + TICKET_TABLE_NAME + " "+
                " ("
                + TICKET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TICKET_DATE + " TEXT, "
                + TICKET_TIME + " TEXT, "
                + TICKET_CLASS + " TEXT, "
                + TICKET_FROM + " TEXT, "
                + TICKET_TO + " TEXT, "
                + TICKET_PRICE + " INTEGER"+
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
        sqLiteDatabase.execSQL(TABLE_CREATE_ADD_TRAIN);
        sqLiteDatabase.execSQL(TABLE_CREATE_ADD_STATION);
        sqLiteDatabase.execSQL(TABLE_CREATE_ADD_TICKET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE_QUERY = " DROP TABLE IF EXISTS " + Timetable_TABLE_NAME;
        String DROP_TRAIN_QUERY = " DROP TABLE IF EXISTS " + TRAIN_TABLE_NAME;
        String DROP_STATION_QUERY = " DROP TABLE IF EXISTS " + STATION_TABLE_NAME;
        String DROP_TICKET_QUERY = " DROP TABLE IF EXISTS " + TICKET_TABLE_NAME;

        //Drop Older Table if Existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        sqLiteDatabase.execSQL(DROP_TRAIN_QUERY);
        sqLiteDatabase.execSQL(DROP_STATION_QUERY);
        sqLiteDatabase.execSQL(DROP_TICKET_QUERY);

        //Create Table Again
        onCreate(sqLiteDatabase);

    }
//Timetable Function ----------------------------------------------------------------------------------

    public long insertTimetable(TimetableModel tm){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //store all data and pass it to database table
        contentValues.put(ROUTE_NAME,tm.getrName());
        contentValues.put(DATE,tm.getDate());
        contentValues.put(ARRIVE_TIME,tm.getaTime());
        contentValues.put(DEPART_TIME,tm.getdTime());
        contentValues.put(TRAIN_NAME,tm.gettName());

        //insert data inside database table
        long result = sqLiteDatabase.insert(Timetable_TABLE_NAME, null, contentValues);

        return result;
    }

    public ArrayList<TimetableModel> getAllTimetables(){

        ArrayList<TimetableModel> Timetables = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //select * data from Timetable
        String GET_DATA_QUERY = " SELECT * FROM " + Timetable_TABLE_NAME;

        Cursor cursor = null; //Cursor OBJ

        cursor = sqLiteDatabase.rawQuery(GET_DATA_QUERY, null); //cursor obtain all data from db

        if( cursor.moveToFirst()){ //first row

            do{
                //row data
                int id = cursor.getInt(0);
                String rName = cursor.getString(1);
                String tDate = cursor.getString(2);
                String aTime = cursor.getString(3);
                String dTime = cursor.getString(4);
                String tName = cursor.getString(5);

                TimetableModel tm = new TimetableModel(id, rName, tDate, aTime, dTime, tName);
                Timetables.add(tm);

            }while(cursor.moveToNext());//next row

        }

        return Timetables;
    }

    public int updateTimetable(TimetableModel tm) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //store all data and pass it to database table
        contentValues.put(ROUTE_NAME,tm.getrName());
        contentValues.put(DATE,tm.getDate());
        contentValues.put(ARRIVE_TIME,tm.getaTime());
        contentValues.put(DEPART_TIME,tm.getdTime());
        contentValues.put(TRAIN_NAME,tm.gettName());

        int result = sqLiteDatabase.update(Timetable_TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(tm.getId())});
        return result;
    }

    public int deleteTimetable(int id) {

        SQLiteDatabase DBHelper = getWritableDatabase();

        int result = DBHelper.delete(Timetable_TABLE_NAME, "id=?",new String[]{String.valueOf(id)});

        return result;
    }


//Train Function-------------------------------------------------------------------------------------------

    //Insert Train to Train Table
    public long InsertTrain(TrainModel trainM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TRAIN_NAME_TT,trainM.getTrainName());
        contentValues.put(NO_OF_PASSENGERS_TT,trainM.getNoOfPassengers());
        contentValues.put(TRAIN_CATEGORY_TT,trainM.getTrainCategory());

        long result = sqLiteDatabase.insert(TRAIN_TABLE_NAME, null, contentValues);

        return result;
    }

    public ArrayList<TrainModel> getAllTrainDetails(){

        ArrayList<TrainModel> TrainsDetails = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String GET_DATA_QUERY = " SELECT * FROM " + TRAIN_TABLE_NAME;

        Cursor cursor = null;

        cursor = sqLiteDatabase.rawQuery(GET_DATA_QUERY, null);

        if( cursor.moveToFirst()){

            do{
                //row data
                int id = cursor.getInt(0);
                String trainName = cursor.getString(1);
                String noOfPassengers = cursor.getString(2);
                String trainCategory = cursor.getString(3);

                TrainModel trainM = new TrainModel(id, trainName, noOfPassengers,trainCategory);
                TrainsDetails.add(trainM);

            }while(cursor.moveToNext());

        }

        return TrainsDetails;
    }

    public int updateTrainTable(TrainModel trainM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TRAIN_NAME_TT,trainM.getTrainName());
        contentValues.put(NO_OF_PASSENGERS_TT,trainM.getNoOfPassengers());
        contentValues.put(TRAIN_CATEGORY_TT,trainM.getTrainCategory());

        int result = sqLiteDatabase.update(TRAIN_TABLE_NAME, contentValues, "Train_ID=?", new String[]{String.valueOf(trainM.getId())});

        return result;
    }

    public int deleteTrains(int id) {

        SQLiteDatabase DBHelper = getWritableDatabase();

        int result = DBHelper.delete(TRAIN_TABLE_NAME, "Train_ID=?",new String[]{String.valueOf(id)});

        return result;
    }

//Station Function -----------------------------------------------------------------------------------

    public long insertStation(StationModel SM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STATION_NAME_ST,SM.getStationName());
        contentValues.put(STATION_MASTER_ST,SM.getMasterName());
        contentValues.put(STATION_CITY_ST,SM.getCity());

        long result = sqLiteDatabase.insert(STATION_TABLE_NAME, null, contentValues);

        return result;
    }

    public ArrayList<StationModel> getAllStationDetails(){

        ArrayList<StationModel> stations = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String GET_DATA_QUERY = " SELECT * FROM " + STATION_TABLE_NAME;

        Cursor cursor = null;

        cursor = sqLiteDatabase.rawQuery(GET_DATA_QUERY, null);

        if( cursor.moveToFirst()){

            do{
                //row data
                int stationID = cursor.getInt(0);
                String stationName = cursor.getString(1);
                String masterName = cursor.getString(2);
                String cityName = cursor.getString(3);

                StationModel SM = new StationModel(stationID, stationName, masterName,cityName);
                stations.add(SM);

            }while(cursor.moveToNext());

        }

        return stations;
    }

    public int updateStationTable(StationModel SM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STATION_NAME_ST,SM.getStationName());
        contentValues.put(STATION_MASTER_ST,SM.getMasterName());
        contentValues.put(STATION_CITY_ST,SM.getCity());

        int result = sqLiteDatabase.update(STATION_TABLE_NAME, contentValues, "Station_ID=?", new String[]{String.valueOf(SM.getStationID())});

        return result;

    }

    public int deleteStations(int stationID) {

        SQLiteDatabase DBHelper = getWritableDatabase();

        int result = DBHelper.delete(STATION_TABLE_NAME, "Station_ID=?",new String[]{String.valueOf(stationID)});

        return result;
    }

    //Ticket Function-------------------------------------------------------------------------------

    //Insert
    public long addTicket(TicketModel ticketM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Ticket_Date", ticketM.getDate());
        contentValues.put("Ticket_Time", ticketM.getTime());
        contentValues.put("Ticket_Class", ticketM.getClassType());
        contentValues.put("Ticket_From", ticketM.getFrom());
        contentValues.put("Ticket_To", ticketM.getTo());
        contentValues.put("Ticket_Price", ticketM.getPrice());

        long result = sqLiteDatabase.insert(TICKET_TABLE_NAME, null, contentValues);
        return  result;
    }

    //Display TTicket Details
    public  ArrayList<TicketModel> getAllTickets(){

        ArrayList<TicketModel> ticketDetails = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String GET_DATA_QUERY = " SELECT * FROM " + TICKET_TABLE_NAME;

        Cursor cursor = null;

        cursor = sqLiteDatabase.rawQuery(GET_DATA_QUERY, null);

        if(cursor.moveToFirst()) {
            do{
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String time = cursor.getString(2);
                String classType = cursor.getString(3);
                String from = cursor.getString(4);
                String to = cursor.getString(5);
                String price = cursor.getString(6);

                TicketModel ticketM = new TicketModel(id, date, time, classType, from, to, price);
                ticketDetails.add(ticketM);

            }while (cursor.moveToNext());
        }
        return ticketDetails;
    }

    //Update Ticket
    public int updateTicket(TicketModel ticketM) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Ticket_Date", ticketM.getDate());
        contentValues.put("Ticket_Time", ticketM.getTime());
        contentValues.put("Ticket_Class", ticketM.getClassType());
        contentValues.put("Ticket_From", ticketM.getFrom());
        contentValues.put("Ticket_To", ticketM.getTo());
        contentValues.put("Ticket_Price", ticketM.getPrice());


        int result = sqLiteDatabase.update(TICKET_TABLE_NAME, contentValues, "Ticket_ID=?" ,new String[]{String.valueOf(ticketM.getId())});

        return result;
    }

    public int deleteTickets(int id) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.delete(TICKET_TABLE_NAME, "Ticket_ID=?",new String[]{ String.valueOf(id)});
    }
}
