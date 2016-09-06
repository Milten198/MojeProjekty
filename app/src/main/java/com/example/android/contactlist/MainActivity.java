package com.example.android.contactlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.contactlist.Data.ContactContract;
import com.example.android.contactlist.Data.ContactDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactInfoListener {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    FragmentManager fm = getSupportFragmentManager();
    SingleContactInfo singleContact;
    List<SingleContactInfo> contactInfos;
    ContactDbHelper dbHelper;
    String tableName = ContactContract.ContactEntry.TABLE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactInfos = new ArrayList<>();
        dbHelper = new ContactDbHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this, loadData());
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            SingleContactInfo contactInfo = new SingleContactInfo();
            int userNameId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_NAME);
            String userName = cursor.getString(userNameId);
            int userSurnameId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_SURNAME);
            String userSurame = cursor.getString(userSurnameId);
            int userEmailId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_EMAIL);
            String userEmail = cursor.getString(userEmailId);
            int userAgeId = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_AGE);
            String userAge = cursor.getString(userAgeId);

            contactInfo.name = userName;
            contactInfo.surname = userSurame;
            contactInfo.email = userEmail;
            contactInfo.age = userAge;
            contactInfos.add(contactInfo);
        }
        return contactInfos;
    }

    @Override
    public void onFinishUserDialog(boolean hasChanged) {
        if (hasChanged) {
            Toast.makeText(this, "True", Toast.LENGTH_LONG).show();
        }
    }
}
