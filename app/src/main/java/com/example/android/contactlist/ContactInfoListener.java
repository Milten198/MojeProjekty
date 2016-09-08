package com.example.android.contactlist;

public interface ContactInfoListener {
    void onFinishUserAddDialog(SingleContactInfo singleContactInfo);
    void onFinishUserRemoveDialog(SingleContactInfo singleContactInfo);
}
