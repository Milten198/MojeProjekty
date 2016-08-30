package com.example.android.contactlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddDialogFragment extends DialogFragment {

    EditText mUserName, mUserSurname, mUserEmail, mUserAge;
    Button confirmButton, cancelButton;
    List<ContactInfoList> contactInfoLists = Collections.EMPTY_LIST;

    public interface ContactInfoListener {
        void onFinishUserDialog(List<ContactInfoList> list);
    }

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
                ContactInfoListener contactInfoListener = (ContactInfoListener) getActivity();

                contactInfoLists = new ArrayList<ContactInfoList>();
                ContactInfoList singleContactInfo = new ContactInfoList();

                singleContactInfo.name = mUserName.getText().toString();
                singleContactInfo.surname = mUserSurname.getText().toString();
                singleContactInfo.email = mUserEmail.getText().toString();
                singleContactInfo.age = mUserAge.getText().toString();

                contactInfoLists.add(singleContactInfo);


                contactInfoListener.onFinishUserDialog(contactInfoLists);
                dismiss();
            }
        });
        return view;
    }

}
