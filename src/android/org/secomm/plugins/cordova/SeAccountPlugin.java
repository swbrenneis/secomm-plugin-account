package org.secomm.plugins.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;

public class SeAccountPlugin extends CordovaPlugin {

	public static final String TAG = "SeAccountPlugin";

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		LOG.d(TAG, "Starting execute " + action);
		return false;
	}

}
