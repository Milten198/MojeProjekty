package com.example.android.contactlist;

import java.util.List;

public interface ContactInfoListener {
    void onFinishUserDialog(List<SingleContactInfo> list);
}
