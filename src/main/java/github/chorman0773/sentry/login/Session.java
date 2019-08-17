package github.chorman0773.sentry.login;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;

import github.chorman0773.sentry.login.fs.SentrySessionKeyPrincipal;

/**
 * Basic class for Constructing Sentry Sessions
 * @author chorm
 */
public class Session {
	private SentrySessionKeyPrincipal session;
	
	public Session(String id,PrivateKey priv,PublicKey pub) throws IOException {
		session = new SentrySessionKeyPrincipal(id,pub,priv);
	}
	public Object query(String path) {
		return null;
	}
	public Object post(String path,Object content) {
		return null;
	}
	public void delete(String path) {
		
	}
	public ByteBuffer get(String path) {
		return null;
	}
	public int get(String path,ByteBuffer buff) {
		return 0;
	}
	public void put(String path,ByteBuffer bytes) {
		
	}

}
