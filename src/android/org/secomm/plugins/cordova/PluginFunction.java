/**
 * 
 */
package org.secomm.plugins.cordova;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author stevebrenneis
 *
 */
public interface PluginFunction {

	/**
	 * Execute the plugin function.
	 * 
	 * @param args
	 * @param callbackContext
	 * @return
	 * @throws JSONException
	 */
	public boolean execute(JSONArray args, CallbackContext callbackContext) throws JSONException;

}
