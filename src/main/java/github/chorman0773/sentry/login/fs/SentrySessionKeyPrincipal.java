package github.chorman0773.sentry.login.fs;

import java.nio.file.attribute.UserPrincipal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SentrySessionKeyPrincipal implements UserPrincipal {
	private String name;
	private PublicKey key;
	private PrivateKey privateKey;
	public SentrySessionKeyPrincipal(String name,PublicKey sessionKey,PrivateKey sessionPrivate) {
		this.name = name;
		this.key = sessionKey;
		this.privateKey = sessionPrivate;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public byte[] signRequest(byte[] payload){
		try {
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return rsaCipher.doFinal(payload);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PublicKey getSessionKey() {
		return key;
	}

}
