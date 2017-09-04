/**
 * 
 */
package org.secomm.plugins.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author stevebrenneis
 *
 */
public class CreateVaultFunction implements PluginFunction {

	/**
	 * 
	 */
	public CreateVaultFunction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.secomm.plugins.cordova.PluginFunction#execute(org.json.JSONArray, org.apache.cordova.CallbackContext)
	 */
	@Override
	public boolean execute(JSONArray args, CallbackContext callbackContext) throws JSONException {

		LOG.d(SeAccountPlugin .TAG, "Starting create vault function");

		String accountname = args.getString(0);
		String passphrase = args.getString(1);
		ParameterGenerator gen = new ParameterGenerator();
		gen.generateAccountParameters(accountname);

		ClientVault vault = new ClientVault(gen, passphrase);
		try {
			byte[] encoded = vault.encodeVault();
			PluginResult result = new PluginResult(PluginResult.Status.OK, encoded);
			callbackContext.sendPluginResult(result);
			return true;
		}
		catch (VaultException e) {
			LOG.d(SeAccountPlugin.TAG, "Vault creation error: " + e.getLocalizedMessage());
			return false;
		}

	}

}
