package es.iesnervion.fjruiz.mov_examen_2eva.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.iesnervion.fjruiz.mov_examen_2eva.R;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnMasterInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MasterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MasterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterFragment extends Fragment {

    private OnMasterInteractionListener mListener;

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
}
