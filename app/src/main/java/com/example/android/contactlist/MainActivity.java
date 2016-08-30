package com.example.android.contactlist;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddDialogFragment.ContactInfoListener {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    AddDialogFragment editNameDialogFragment = new AddDialogFragment();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        FragmentManager fm = getSupportFragmentManager();
        editNameDialogFragment = new AddDialogFragment();
        editNameDialogFragment.show(fm, "das");
    }

    public void removeContact(View view) {
    }

    @Override
    public void onFinishUserDialog(List<ContactInfoList> infoLists) {
        ContactInfoList singleContact = new ContactInfoList();
        singleContact = infoLists.get(0);
        Toast.makeText(this, singleContact.name, Toast.LENGTH_LONG).show();
    }
}
