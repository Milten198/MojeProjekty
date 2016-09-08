package com.example.android.contactlist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.contactlist.Data.ContactContract;
import com.example.android.contactlist.Data.ContactDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveDialogFragment extends DialogFragment {

    EditText mNameToRemove, mSurnameToRemove;
    Button confirmRemovingButton, cancelRemovingButton;
    List<SingleContactInfo> contactInfoList = Collections.EMPTY_LIST;
    ContactDbHelper dbHelper;
    String tableName = ContactContract.ContactEntry.TABLE_NAME;
    String userName = ContactContract.ContactEntry.COLUMN_USER_NAME;
    String userSurname = ContactContract.ContactEntry.COLUMN_USER_SURNAME;

    public RemoveDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remove_contact_fragment, container, false);
        dbHelper = new ContactDbHelper(getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        mNameToRemove = (EditText) view.findViewById(R.id.nameToRemoveEditText);
        mSurnameToRemove = (EditText) view.findViewById(R.id.surnameToRemoveEditText);

        cancelRemovingButton = (Button) view.findViewById(R.id.cancelRemovingButton);
        confirmRemovingButton = (Button) view.findViewById(R.id.confirmRemovingButton);

        cancelRemovingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        confirmRemovingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactInfoListener contactInfoListener = (ContactInfoListener) getActivity();
                SingleContactInfo singleContactInfo = new SingleContactInfo();
                singleContactInfo.name = mNameToRemove.getText().toString();
                singleContactInfo.surname = mSurnameToRemove.getText().toString();
                singleContactInfo.email = null;
                singleContactInfo.age = null;

                String[] argsToDelete = {singleContactInfo.name, singleContactInfo.surname};
                contactInfoListener.onFinishUserRemoveDialog(singleContactInfo);
                db.delete(tableName, userName + " =? AND " + userSurname + " =? ", argsToDelete);
                dismiss();
            }
        });
        return view;
    }
}
