package es.iesnervion.fjruiz.mov_examen_2eva.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.iesnervion.fjruiz.mov_examen_2eva.R;
import es.iesnervion.fjruiz.mov_examen_2eva.arrayAdapter.MyArrayAdapter;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnDetailsInterationListener;
import es.iesnervion.fjruiz.mov_examen_2eva.model.Person;
import es.iesnervion.fjruiz.mov_examen_2eva.sqlite.PersonDBHandler;

public class DetailsFragment extends ListFragment {
    private OnDetailsInterationListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PersonDBHandler handler=new PersonDBHandler(getContext());
        ArrayList<Person> people=handler.getPersons();
        if(people.size()!=0) {
            Person[] peopleArray=new Person[people.size()];
            people.toArray(peopleArray);
            setListAdapter(new MyArrayAdapter(getContext(), R.layout.master_row, R.id.DetailsFragment, peopleArray));
        }else{
            Toast.makeText(getContext(), "No hay personas registradas", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailsInterationListener) {
            mListener = (OnDetailsInterationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDetailsInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Person p=(Person) getListView().getItemAtPosition(position);
        mListener.onDetailsInteraction(p.getId());
    }
}
