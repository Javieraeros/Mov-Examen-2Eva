package es.iesnervion.fjruiz.mov_examen_2eva.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import es.iesnervion.fjruiz.mov_examen_2eva.model.Person;

public class PersonDBHandler {
    private Context context;

    public PersonDBHandler(Context context){
        this.context=context;
    }

    public int insertPerson(Person p){
        PersonsDB myDB=new PersonsDB(context);
        SQLiteDatabase db=myDB.getWritableDatabase();
        int rowsNumber=0;
        ContentValues miInsert=new ContentValues();
        miInsert.put(Contract.PersonTable.NAME,p.getName());
        miInsert.put(Contract.PersonTable.AGE,p.getAge());
        miInsert.put(Contract.PersonTable.TELEPHONE,p.getTelephone());
        miInsert.put(Contract.PersonTable.SEX,String.valueOf(p.getSex()));
        rowsNumber=(int) db.insert(Contract.PersonTable.TABLE,null,miInsert);
        return rowsNumber;
    }

    public int updatePerson(Person p){
        PersonsDB myDB=new PersonsDB(context);
        SQLiteDatabase db=myDB.getWritableDatabase();
        int rowsNumber=0;
        ContentValues myUpdate=new ContentValues();
        myUpdate.put(Contract.PersonTable.NAME,p.getName());
        myUpdate.put(Contract.PersonTable.AGE,p.getAge());
        myUpdate.put(Contract.PersonTable.TELEPHONE,p.getTelephone());
        myUpdate.put(Contract.PersonTable.SEX,String.valueOf(p.getSex()));
        rowsNumber=db.update(Contract.PersonTable.TABLE,myUpdate,Contract.PersonTable.ID+"=?",
                new String[]{String.valueOf(p.getId())});
        return rowsNumber;
    }

    public Person getPerson(int id){
        Person p=null;
        PersonsDB myDB=new PersonsDB(context);
        SQLiteDatabase db=myDB.getReadableDatabase();
        String selectQuery="Select ";
        selectQuery+=Contract.PersonTable.NAME+",";
        selectQuery+=Contract.PersonTable.AGE+",";
        selectQuery+=Contract.PersonTable.TELEPHONE+",";
        selectQuery+=Contract.PersonTable.SEX+" from ";
        selectQuery+=Contract.PersonTable.TABLE+" where ";
        selectQuery+=Contract.PersonTable.ID+"="+id;
        Cursor resultado=db.rawQuery(selectQuery,null);
        if(resultado.moveToFirst()){
            p=new Person();
            p.setId(id);
            p.setName(resultado.getString(0));
            p.setAge(resultado.getInt(1));
            p.setTelephone(resultado.getString(2));
            //Use charAt because we save the sex as a string in the db, but as a char in the person class
            p.setSex(resultado.getString(3).charAt(0));
        }
        resultado.close();
        return p;
    }

    public ArrayList<Person> getPersons(){
        ArrayList<Person> people=new ArrayList<>();
        PersonsDB myDB=new PersonsDB(context);
        SQLiteDatabase db=myDB.getReadableDatabase();
        String selectQuery="Select ";
        selectQuery+=Contract.PersonTable.ID+",";
        selectQuery+=Contract.PersonTable.NAME+",";
        selectQuery+=Contract.PersonTable.AGE+",";
        selectQuery+=Contract.PersonTable.TELEPHONE+",";
        selectQuery+=Contract.PersonTable.SEX+" from ";
        selectQuery+=Contract.PersonTable.TABLE;
        Cursor resultado=db.rawQuery(selectQuery,null);
        if(resultado.moveToFirst()) {
            do {
                Person p = new Person();
                p.setId(resultado.getInt(0));
                p.setName(resultado.getString(1));
                p.setAge(resultado.getInt(2));
                p.setTelephone(resultado.getString(3));
                //Use charAt because we save the sex as a string in the db, but as a char in the person class
                p.setSex(resultado.getString(4).charAt(0));
                people.add(p);
            } while (resultado.moveToNext());
        }
        resultado.close();

        return people;
    }
}
