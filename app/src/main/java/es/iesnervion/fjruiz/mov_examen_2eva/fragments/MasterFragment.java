package es.iesnervion.fjruiz.mov_examen_2eva.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.iesnervion.fjruiz.mov_examen_2eva.R;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnMasterInteractionListener;

public class MasterFragment extends ListFragment {

    private OnMasterInteractionListener mListener;
    private Context context;

    public MasterFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] options={getResources().getString(R.string.add),getResources().getString(R.string.view)};
        setListAdapter(new ArrayAdapter<String>(getContext(), R.layout.master_row,R.id.RowText,options));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMasterInteractionListener) {
            mListener = (OnMasterInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMasterInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onMasterInteraction(position);
    }
}
