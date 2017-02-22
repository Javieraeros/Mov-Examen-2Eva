package es.iesnervion.fjruiz.mov_examen_2eva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import es.iesnervion.fjruiz.mov_examen_2eva.fragments.DetailsFragment;
import es.iesnervion.fjruiz.mov_examen_2eva.fragments.EditorFragment;
import es.iesnervion.fjruiz.mov_examen_2eva.fragments.MasterFragment;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnDetailsInterationListener;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnEditorInteractionListener;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnMasterInteractionListener;
import es.iesnervion.fjruiz.mov_examen_2eva.model.Person;
import es.iesnervion.fjruiz.mov_examen_2eva.sqlite.PersonDBHandler;

public class MainActivity extends AppCompatActivity implements OnMasterInteractionListener,
        OnDetailsInterationListener,OnEditorInteractionListener{
    //TODO use butterknife

    private boolean inTablet;

    public static final String PersonBundle="Person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(findViewById(R.id.movil)!=null){
            MasterFragment master = new MasterFragment();
            inTablet=false;
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movil,master).commit();
        }else{
            inTablet=true;
        }
    }

    @Override
    public void onMasterInteraction(int option) {
        switch (option){
            //Add
            case 0:
                EditorFragment editor=new EditorFragment();
                if(inTablet){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.tabletSlave,editor).addToBackStack(null).commit();
                }else{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movil,editor).addToBackStack(null).commit();
                }
                break;
            //View
            case 1:
                DetailsFragment details=new DetailsFragment();
                if(inTablet){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.tabletSlave,details).addToBackStack(null).commit();
                }else{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movil,details).addToBackStack(null).commit();
                }
                break;
        }
    }
    /**
     * Method used to pass the activity, the id of the person we want to view or edit
     * @param id
     */
    @Override
    public void onDetailsInteraction(int id) {
        PersonDBHandler dbHandler=new PersonDBHandler(this);
        Person p=dbHandler.getPerson(id);
        EditorFragment editor=new EditorFragment();
        Bundle myBundle=new Bundle();
        myBundle.putParcelable(PersonBundle,p);
        editor.setArguments(myBundle);
        if(inTablet){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tabletSlave,editor).addToBackStack(null).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movil,editor).addToBackStack(null).commit();
        }
    }

    @Override
    public void onEditorInteracion(Person p) {
        PersonDBHandler dbHandler=new PersonDBHandler(this);
        int rows=0;
        if(p.getId()==0){
            rows=dbHandler.insertPerson(p);
        }else{
            rows=dbHandler.updatePerson(p);
        }
        if(rows!=0){
            //TODO use R.String.****
            Toast.makeText(this, "Persona guardada correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error en la BBDD", Toast.LENGTH_SHORT).show();
        }
        if(findViewById(R.id.movil)!=null){
            MasterFragment master = new MasterFragment();
            inTablet=false;
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movil,master).commit();
        }else{
            DetailsFragment details=new DetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tabletSlave,details).addToBackStack(null).commit();
        }
    }
}
