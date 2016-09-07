package com.example.android.contactlist.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.contactlist.Data.ContactContract.ContactEntry;

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contact.db";
    static final int DATABASE_VERSION = 1;

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE " + ContactEntry.TABLE_NAME + " (" +
                ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_USER_SURNAME + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_USER_AGE + " INTEGER NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
