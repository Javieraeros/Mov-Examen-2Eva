package es.iesnervion.fjruiz.mov_examen_2eva.arrayAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//TODO Use butterknife
public class ViewHolder {
    private TextView name;
    private TextView telephone;

    public ViewHolder(View row,int nameId,int telephoneId) {
        this.name = (TextView) row.findViewById(nameId);
        this.telephone = (TextView) row.findViewById(telephoneId);
    }


    public TextView getName() {
        return name;
    }


    public TextView getTelephone() {
        return telephone;
    }

}
