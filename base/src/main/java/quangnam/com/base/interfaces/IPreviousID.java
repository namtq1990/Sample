package quangnam.com.base.interfaces;

/**
 * Created by quangnam on 8/23/17.
 * Project sosokan-android
 */

public interface IPreviousID {

//    int DEFAULT_SAVED_ID = -1;

    /**
     * When configuration change or something make this component destroy, we need id as a key
     * to get saved data from somewhere. Currently, this id is hashcode of previous instance.
     *
     * @return Hashcode of previous instance of this fragment if exists, otherwise return {@link #hashCode()}
     */
    int getSaveID();
}
