package com.example.android.contactlist;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new ContactDbHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this, loadMockData());
        recyclerView.setAdapter(adapter);
    }


    public List<SingleRowRepresentation> loadMockData() {
        Resources resources = getResources();
        List<SingleRowRepresentation> allElements = new ArrayList<>();
        int image = R.drawable.avatar;
        String[] names = resources.getStringArray(R.array.names);
        String[] surnames = resources.getStringArray(R.array.surnames);
        String[] emails = resources.getStringArray(R.array.emails);
        String[] age = resources.getStringArray(R.array.age);
        String[] ids = resources.getStringArray(R.array.id);

        for (int i = 0; i < 10; i++) {
            SingleRowRepresentation singleRow = new SingleRowRepresentation();
            singleRow.name = names[i];
            singleRow.surname = surnames[i];
            singleRow.email = emails[i];
            singleRow.age = age[i];
            singleRow.id = ids[i];
            singleRow.image = image;

            allElements.add(singleRow);
        }

        return allElements;
    }

    public void addContact(View view) {
        AddDialogFragment addDialogFragment = new AddDialogFragment();
        addDialogFragment.show(fm, "add");
    }

    public void removeContact(View view) {
        RemoveDialogFragment removeDialogFragment = new RemoveDialogFragment();
        removeDialogFragment.show(fm, "remove");
    }

    @Override
    public void onFinishUserDialog(List<SingleContactInfo> infoLists) {

        contactInfos = new ArrayList<>();
        singleContact = infoLists.get(0);
        contactInfos.add(singleContact);

        Toast.makeText(this, singleContact.name, Toast.LENGTH_LONG).show();
    }

    public void confirmContact(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SingleContactInfo singleContactInfo = new SingleContactInfo();
        String name = singleContactInfo.name;
        String surname = singleContactInfo.surname;
        String email = singleContactInfo.email;
        String age = singleContactInfo.age;

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("surname", surname);
        values.put("email", email);
        values.put("age", age);

        db.insert(ContactContract.ContactEntry.TABLE_NAME, null, values);
        checkDatabase();
    }

    public void checkDatabase() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(ContactContract.ContactEntry.TABLE_NAME, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_USER_SURNAME);
            String surnameA = cursor.getString(index);
            buffer.append(surnameA);
        }
        System.out.print(buffer);
    }
}
