/**
 * 
 */
package org.secomm.plugins.cordova;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stevebrenneis
 *
 */
public class FunctionFactory {

	/**
	 * Functions factories.
	 */
	private static Map<String, PluginFunctionFactory> functionFactories;

	/**
	 * 
	 */
	private FunctionFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieve a plugin function.
	 * 
	 * @param name
	 * @return The requested plugin function or null if not found.
	 */
	public static PluginFunction getFunction(String name) {

		PluginFunctionFactory factory = functionFactories.get(name);
		if (factory != null) {
			return factory.create();
		}
		else {
			return null;
		}

	}

	/**
	 * Load the factory map.
	 */
	public static void initialize() {

		functionFactories = new HashMap<>();
		functionFactories.put("createVault", new PluginFunctionFactory() {
												@Override
												public PluginFunction create() {
													return new CreateVaultFunction();
												}
											});

	}

}
