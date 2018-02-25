package quangnam.com.sample.data;

import quangnam.com.sample.data.database.IDatabaseHelper;
import quangnam.com.sample.data.network.ApiHelper;
import quangnam.com.sample.data.pref.IPrefHelper;

/**
 * Created by quangnam on 1/13/18.
 * Project Sample
 */

public interface DataManager extends ApiHelper, IPrefHelper, IDatabaseHelper {
}
