/**
 * 
 */
package org.secomm.plugins.cordova;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

import org.cryptokitty.codec.GCMCodec;
import org.cryptokitty.codec.PEMCodec;
import org.cryptokitty.digest.SHA256;
import org.cryptokitty.exceptions.CodecException;

/**
 * @author stevebrenneis
 *
 */
public class ClientVault extends SessionState {

	/**
	 * Current vault version.
	 */
	private static final String CURRENT_VERSION = "1.0";

	/**
	 * Vault version.
	 */
	private String version;

	/**
	 * Vault key.
	 */
	private byte vaultKey[];

	/**
	 * Passphrase to the vault.
	 */
	private String passphrase;

	/**
	 * Vault save path.
	 */
	private String path;

	/**
	 * 
	 * @param state
	 * @param passphrase
	 */
	public ClientVault(SessionState state, String passphrase) {
		super(state);

		version = CURRENT_VERSION;
		this.passphrase = passphrase;
		s2k();

	}
	
	/**
	 * Used by loadVault
	 */
	public ClientVault(String passphrase) {

		version = CURRENT_VERSION;
		this.passphrase = passphrase;
		s2k();

	}
	
	private void decodeVault(byte[] ciphertext) throws VaultException {

		try {
			GCMCodec codec = new GCMCodec(ciphertext);
			SHA256 digest = new SHA256();
			byte[] additionalData = digest.digest(passphrase.getBytes());
			codec.decrypt(vaultKey, additionalData);

			version = codec.getString();
			publicId = codec.getBlock();
			genpass = codec.getBlock();
			enclaveKey = codec.getBlock();
			contactsKey = codec.getBlock();
			authData = codec.getBlock();
			accountRandom = codec.getBlock();
			String serverPEM = codec.getString();
			String userPEM = codec.getString();

			PEMCodec pem = new PEMCodec(true);	// X.509 keys.
			serverPublicKey = pem.decodePublicKey(serverPEM);
			userPrivateKey = pem.decodePrivateKey(userPEM);
			userPublicKey = pem.decodePublicFromPrivate(userPEM);
		}
		catch (CodecException e) {
			throw new VaultException("Decryption/decoding error: "
													+ e.getLocalizedMessage());
		}

	}
	
	private byte[] encodeVault() throws VaultException {

		try {
			GCMCodec codec = new GCMCodec();
			codec.putString(version);
			codec.putBlock(publicId);
			codec.putBlock(genpass);
			codec.putBlock(enclaveKey);
			codec.putBlock(contactsKey);
			codec.putBlock(authData);
			codec.putBlock(accountRandom);

			PEMCodec pem = new PEMCodec(true);	// X.509 keys
			StringWriter srvWriter = new StringWriter();
			pem.encode(srvWriter, serverPublicKey);
			codec.putString(srvWriter.toString());
			StringWriter usrWriter = new StringWriter();
			pem.encode(usrWriter, userPrivateKey, userPublicKey);
			codec.putString(usrWriter.toString());

			SHA256 digest = new SHA256();
			byte[] additionalData = digest.digest(passphrase.getBytes());
			return codec.encrypt(vaultKey, additionalData);
		}
		catch (CodecException e) {
			throw new VaultException("Encryption/encoding error: "
													+ e.getLocalizedMessage());
		}

	}

	/**
	 * 
	 * @param path
	 * @throws VaultException
	 */
	private void loadVault(String path) throws VaultException {
		
		try {
			File infile = new File(path);
			FileInputStream in = new FileInputStream(infile);
			int size = in.available();
		
			byte ciphertext[] = new byte[size];
			in.read(ciphertext);
			in.close();

			decodeVault(ciphertext);
		}
		catch (IOException e) {
			throw new VaultException("File error: " + e.getMessage());
		}

	}

	/**
	 * 
	 * @param path
	 * @param passphrase
	 * @return
	 * @throws VaultException
	 */
	public static ClientVault loadVault(String path, String passphrase) throws VaultException {
		
			ClientVault vault = new ClientVault(passphrase);
			vault.loadVault(path);
			return vault;

	}

	/**
	 * 
	 * @throws VaultException
	 */
	public void saveVault() throws VaultException {

		try {
			byte ciphertext[] = encodeVault();

			FileOutputStream out = new FileOutputStream(new File(path));
			out.write(ciphertext);
			out.close();
		}
		catch (IOException e) {
			throw new VaultException("Vault encoding error: " + e.getMessage());
		}

	}

	/**
	 * 
	 */
	private void s2k() {
		
		char c = passphrase.charAt(passphrase.length() - 1);
	    int count = c & 0x0f;
	    if (count == 0) {
	        count = 0x0a;
	    }
	    count = count << 16;
	    String message = passphrase + "client vault";
	    SHA256 digest = new SHA256();
	    byte hash[] = digest.digest(message.getBytes());
	    count -= 32;
	    while (count > 0) {
	    	ByteBuffer buf = ByteBuffer.wrap(message.getBytes());
	    	buf.put(hash);
	    	hash = digest.digest(buf.array());
	    	count = count - 32 - message.length();
	    }
	    vaultKey = hash;
		
	}

	/**
	 * 
	 * @param path
	 */
	public void setSavePath(String path) {
		this.path = path;
	}

}
