package com.example.android.contactlist;

import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.android.contactlist.Data.ContactContract;
import com.example.android.contactlist.Data.ContactDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactInfoListener, android.app.LoaderManager.LoaderCallbacks<Cursor> {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    FragmentManager fm = getSupportFragmentManager();
    SingleContactInfo singleContact;
    List<SingleContactInfo> contactInfos;
    ContactDbHelper dbHelper;
    String tableName = ContactContract.ContactEntry.TABLE_NAME;
    int LOADER_ID = 1;
    Uri contactURI = ContactContract.ContactEntry.CONTENT_URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        //getLoaderManager().restartLoader(LOADER_ID, null, this);
        contactInfos = new ArrayList<>();
        dbHelper = new ContactDbHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this);
        adapter.setData(loadData());
        recyclerView.setAdapter(adapter);

    }

    public void addContact(View view) {
        AddDialogFragment addDialogFragment = new AddDialogFragment();
        addDialogFragment.show(fm, "add");
    }

    public void removeContact(View view) {
        RemoveDialogFragment removeDialogFragment = new RemoveDialogFragment();
        removeDialogFragment.show(fm, "remove");
    }

    public List<SingleContactInfo> loadData() {
        return contactInfos;
    }

    @Override
    public void onFinishUserAddDialog(SingleContactInfo singleContactInfo) {
        adapter.insertPerson(singleContactInfo);
    }

    @Override
    public void onFinishUserRemoveDialog(SingleContactInfo singleContactInfo) {
        adapter.removePerson(singleContactInfo);
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        CursorLoader loader = new CursorLoader(
                MainActivity.this,
                contactURI,
                null,
                null,
                null,
                null
        );
        return loader;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
       while (cursor.moveToNext()){
            SingleContactInfo info = new SingleContactInfo();
            int nameId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_NAME);
            int surnameId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_SURNAME);
            int emailId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_EMAIL);
            int ageId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_AGE);
            String name = cursor.getString(nameId);
            String surname = cursor.getString(surnameId);
            String email = cursor.getString(emailId);
            String age = cursor.getString(ageId);
            info.name = name;
            info.surname = surname;
            info.email = email;
            info.age = age;
            contactInfos.add(info);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }


}
