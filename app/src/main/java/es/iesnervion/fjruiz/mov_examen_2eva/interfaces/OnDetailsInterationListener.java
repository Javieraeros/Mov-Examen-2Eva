package es.iesnervion.fjruiz.mov_examen_2eva.interfaces;

/**
 * Created by fjruiz on 22/02/17.
 */

public interface OnDetailsInterationListener {
    /**
     * Method used to pass the activity, the id of the person we want to create,
     * or we want to
     * @param id
     * @param _new
     */
    void onFragmentInteraction(int id,boolean _new);
}
