package com.example.android.contactlist.Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContactContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.contactlist.Data";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CONTACTS = "contacts";

    public static final class ContactEntry implements BaseColumns {

        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_SURNAME = "surname";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_AGE = "age";
    }
}
