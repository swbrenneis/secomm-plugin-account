/**
 * 
 */
package org.secomm.plugins.cordova;

import org.cryptokitty.keys.RSAPrivateKey;
import org.cryptokitty.keys.RSAPublicKey;

/**
 * @author stevebrenneis
 *
 * Session state POD
 */
public class SessionState {

	/**
	 * Client authentication random bytes.
	 */
	public byte[] clientAuthRandom;

	/**
	 * Server authentication random bytes.
	 */
	public byte[] serverAuthRandom;

	/**
	 * Session ID.
	 */
	public int sessionId;

	/**
	 * Authentication token.
	 */
	public long authToken;

	/**
	 * Public ID.
	 */
	public byte[] publicId;

	/**
	 * Generated password.
	 */
	public byte [] genpass;

	/**
	 * Enclave AES key.
	 */
	public byte[] enclaveKey;

	/**
	 * Contacts AES key.
	 */
	public byte[] contactsKey;

	/**
	 * GCM authentication data.
	 */
	public byte[] authData;

	/**
	 * Account random number. Used to generate the private ID.
	 */
	public byte[] accountRandom;

	/**
	 * Server authentication public key.
	 */
	public RSAPublicKey serverPublicKey;

	/**
	 * User authentication public key.
	 */
	public RSAPublicKey userPublicKey;

	/**
	 * User authentication public key.
	 */
	public RSAPrivateKey userPrivateKey;

	/**
	 * 
	 */
	protected SessionState() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Used by ClientVault when passed a ParameterGenerator object.
	 */
	protected SessionState(SessionState other) {

		this.publicId = other.publicId;
		this.genpass = other.genpass;
		this.enclaveKey = other.enclaveKey;
		this.contactsKey = other.contactsKey;
		this.authData = other.authData;
		this.accountRandom = other.accountRandom;
		this.serverPublicKey = other.serverPublicKey;
		this.userPrivateKey = other.userPrivateKey;
		this.userPublicKey = other.userPublicKey;

	}

}
