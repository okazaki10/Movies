package com.example.movies.ViewModel

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Let's start by creating our database CRUD helper class
 * based on the SQLiteHelper.
 */
class FavouriteDB(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    /**
     * Let's create a companion object to hold our static fields.
     * A Companion object is an object that is common to all instances of a given
     * class.
     */
    companion object {
        val DATABASE_NAME = "favourite.db"
        val TABLE_NAME = "favourite_table"
        val COL_1 = "ID"
        val COL_2 = "MOVIE_ID"
        val COL_3 = "TITLE"
        val COL_4 = "IMAGE"
        val COL_5 = "ORIGINAL_TITLE"
        val COL_6 = "OVERVIEW"
    }

    /**
     * Our onCreate() method.
     * Called when the database is created for the first time. This is
     * where the creation of tables and the initial population of the tables
     * should happen.
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,MOVIE_ID TEXT, TITLE TEXT,IMAGE TEXT,ORIGINAL_TITLE TEXT,OVERVIEW TEXT)")
    }

    /**
     * Let's create Our onUpgrade method
     * Called when the database needs to be upgraded. The implementation should
     * use this method to drop tables, add tables, or do anything else it needs
     * to upgrade to the new schema version.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /**
     * Let's create our insertData() method.
     * It Will insert data to SQLIte database.
     */
    fun insertData(movie_id: String, title: String, image: String,originaltitle:String,overview:String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, movie_id)
        contentValues.put(COL_3, title)
        contentValues.put(COL_4, image)
        contentValues.put(COL_5, originaltitle)
        contentValues.put(COL_6, overview)
        db.insert(TABLE_NAME, null, contentValues)
    }

    /**
     * Let's create  a method to update a row with new field values.
     */
    fun updateData(id: String, name: String, surname: String, marks: String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, surname)
        contentValues.put(COL_4, marks)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    /**
     * Let's create a function to delete a given row based on the id.
     */
    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"MOVIE_ID = ?", arrayOf(id))
    }

    /**
     * The below getter property will return a Cursor containing our dataset.
     */
    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res
        }

    fun selectData(id:String): Cursor
    {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE MOVIE_ID = ?", arrayOf(id),null)
        return res
    }


}
//end