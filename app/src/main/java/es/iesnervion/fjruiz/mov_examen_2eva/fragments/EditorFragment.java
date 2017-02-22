package es.iesnervion.fjruiz.mov_examen_2eva.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import es.iesnervion.fjruiz.mov_examen_2eva.MainActivity;
import es.iesnervion.fjruiz.mov_examen_2eva.R;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnEditorInteractionListener;
import es.iesnervion.fjruiz.mov_examen_2eva.model.Person;

public class EditorFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private OnEditorInteractionListener mListener;
    private boolean editionMode =false,sexSelected=false,validAge=true;
    private Person person=null,savePerson=null;
    private EditText name,age,telephone;
    private RadioGroup rg;
    private RadioButton rbMan,rbWoman;
    private Switch edit;
    private Button accept;

    public EditorFragment() {
        // Required empty public constructor

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_editor, container, false);
        if(getArguments()!=null){
            editionMode =true;
            person=getArguments().getParcelable(MainActivity.PersonBundle);
        }
        //Region asignaciones

        name=(EditText) v.findViewById(R.id.txtName);
        age=(EditText) v.findViewById(R.id.txtAge);
        telephone=(EditText) v.findViewById(R.id.txtTelephone);

        rg=(RadioGroup) v.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rbMan=(RadioButton) v.findViewById(R.id.rbMan);
        rbWoman=(RadioButton) v.findViewById(R.id.rbWoman);

        edit=(Switch) v.findViewById(R.id.editorSwitch);
        edit.setOnCheckedChangeListener(this);
        accept=(Button) v.findViewById(R.id.editorAccept);
        accept.setOnClickListener(this);
        //endregion

        if(editionMode){
            name.setText(person.getName());
            telephone.setText(person.getTelephone());
            age.setText(person.getAge()+"");
            sexSelected=true;
            edit.setVisibility(View.VISIBLE);
            if(person.getSex()=='M') {
                rbMan.setChecked(true);
            }else{
                rbWoman.setChecked(true);
            }

            name.setEnabled(false);
            telephone.setEnabled(false);
            age.setEnabled(false);
            rbMan.setEnabled(false);
            rbWoman.setEnabled(false);
        }else{
            edit.setVisibility(View.INVISIBLE);
            name.setEnabled(true);
            telephone.setEnabled(true);
            age.setEnabled(true);
            rbMan.setEnabled(true);
            rbWoman.setEnabled(true);
        }
        return v;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditorInteractionListener) {
            mListener = (OnEditorInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * <p>Called when the checked radio button has changed. When the
     * selection is cleared, checkedId is -1.</p>
     *
     * @param group     the group in which the checked radio button has changed
     * @param checkedId the unique identifier of the newly checked radio button
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId!=-1) {
            sexSelected = true;
        }
    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            name.setEnabled(true);
            telephone.setEnabled(true);
            age.setEnabled(true);
            rbMan.setEnabled(true);
            rbWoman.setEnabled(true);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String personName=name.getText().toString();
        int personAge=0;
        char personSex='M';
        try {
            personAge= Integer.parseInt(age.getText().toString());
        }catch (NumberFormatException e){
            validAge=false;
        }
        String personTelephone=telephone.getText().toString();
        if(rbWoman.isChecked()){
            personSex='W';
        }
        if(sexSelected && validAge && !personName.equals("") && !personTelephone.equals("")){
            int id=0;
            if (person!=null){
                id=person.getId();
            }
            savePerson=new Person(id,personName,personAge,personTelephone,personSex);
            mListener.onEditorInteracion(savePerson);

        }else{
            Toast.makeText(getContext(), "Algún campo no es válido", Toast.LENGTH_SHORT).show();
        }
    }
}
