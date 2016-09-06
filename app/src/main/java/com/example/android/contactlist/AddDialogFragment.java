package com.example.android.contactlist;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.contactlist.Data.ContactContract;
import com.example.android.contactlist.Data.ContactDbHelper;

public class AddDialogFragment extends DialogFragment {

    EditText mUserName, mUserSurname, mUserEmail, mUserAge;
    Button confirmButton, cancelButton;
    ContactDbHelper dbHelper;
    String tableName = ContactContract.ContactEntry.TABLE_NAME;

    public AddDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact_fragment, container, false);

        mUserName = (EditText) view.findViewById(R.id.nameEditText);
        mUserSurname = (EditText) view.findViewById(R.id.surnameEditText);
        mUserEmail = (EditText) view.findViewById(R.id.emailEditText);
        mUserAge = (EditText) view.findViewById(R.id.ageEditText);

        dbHelper = new ContactDbHelper(getContext());
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        confirmButton = (Button) view.findViewById(R.id.confirmButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                ContactInfoListener contactInfoListener = (ContactInfoListener) getActivity();

                String name = mUserName.getText().toString();
                String surname = mUserSurname.getText().toString();
                String email = mUserEmail.getText().toString();
                String age = mUserAge.getText().toString();

                values.put("name", name);
                values.put("surname", surname);
                values.put("email", email);
                values.put("age", age);

                db.insert(tableName, null, values);
                contactInfoListener.onFinishUserDialog(true);
                dismiss();

            }
        });
        return view;
    }

}
