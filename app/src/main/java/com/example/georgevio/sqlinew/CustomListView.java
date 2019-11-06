package com.example.georgevio.sqlinew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListView extends ArrayAdapter<Users> {

    private ArrayList<Users> users;
    private LayoutInflater layoutInflater;
    private Context context;
    private int ViewResourceId;

    // Constructor
    public CustomListView(Context context,int textViewResourceId, ArrayList<Users> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.context = context;
        ViewResourceId = textViewResourceId;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(ViewResourceId, null);

        Users user = users.get(position);

        if (user != null) {
            TextView Name = (TextView) convertView.findViewById(R.id.editName);
            TextView Phone = (TextView) convertView.findViewById(R.id.editPhone);
            TextView Email = (TextView) convertView.findViewById(R.id.editEmail);
            TextView Age = (TextView) convertView.findViewById(R.id.editAge);

            if (Name != null) {
                Name.setText(user.getName());
            }
            if (Phone != null) {
                Phone.setText((user.getPhone()));
            }
            if (Email != null) {
                Email.setText((user.getEmail()));
            }
            if (Age != null) {
                Age.setText(user.getAge());
            }
        }

        return convertView;
    }

   /* public int getCount() {
        return users.size();
    }

    public Object getItem(int position) {
        return users.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //      int type = getItemViewType(position);

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();

            holder.FirstText = (TextView) convertView.findViewById(R.id.editName);
            holder.SecondText = (TextView) convertView.findViewById(R.id.editPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.FirstText.setText("JOPH");
        holder.SecondText.setText("44");

        return convertView;
    }

    static class ViewHolder {
        TextView FirstText;
        TextView SecondText;
    }*/
}
