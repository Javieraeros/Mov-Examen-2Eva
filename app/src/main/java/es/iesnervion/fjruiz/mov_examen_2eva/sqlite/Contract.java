package es.iesnervion.fjruiz.mov_examen_2eva.sqlite;

import android.provider.BaseColumns;

/**
 * Created by fjruiz on 31/01/17.
 */

public class Contract {
    public static class PersonTable implements BaseColumns{

        public final static String TABLE="Persons";
        public final static String ID="_id";
        public final static String NAME="Name";
        public final static String TELEPHONE="Telephone";
        public final static String AGE="Age";
        public final static String SEX="Sex";

    }
}
