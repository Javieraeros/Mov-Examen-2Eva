package es.iesnervion.fjruiz.mov_examen_2eva.arrayAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import es.iesnervion.fjruiz.mov_examen_2eva.R;
import es.iesnervion.fjruiz.mov_examen_2eva.model.Person;


public class MyArrayAdapter extends ArrayAdapter {

    public MyArrayAdapter(Context context, int resource, int textViewResourceID, Object[] objects) {
        super(context, resource, textViewResourceID, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        Person person = (Person) getItem(position);

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.details_row, parent, false);

            holder = new ViewHolder(v, R.id.detailsName,R.id.detailsTelephone);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.getName().setText(person.getName());
        holder.getTelephone().setText(person.getTelephone());
        return v;
    }

}
