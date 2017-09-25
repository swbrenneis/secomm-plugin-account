package org.secomm.plugins.cordova.account;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.secomm.plugins.cordova.account.impl.FunctionFactory;
import org.secomm.plugins.cordova.account.impl.PluginFunction;
import org.secomm.plugins.cordova.account.impl.SeAccountPluginImpl;

public class SeAccountPlugin extends CordovaPlugin {

	/*
	 * (non-Javadoc)
	 * @see org.apache.cordova.CordovaPlugin#initialize(org.apache.cordova.CordovaInterface, org.apache.cordova.CordovaWebView)
	 */
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

		FunctionFactory.initialize();

	}
	

	/*
	 * (non-Javadoc)
	 * @see org.apache.cordova.CordovaPlugin#execute(java.lang.String, org.json.JSONArray, org.apache.cordova.CallbackContext)
	 */
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		LOG.d(SeAccountPluginImpl.TAG, "Starting execute " + action);

		PluginFunction function = FunctionFactory.getFunction(action);
		if (function != null) {
			return function.execute(args, callbackContext, cordova);
		}
		else {
			return false;
		}

	}

}
