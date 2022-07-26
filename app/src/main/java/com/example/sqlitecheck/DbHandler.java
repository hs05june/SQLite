package com.example.sqlitecheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context, Parameters.DB_NAME, null, Parameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Parameters.TABLE_NAME + " ("
                + Parameters.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Parameters.NAME_COL + " TEXT,"
                + Parameters.TRACKS_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewEmployee(Employee emp) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Parameters.NAME_COL, emp.getName());
        values.put(Parameters.TRACKS_COL, emp.getIncrement());
        Log.e("add","done");
        db.insert(Parameters.TABLE_NAME, null, values);
        db.close();
    }

    public List<Employee> display(){
        ArrayList<Employee> ans = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Parameters.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Employee emp = new Employee();
                emp.setId(cursor.getInt(0));
                emp.setName(cursor.getString(1));
                emp.setIncrement(cursor.getString(2));
                ans.add(emp);
            }while(cursor.moveToNext());
        }
        return ans;
    }

    public Employee getEmployee(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Employee employee = new Employee();
        Cursor cursor = db.query(Parameters.TABLE_NAME,new String[]{Parameters.ID_COL,Parameters.NAME_COL,Parameters.TRACKS_COL},Parameters.ID_COL+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor.moveToFirst()){
        employee.setId(cursor.getInt(0));
        employee.setName(cursor.getString(1));
        employee.setIncrement(cursor.getString(2));}
        return employee;
    }

    public int update(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Parameters.NAME_COL,employee.getName());
        values.put(Parameters.TRACKS_COL,employee.getIncrement());
        return db.update(Parameters.TABLE_NAME,values,Parameters.ID_COL+"=?",new String[]{String.valueOf(employee.getId())});
    }

    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME,Parameters.ID_COL+"=?",new String[]{String.valueOf(id)});
    }

    public void deleteByName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME,Parameters.NAME_COL+"=?",new String[]{String.valueOf(name)});
    }

    public int getTotalNumber(){
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Parameters.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        return  cursor.getCount();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Parameters.TABLE_NAME);
        onCreate(db);
    }
}
