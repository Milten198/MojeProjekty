package com.example.android.contactlist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<SingleRowRepresentation> allElements = Collections.EMPTY_LIST;
    LayoutInflater inflater;

    public ContactAdapter(Context context, List<SingleRowRepresentation> allElements) {
        this.allElements = allElements;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        SingleRowRepresentation currentRow = allElements.get(position);
        holder.idTextView.setText(currentRow.id);
        holder.nameTextView.setText(currentRow.name);
        holder.surnameTextView.setText(currentRow.surname);
        holder.emailTextView.setText(currentRow.email);
        holder.ageTextView.setText(currentRow.age);
        holder.avatarImageView.setImageResource(currentRow.image);
    }

    @Override
    public int getItemCount() {
        return allElements.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, surnameTextView, emailTextView, ageTextView, idTextView;
        ImageView avatarImageView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            idTextView = (TextView) itemView.findViewById(R.id.userId);
            nameTextView = (TextView) itemView.findViewById(R.id.userName);
            surnameTextView = (TextView) itemView.findViewById(R.id.userSurname);
            emailTextView = (TextView) itemView.findViewById(R.id.userEmail);
            ageTextView = (TextView) itemView.findViewById(R.id.userAge);
            avatarImageView = (ImageView) itemView.findViewById(R.id.userStandardAvatar);
        }
    }
}