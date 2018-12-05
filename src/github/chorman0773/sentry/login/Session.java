package github.chorman0773.sentry.login;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;

public class Session {
	private byte[] sessionToken;
	private byte[] authToken;
	
	public Session(String name,String pwd) throws IOException {
		JsonObject obj = new JsonObject();
		obj.addProperty("reqcode", 0);
		obj.addProperty("account", name);
		obj.addProperty("password", pwd);
		obj = (JsonObject) post("/auth",obj);
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
