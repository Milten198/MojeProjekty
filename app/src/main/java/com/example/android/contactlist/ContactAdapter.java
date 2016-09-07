package com.example.android.contactlist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<SingleContactInfo> allElements = Collections.EMPTY_LIST;
    LayoutInflater inflater;

    public ContactAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        SingleContactInfo currentRow = allElements.get(position);
        holder.nameTextView.setText(currentRow.name);
        holder.surnameTextView.setText(currentRow.surname);
        holder.emailTextView.setText(currentRow.email);
        holder.ageTextView.setText(currentRow.age);
    }

    @Override
    public int getItemCount() {
        return allElements.size();
    }

    public void insertRecord(SingleContactInfo singleContactInfo) {
        allElements.add(singleContactInfo);
        notifyItemChanged(allElements.size() - 1);
    }

    public void setData(List<SingleContactInfo> allElements){
        this.allElements = allElements;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, surnameTextView, emailTextView, ageTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.userName);
            surnameTextView = (TextView) itemView.findViewById(R.id.userSurname);
            emailTextView = (TextView) itemView.findViewById(R.id.userEmail);
            ageTextView = (TextView) itemView.findViewById(R.id.userAge);
        }
    }
}
