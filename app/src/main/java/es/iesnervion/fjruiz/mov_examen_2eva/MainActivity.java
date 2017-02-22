package es.iesnervion.fjruiz.mov_examen_2eva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import es.iesnervion.fjruiz.mov_examen_2eva.fragments.DetailsFragment;
import es.iesnervion.fjruiz.mov_examen_2eva.fragments.MasterFragment;
import es.iesnervion.fjruiz.mov_examen_2eva.interfaces.OnMasterInteractionListener;

public class MainActivity extends AppCompatActivity implements OnMasterInteractionListener{
    //TODO use butterknife

    private boolean inTablet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(findViewById(R.id.activity_main)!=null){
            MasterFragment master = new MasterFragment();
            inTablet=false;
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main,master).commit();
        }else{
            inTablet=true;
        }
    }

    @Override
    public void onFragmentInteraction(int option) {
        switch (option){
            //Add
            case 0:
                //TODO call EditorFragment
                break;
            //View
            case 1:
                //TODO call DetailsFragment
                break;
        }
    }
}
