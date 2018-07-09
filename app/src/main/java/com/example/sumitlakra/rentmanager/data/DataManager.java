package com.example.sumitlakra.rentmanager.data;

import com.example.sumitlakra.rentmanager.data.db.DbHelper;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.data.network.ApiHelper;

import java.util.List;

/**
 *  interface that is implemented by the AppDataManager.
 *  It contains methods, exposed for all the data handling operations.
 */

public interface DataManager extends DbHelper, ApiHelper {

}
