package com.example.android.contactlist;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter;

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
}
