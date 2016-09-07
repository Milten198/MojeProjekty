package com.example.android.contactlist.Data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class ContactProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private ContactDbHelper mOpenHelper;

    static final int CONTACT = 100;
    static final int CONTACT_WITH_ID = 200;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ContactContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, ContactContract.PATH_CONTACTS, CONTACT);
        matcher.addURI(authority, ContactContract.PATH_CONTACTS + "/#", CONTACT_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new ContactDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor retCursor;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CONTACT: {
                retCursor = mOpenHelper.getReadableDatabase().query(ContactContract.ContactEntry.TABLE_NAME,
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);
                return retCursor;
            }
            case CONTACT_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(ContactContract.ContactEntry.TABLE_NAME,
                        strings,
                        ContactContract.ContactEntry._ID + " =? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        s1);
                return retCursor;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CONTACT:
                return ContactContract.ContactEntry.CONTENT_DIR_TYPE;
            case CONTACT_WITH_ID:
                return ContactContract.ContactEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case CONTACT: {
                long _id = db.insert(ContactContract.ContactEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ContactContract.ContactEntry.buildContactUri(_id);
                } else {
                    throw new android.database.SQLException("Faild to insert row into: " + uri);
                }
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numDeleted;
        switch (sUriMatcher.match(uri)) {
            case CONTACT: {
                numDeleted = db.delete(ContactContract.ContactEntry.TABLE_NAME,
                        s,
                        strings);
                break;
            }
            case CONTACT_WITH_ID: {
                numDeleted = db.delete(ContactContract.ContactEntry.TABLE_NAME,
                        ContactContract.ContactEntry._ID + " =? ",
                        new String[] {String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return numDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
