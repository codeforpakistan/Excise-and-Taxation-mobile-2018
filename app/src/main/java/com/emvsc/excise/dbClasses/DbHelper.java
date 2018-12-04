package com.emvsc.excise.dbClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shahzaib on 01-Aug-18.
 */

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MAKE_TABLE = "CREATE TABLE " +DbConstants.TABLE_MAKE+
                "("+DbConstants.MAKE_ID+" VARCHAR(11)," +
                ""+DbConstants.MAKE_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_MAKE_TABLE);
        Log.e("DB", "make Table Created !");

        String CREATE_MODEL_TABLE = "CREATE TABLE " +DbConstants.TABLE_MODEL+
                "("+DbConstants.MODEL_PARENT_ID+" VARCHAR(11)," +
                ""+DbConstants.MODEL_ID+" VARCHAR(200),"+
                ""+DbConstants.MODEL_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_MODEL_TABLE);
        Log.e("DB", "model Table Created !");

        String CREATE_ACCESSORIES_TABLE = "CREATE TABLE " +DbConstants.TABLE_ACCESSORIES+
                "("+DbConstants.ACCESSORIES_ID+" VARCHAR(11)," +
                ""+DbConstants.ACCESSORIES_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_ACCESSORIES_TABLE);
        Log.e("DB", "accessories Table Created" );

        String CREATE_DISTRICT_TABLE = "CREATE TABLE " +DbConstants.TABLE_DISTRICT+
                "("+DbConstants.DISTRICT_ID+" VARCHAR(11)," +
                ""+DbConstants.DISTRICT_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_DISTRICT_TABLE);
        Log.e("DB", "district Table Created" );

        String CREATE_SEIZE_CAT_TABLE = "CREATE TABLE " +DbConstants.TABLE_SEIZE_CAT+
                "("+DbConstants.SEIZ_ID+" VARCHAR(11)," +
                ""+DbConstants.SEIZ_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_SEIZE_CAT_TABLE);
        Log.e("DB", "seize cat Table Created" );

        String CREATE_MODEL_YEAR_TABLE = "CREATE TABLE " +DbConstants.TABLE_MODEL_YEAR+
                "("+DbConstants.MODEL_YEAR_ID+" VARCHAR(11)," +
                ""+DbConstants.MODEL_YEAR_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_MODEL_YEAR_TABLE);
        Log.e("DB", "model year cat Table Created" );

        String CREATE_COLOR_TABLE = "CREATE TABLE " +DbConstants.TABLE_COLOR+
                "("+DbConstants.VEHICLE_COLOR_ID+" VARCHAR(11)," +
                ""+DbConstants.VEHICLE_COLOR_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_COLOR_TABLE);
        Log.e("DB", "color Table Created" );

        String CREATE_WHEEL_TABLE = "CREATE TABLE " +DbConstants.TABLE_WHEELS+
                "("+DbConstants.VEHICLE_WHEEL_ID+" VARCHAR(11)," +
                ""+DbConstants.VEHICLE_WHEEL_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_WHEEL_TABLE);
        Log.e("DB", "wheel Table Created" );

        String CREATE_BODY_TABLE = "CREATE TABLE " +DbConstants.TABLE_BODY_BUILD+
                "("+DbConstants.VEHICLE_BODY_ID+" VARCHAR(11)," +
                ""+DbConstants.VEHICLE_BODY_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_BODY_TABLE);
        Log.e("DB", "body Table Created" );

        String CREATE_REG_DISTRICT_TABLE = "CREATE TABLE " +DbConstants.TABLE_REG_DISTRICT+
                "("+DbConstants.REG_DISTRICT_ID+" VARCHAR(11)," +
                ""+DbConstants.REG_DISTRICT_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_REG_DISTRICT_TABLE);
        Log.e("DB", "Reg District Table Created" );



        String CREATE_SEIZE_TABLE = "CREATE TABLE " +DbConstants.TABLE_SEIZE+
                "("+DbConstants.SEIZE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL  UNIQUE," +
                ""+DbConstants.SEIZE_FORM_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_CAT_ID+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DISTRICT_ID+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DRIVER_NAME+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DRIVER_CNIC+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DRIVER_MOB_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DRIVER_ADDRESS+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_OWNER_NAME+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_OWNER_CNIC+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_OWNER_MOB_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_SQUAD_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_CHASIS_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_ENGINE_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_REG_NO+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DATE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_TIME+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_MAKE_ID+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_MODEL_ID+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_MODEL_YEAR+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_TYPE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_BODY_BUILD+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_COLOR+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_TRANSMISSION+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_ASSEMBELY+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_WEEHLES+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_ENGINE_TYPE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_ENGINE_CAPICITY+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_VEHICLE_MILEAGE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_DESCRIPTION+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_CURRENT_LATITUDE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_CURRENT_LONGITUDE+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_ACCESSORIES+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_USER_ID+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE1+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE2+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE3+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE4+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE5+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE6+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE7+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_IMAGE8+" VARCHAR(200)," +
                ""+DbConstants.SEIZE_CAT_NAME+" VARCHAR(200));";
        sqLiteDatabase.execSQL(CREATE_SEIZE_TABLE);
        Log.e("DB", "seize Table Created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query1;
        query1 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_MAKE;
        sqLiteDatabase.execSQL(query1);

        String query2;
        query2 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_MODEL;
        sqLiteDatabase.execSQL(query2);

        String query3;
        query3 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_ACCESSORIES;
        sqLiteDatabase.execSQL(query3);

        onCreate(sqLiteDatabase);

        String query4;
        query4 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_SEIZE;
        sqLiteDatabase.execSQL(query4);

        String query5;
        query5 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_DISTRICT;
        sqLiteDatabase.execSQL(query5);

        String query6;
        query6 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_SEIZE_CAT;
        sqLiteDatabase.execSQL(query6);

        String query7;
        query7 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_COLOR;
        sqLiteDatabase.execSQL(query7);

        String query8;
        query8 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_WHEELS;
        sqLiteDatabase.execSQL(query8);

        String query9;
        query9 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_MODEL_YEAR;
        sqLiteDatabase.execSQL(query9);

        String query10;
        query10 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_BODY_BUILD;
        sqLiteDatabase.execSQL(query10);

        String query11;
        query11 = "DROP TABLE IF EXISTS " +DbConstants.TABLE_REG_DISTRICT;
        sqLiteDatabase.execSQL(query11);


        onCreate(sqLiteDatabase);
    }

    public void addDistricts(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.DISTRICT_ID, id);
            values.put(DbConstants.DISTRICT_NAME, name);
            database.insert(DbConstants.TABLE_DISTRICT, null, values);
            database.close();
            Log.e("shah", "districts Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addBody(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.VEHICLE_BODY_ID, id);
            values.put(DbConstants.VEHICLE_BODY_NAME, name);
            database.insert(DbConstants.TABLE_BODY_BUILD, null, values);
            database.close();
            Log.e("shah", "body Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addSeizeCat(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.SEIZ_ID, id);
            values.put(DbConstants.SEIZ_NAME, name);
            database.insert(DbConstants.TABLE_SEIZE_CAT, null, values);
            database.close();
            Log.e("shah", "seize Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addVehicleColor(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.VEHICLE_COLOR_ID, id);
            values.put(DbConstants.VEHICLE_COLOR_NAME, name);
            database.insert(DbConstants.TABLE_COLOR, null, values);
            database.close();
            Log.e("shah", "color Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addVehicleModelYear(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.MODEL_YEAR_ID, id);
            values.put(DbConstants.MODEL_YEAR_NAME, name);
            database.insert(DbConstants.TABLE_MODEL_YEAR, null, values);
            database.close();
            Log.e("shah", "model year Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addVehicleWheels(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.VEHICLE_WHEEL_ID, id);
            values.put(DbConstants.VEHICLE_WHEEL_NAME, name);
            database.insert(DbConstants.TABLE_WHEELS, null, values);
            database.close();
            Log.e("shah", "wheels Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addAccessories(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.ACCESSORIES_ID, id);
            values.put(DbConstants.ACCESSORIES_NAME, name);
            database.insert(DbConstants.TABLE_ACCESSORIES, null, values);
            database.close();
            Log.e("shah", "accessories Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addRegDistrict(String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.REG_DISTRICT_ID, id);
            values.put(DbConstants.REG_DISTRICT_NAME, name);
            database.insert(DbConstants.TABLE_REG_DISTRICT, null, values);
            database.close();
            Log.e("shah", "Reg Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addMake(String id, String make){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.MAKE_ID, id);
            values.put(DbConstants.MAKE_NAME, make);
            database.insert(DbConstants.TABLE_MAKE, null, values);
            database.close();
            Log.e("shah", "Make Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public void addModel(String parent_id, String id, String name){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.MODEL_PARENT_ID, parent_id);
            values.put(DbConstants.MODEL_ID, id);
            values.put(DbConstants.MODEL_NAME, name);
            database.insert(DbConstants.TABLE_MODEL, null, values);
            database.close();
            Log.e("shah", "Model Data Stored Successfully");

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }
    public void addSeizeVehicle(Map<String, String> dataMap){
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.SEIZE_FORM_NO, dataMap.get(DbConstants.SEIZE_FORM_NO));
            values.put(DbConstants.SEIZE_CAT_ID, dataMap.get(DbConstants.SEIZE_CAT_ID));
            values.put(DbConstants.SEIZE_CAT_NAME, dataMap.get(DbConstants.SEIZE_CAT_NAME));
            values.put(DbConstants.SEIZE_DISTRICT_ID, dataMap.get(DbConstants.SEIZE_DISTRICT_ID));
            values.put(DbConstants.SEIZE_DRIVER_NAME, dataMap.get(DbConstants.SEIZE_DRIVER_NAME));
            values.put(DbConstants.SEIZE_DRIVER_CNIC, dataMap.get(DbConstants.SEIZE_DRIVER_CNIC));
            values.put(DbConstants.SEIZE_DRIVER_MOB_NO, dataMap.get(DbConstants.SEIZE_DRIVER_MOB_NO));
            values.put(DbConstants.SEIZE_DRIVER_ADDRESS, dataMap.get(DbConstants.SEIZE_DRIVER_ADDRESS));
            values.put(DbConstants.SEIZE_VEHICLE_OWNER_NAME, dataMap.get(DbConstants.SEIZE_VEHICLE_OWNER_NAME));
            values.put(DbConstants.SEIZE_VEHICLE_OWNER_CNIC, dataMap.get(DbConstants.SEIZE_VEHICLE_OWNER_CNIC));
            values.put(DbConstants.SEIZE_VEHICLE_OWNER_MOB_NO, dataMap.get(DbConstants.SEIZE_VEHICLE_OWNER_MOB_NO));
            values.put(DbConstants.SEIZE_SQUAD_NO, dataMap.get(DbConstants.SEIZE_SQUAD_NO));
            values.put(DbConstants.SEIZE_CHASIS_NO, dataMap.get(DbConstants.SEIZE_CHASIS_NO));
            values.put(DbConstants.SEIZE_ENGINE_NO, dataMap.get(DbConstants.SEIZE_ENGINE_NO));
            values.put(DbConstants.SEIZE_VEHICLE_REG_NO, dataMap.get(DbConstants.SEIZE_VEHICLE_REG_NO));
            values.put(DbConstants.SEIZE_DATE, dataMap.get(DbConstants.SEIZE_DATE));
            values.put(DbConstants.SEIZE_TIME, dataMap.get(DbConstants.SEIZE_TIME));
            values.put(DbConstants.SEIZE_MAKE_ID, dataMap.get(DbConstants.SEIZE_MAKE_ID));
            values.put(DbConstants.SEIZE_MODEL_ID, dataMap.get(DbConstants.SEIZE_MODEL_ID));
            values.put(DbConstants.SEIZE_MODEL_YEAR, dataMap.get(DbConstants.SEIZE_MODEL_YEAR));
            values.put(DbConstants.SEIZE_VEHICLE_TYPE, dataMap.get(DbConstants.SEIZE_VEHICLE_TYPE));
            values.put(DbConstants.SEIZE_BODY_BUILD, dataMap.get(DbConstants.SEIZE_BODY_BUILD));
            values.put(DbConstants.SEIZE_VEHICLE_COLOR, dataMap.get(DbConstants.SEIZE_VEHICLE_COLOR));
            values.put(DbConstants.SEIZE_VEHICLE_TRANSMISSION, dataMap.get(DbConstants.SEIZE_VEHICLE_TRANSMISSION));
            values.put(DbConstants.SEIZE_VEHICLE_ASSEMBELY, dataMap.get(DbConstants.SEIZE_VEHICLE_ASSEMBELY));
            values.put(DbConstants.SEIZE_VEHICLE_WEEHLES, dataMap.get(DbConstants.SEIZE_VEHICLE_WEEHLES));
            values.put(DbConstants.SEIZE_ENGINE_TYPE, dataMap.get(DbConstants.SEIZE_ENGINE_TYPE));
            values.put(DbConstants.SEIZE_ENGINE_CAPICITY, dataMap.get(DbConstants.SEIZE_ENGINE_CAPICITY));
            values.put(DbConstants.SEIZE_VEHICLE_MILEAGE, dataMap.get(DbConstants.SEIZE_VEHICLE_MILEAGE));
            values.put(DbConstants.SEIZE_DESCRIPTION, dataMap.get(DbConstants.SEIZE_DESCRIPTION));
            values.put(DbConstants.SEIZE_USER_ID, dataMap.get(DbConstants.SEIZE_USER_ID));
            values.put(DbConstants.SEIZE_CURRENT_LATITUDE, dataMap.get(DbConstants.SEIZE_CURRENT_LATITUDE));
            values.put(DbConstants.SEIZE_CURRENT_LONGITUDE, dataMap.get(DbConstants.SEIZE_CURRENT_LONGITUDE));
            values.put(DbConstants.SEIZE_ACCESSORIES, dataMap.get(DbConstants.SEIZE_ACCESSORIES));
            values.put(DbConstants.SEIZE_IMAGE1, dataMap.get(DbConstants.SEIZE_IMAGE1));
            values.put(DbConstants.SEIZE_IMAGE2, dataMap.get(DbConstants.SEIZE_IMAGE2));
            values.put(DbConstants.SEIZE_IMAGE3, dataMap.get(DbConstants.SEIZE_IMAGE3));
            values.put(DbConstants.SEIZE_IMAGE4, dataMap.get(DbConstants.SEIZE_IMAGE4));
            values.put(DbConstants.SEIZE_IMAGE5, dataMap.get(DbConstants.SEIZE_IMAGE5));
            values.put(DbConstants.SEIZE_IMAGE6, dataMap.get(DbConstants.SEIZE_IMAGE6));
            values.put(DbConstants.SEIZE_IMAGE7, dataMap.get(DbConstants.SEIZE_IMAGE7));
            values.put(DbConstants.SEIZE_IMAGE8, dataMap.get(DbConstants.SEIZE_IMAGE8));
            database.insert(DbConstants.TABLE_SEIZE, null, values);
            database.close();
            Log.e("DB", "Seize Data inserted successfully" );
            Log.e("DB", ""+values );

        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    public ArrayList<HashMap<String, String>> getSizeData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_SEIZE;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.SEIZ_ID,cursor.getString(0));
                    map.put(DbConstants.SEIZE_FORM_NO,cursor.getString(1));
                    map.put(DbConstants.SEIZE_CAT_ID,cursor.getString(2));
                    map.put(DbConstants.SEIZE_DISTRICT_ID,cursor.getString(3));
                    map.put(DbConstants.SEIZE_DRIVER_NAME,cursor.getString(4));
                    map.put(DbConstants.SEIZE_DRIVER_CNIC,cursor.getString(5));
                    map.put(DbConstants.SEIZE_DRIVER_MOB_NO,cursor.getString(6));
                    map.put(DbConstants.SEIZE_DRIVER_ADDRESS,cursor.getString(7));
                    map.put(DbConstants.SEIZE_VEHICLE_OWNER_NAME,cursor.getString(8));
                    map.put(DbConstants.SEIZE_VEHICLE_OWNER_CNIC,cursor.getString(9));
                    map.put(DbConstants.SEIZE_VEHICLE_OWNER_MOB_NO,cursor.getString(10));
                    map.put(DbConstants.SEIZE_SQUAD_NO,cursor.getString(11));
                    map.put(DbConstants.SEIZE_CHASIS_NO,cursor.getString(12));
                    map.put(DbConstants.SEIZE_ENGINE_NO,cursor.getString(13));
                    map.put(DbConstants.SEIZE_VEHICLE_REG_NO,cursor.getString(14));
                    map.put(DbConstants.SEIZE_DATE,cursor.getString(15));
                    map.put(DbConstants.SEIZE_TIME,cursor.getString(16));
                    map.put(DbConstants.SEIZE_MAKE_ID,cursor.getString(17));
                    map.put(DbConstants.SEIZE_MODEL_ID,cursor.getString(18));
                    map.put(DbConstants.SEIZE_MODEL_YEAR,cursor.getString(19));
                    map.put(DbConstants.SEIZE_VEHICLE_TYPE,cursor.getString(20));
                    map.put(DbConstants.SEIZE_BODY_BUILD,cursor.getString(21));
                    map.put(DbConstants.SEIZE_VEHICLE_COLOR,cursor.getString(22));
                    map.put(DbConstants.SEIZE_VEHICLE_TRANSMISSION,cursor.getString(23));
                    map.put(DbConstants.SEIZE_VEHICLE_ASSEMBELY,cursor.getString(24));
                    map.put(DbConstants.SEIZE_VEHICLE_WEEHLES,cursor.getString(25));
                    map.put(DbConstants.SEIZE_ENGINE_TYPE,cursor.getString(26));
                    map.put(DbConstants.SEIZE_ENGINE_CAPICITY,cursor.getString(27));
                    map.put(DbConstants.SEIZE_VEHICLE_MILEAGE,cursor.getString(28));
                    map.put(DbConstants.SEIZE_DESCRIPTION,cursor.getString(29));
                    map.put(DbConstants.SEIZE_CURRENT_LATITUDE,cursor.getString(30));
                    map.put(DbConstants.SEIZE_CURRENT_LONGITUDE,cursor.getString(31));
                    map.put(DbConstants.SEIZE_ACCESSORIES,cursor.getString(32));
                    map.put(DbConstants.SEIZE_USER_ID,cursor.getString(33));
                    map.put(DbConstants.SEIZE_IMAGE1,cursor.getString(34));
                    map.put(DbConstants.SEIZE_IMAGE2,cursor.getString(35));
                    map.put(DbConstants.SEIZE_IMAGE3,cursor.getString(36));
                    map.put(DbConstants.SEIZE_IMAGE4,cursor.getString(37));
                    map.put(DbConstants.SEIZE_IMAGE5,cursor.getString(38));
                    map.put(DbConstants.SEIZE_IMAGE6,cursor.getString(39));
                    map.put(DbConstants.SEIZE_IMAGE7,cursor.getString(40));
                    map.put(DbConstants.SEIZE_IMAGE8,cursor.getString(41));
                    map.put(DbConstants.SEIZE_CAT_NAME,cursor.getString(42));

                    wordList.add(map);
                } while (cursor.moveToNext());
                Log.e("wordList", "Sql Data: "+wordList.toString());

            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getSeizeCatData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_SEIZE_CAT;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.SEIZ_ID,cursor.getString(0));
                    map.put(DbConstants.SEIZ_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getRegDistrictData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_REG_DISTRICT;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.REG_DISTRICT_ID,cursor.getString(0));
                    map.put(DbConstants.REG_DISTRICT_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getBodyBuild() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_BODY_BUILD;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.VEHICLE_BODY_ID,cursor.getString(0));
                    map.put(DbConstants.VEHICLE_BODY_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getModelYearData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_MODEL_YEAR;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.MODEL_YEAR_ID,cursor.getString(0));
                    map.put(DbConstants.MODEL_YEAR_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getColorData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_COLOR;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.VEHICLE_COLOR_ID,cursor.getString(0));
                    map.put(DbConstants.VEHICLE_COLOR_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getWheels() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_WHEELS;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.VEHICLE_WHEEL_ID,cursor.getString(0));
                    map.put(DbConstants.VEHICLE_WHEEL_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getAccessoriesData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_ACCESSORIES;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.ACCESSORIES_ID,cursor.getString(0));
                    map.put(DbConstants.ACCESSORIES_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getMakeData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_MAKE;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.MAKE_ID,cursor.getString(0));
                    map.put(DbConstants.MAKE_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getDistrictData() {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_DISTRICT;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.DISTRICT_ID,cursor.getString(0));
                    map.put(DbConstants.DISTRICT_NAME, cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){
            Log.e("Exception", e.toString() );
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getSpecificModelData(String parent_id) {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_MODEL+
                    " WHERE "+DbConstants.MODEL_PARENT_ID+"='"+parent_id+"'";
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.MODEL_PARENT_ID,cursor.getString(0));
                    map.put(DbConstants.MODEL_ID, cursor.getString(1));
                    map.put(DbConstants.MODEL_NAME, cursor.getString(2));
                    Log.e("parent_id",cursor.getString(0));
                    Log.e("model_name",cursor.getString(1));
                    Log.e("model_name",cursor.getString(2));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){

        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getSpecificDistrictData(String district_id) {
        try{
            ArrayList<HashMap<String, String>> wordList;
            wordList = new ArrayList<HashMap<String, String>>();
            String selectQuery = "SELECT *  FROM " +DbConstants.TABLE_DISTRICT+
                    " WHERE "+DbConstants.DISTRICT_ID+"='"+district_id+"'";
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(DbConstants.DISTRICT_ID,cursor.getString(0));
                    map.put(DbConstants.DISTRICT_NAME, cursor.getString(1));
                    Log.e("district_id",cursor.getString(0));
                    Log.e("district_name",cursor.getString(1));
                    wordList.add(map);
                } while (cursor.moveToNext());
            }
            return wordList;
        }catch (Exception e){

        }
        return null;
    }
    
    public void deleteAccessoriesTable() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_ACCESSORIES;
            database.execSQL(query);
            Log.e("Accessories", "Data Deleted !");
        }catch (Exception e){

        }
    }
    public void deleteMakeTable() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_MAKE;
            database.execSQL(query);
            Log.e("Make", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteModelTable() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_MODEL;
            database.execSQL(query);
            Log.e("Model", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteDistrictTable() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_DISTRICT;
            database.execSQL(query);
            Log.e("District", "Data Deleted !");
        }catch (Exception e){

        }
    }
    public void deleteSeizeData(String id) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_SEIZE + " WHERE "+DbConstants.SEIZE_ID+"='"+id+"'" ;
            database.execSQL(query);
            Log.e("Seize", "Data Deleted !");
        }catch (Exception e){

        }
    }
    public void deleteSeizeCat() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_SEIZE_CAT;
            database.execSQL(query);
            Log.e("Seize Cat", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteModelYear() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_MODEL_YEAR;
            database.execSQL(query);
            Log.e("model year", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteColor() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_COLOR;
            database.execSQL(query);
            Log.e("color", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteWheels() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_WHEELS;
            database.execSQL(query);
            Log.e("wheels", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteBodyBuild() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_BODY_BUILD;
            database.execSQL(query);
            Log.e("body", "Data Deleted !");
        }catch (Exception e){

        }
    }

    public void deleteRegDistricts() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "DELETE FROM " + DbConstants.TABLE_REG_DISTRICT;
            database.execSQL(query);
            Log.e("reg dist", "Data Deleted !");
        }catch (Exception e){

        }
    }

}
