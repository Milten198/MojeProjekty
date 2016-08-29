package com.example.android.contactlist.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.android.contactlist.Data.ContactDbHelper;

import static com.example.android.contactlist.Data.ContactContract.*;
import static com.example.android.contactlist.Data.ContactContract.ContactEntry.TABLE_NAME;

public class TestDb extends AndroidTestCase {

    void deleteTheDatabase() {
        mContext.deleteDatabase(ContactDbHelper.DATABASE_NAME);
    }

    public void setUp() {
        deleteTheDatabase();
    }

    public void testCreateDb() throws Throwable{
        ContactDbHelper dbHelper = new ContactDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        assertTrue("DB does not exists", db.isOpen());
    }

    public void testInsertRow() throws Throwable {
        ContactDbHelper dbHelper = new ContactDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        assertTrue("Now rows in DB", insertRow(db) != -1);
    }

    public void testIsDataCorrect() throws Throwable {
        ContactDbHelper dbHelper = new ContactDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        insertRow(db);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        int nameId = cursor.getColumnIndex(ContactEntry.COLUMN_USER_NAME);
        String name = cursor.getString(nameId);
        assertTrue("Wrong name", name.equals("John"));
        Log.v("LogName", name);
    }

    public void testDeleteRow() {
        ContactDbHelper dbHelper = new ContactDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        insertRow(db);
        int deletedRows = db.delete(TABLE_NAME, ContactEntry.COLUMN_USER_NAME + " =? ", new String[]{"John"});
        assertTrue("No rows deleted", deletedRows != -1);
    }

    public long insertRow(SQLiteDatabase db) {
        ContentValues values = TestUtilities.createContactsContentValues();
        long rowId = db.insert(TABLE_NAME, null, values);
        return rowId;
    }

}
