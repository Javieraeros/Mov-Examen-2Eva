package es.iesnervion.fjruiz.mov_examen_2eva.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fjruiz on 31/01/17.
 */

public class PersonsDB extends SQLiteOpenHelper {
    private static String DB_NAME="Person_DB";
    private static int VERSION=1;

    public PersonsDB(Context context) {
        super(context, DB_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creaTabla="Create table "+Contract.PersonTable.TABLE+"(";
        creaTabla+=Contract.PersonTable.ID+" integer primary key autoincrement,";
        creaTabla+=Contract.PersonTable.NAME+" text,";
        creaTabla+=Contract.PersonTable.AGE+" integer,";
        creaTabla+=Contract.PersonTable.SEX+" text,";
        creaTabla+=Contract.PersonTable.TELEPHONE+" text);";
        db.execSQL(creaTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
