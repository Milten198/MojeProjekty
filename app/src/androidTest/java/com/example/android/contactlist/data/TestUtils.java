package com.example.android.contactlist.data;


import android.content.ContentValues;

import com.example.android.contactlist.Data.ContactContract.ContactEntry;

public class TestUtils {
    static ContentValues loadData() {

        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_USER_NAME, "John");
        values.put(ContactEntry.COLUMN_USER_SURNAME, "Snow");
        values.put(ContactEntry.COLUMN_USER_EMAIL, "johnsnow@gmail.com");
        values.put(ContactEntry.COLUMN_USER_AGE, "25");
        return values;
    }
}
