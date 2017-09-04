/**
 * 
 */
package org.secomm.plugins.cordova;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyPair;

import org.cryptokitty.digest.SHA1;
import org.cryptokitty.digest.SHA256;
import org.cryptokitty.keys.RSAKeyPairGenerator;
import org.cryptokitty.keys.RSAPublicKey;
import org.cryptokitty.keys.RSAPrivateKey;
import org.cryptokitty.random.FortunaSecureRandom;

/**
 * @author stevebrenneis
 *
 */
public class ParameterGenerator extends SessionState {

	/**
	 * Secure RNG.
	 */
	private FortunaSecureRandom rnd;

	/**
	 * 
	 */
	public ParameterGenerator() {

		rnd = new FortunaSecureRandom();

	}

	/**
	 * 
	 * @return
	 * @throws ParameterException
	 */
	public void generateAccountParameters(String accountname) {

		generateGenpass();
		generateAuthData();
		generateEnclaveKey();
		generateContactsKey();
		generateUserKeys();
		generatePublicId(accountname);

	}

	private void generateAuthData() {

		SHA256 digest = new SHA256();
		authData = digest.digest(genpass);

	}

	/**
	 * 
	 * @param params
	 * @throws ParameterException
	 */
	private void generateEnclaveKey() {

		// Generate the enclave key
		byte[] rndbytes = new byte[32];
		rnd.nextBytes(rndbytes);
		SHA256 digest = new SHA256();
		digest.update(rndbytes);
		long time = System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(8);
		buf.putLong(time);
		buf.flip();
		digest.update(buf.array());
		enclaveKey = digest.digest();

	}

	/**
	 * 
	 * @param params
	 * @throws ParameterException
	 */
	private void generateContactsKey() {

		// Generate the enclave key
		byte[] rndbytes = new byte[32];
		rnd.nextBytes(rndbytes);
		SHA256 digest = new SHA256();
		digest.update(rndbytes);
		long time = System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(8);
		buf.putLong(time);
		buf.flip();
		digest.update(buf.array());
		contactsKey = digest.digest();

	}

	/**
	 * Generate the account password bytes.
	 * 
	 * @param params
	 */
	private void generateGenpass() {

		genpass = new byte[20];
		rnd.nextBytes(genpass);

	}

	/**
	 * 
	 * @param params
	 * @param username
	 * @throws ParameterException 
	 */
	private void generatePublicId(String accountname) {

		// Create the user id. SHA1 hash <- user name + create time + "@secomm.org"
		SHA1 sha1 = new SHA1();
		sha1.update(accountname.getBytes());
		int created = (int)System.currentTimeMillis() / 1000;
		ByteBuffer cbuf = ByteBuffer.allocate(4);
		cbuf.order(ByteOrder.BIG_ENDIAN);
		cbuf.putInt(created);
		cbuf.flip();
		sha1.update(cbuf.array());
		sha1.update("@secomm.org".getBytes());
		publicId = sha1.digest();

	}

	/**
	 * 
	 * @param params
	 * @throws ParameterException
	 */
	private void generateUserKeys() {

		RSAKeyPairGenerator gen = new RSAKeyPairGenerator();
		gen.initialize(2048);
		KeyPair pair = gen.generateKeyPair();
		userPublicKey = (RSAPublicKey)pair.getPublic();
		userPrivateKey = (RSAPrivateKey)pair.getPrivate();

	}

}
