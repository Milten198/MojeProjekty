package com.example.android.contactlist.data;


import android.content.ContentValues;

public class TestUtilities {

    static ContentValues createContactsContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "John");
        contentValues.put("surname", "Smith");
        contentValues.put("email", "johnsmith@gmail.com");
        contentValues.put("age", 45);

        return contentValues;
    }
}
