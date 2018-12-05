package github.chorman0773.sentry.authentication;

import java.io.Closeable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.URI;
import java.net.URLConnection;

public final class AuthenticationResponce implements Closeable, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5005251505675653659L;
	private final int responce;
	private final String userdata;
	private final URLConnection responceDir;
	private final boolean authenticated;

	public AuthenticationResponce(int responce, String userdata, URLConnection responceDir,boolean authCompleted) {
		this.responce = responce;
		this.userdata = userdata;
		this.responceDir = responceDir;
		this.authenticated = authCompleted;
	}

	@Override
	public void close() throws IOException {
		responceDir.getInputStream().close();
		responceDir.getOutputStream().close();
		

	}
	protected final void writeObject(java.io.ObjectOutputStream out)
		     throws IOException{
		throw new NotSerializableException("Can't serialize a Authentication class");
	}
		 protected final void readObject(java.io.ObjectInputStream in)
		     throws IOException, ClassNotFoundException{
			 throw new NotSerializableException("Can't serialize a Authentication class");
		 }
		 protected final void readObjectNoData()
		     throws ObjectStreamException{
			 throw new NotSerializableException("Can't serialize a Authentication class");
		 }
	public final AuthenticationResponce clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("cannot clone a Authentication class");
	}
	public int requestResponce(){
		return responce;
	}

	/**
	 * @return the userdata
	 */
	public String returnedName() {
		return userdata;
	}

	/**
	 * @return the responceDir
	 */
	public URLConnection responceResource() {
		return responceDir;
	}

	/**
	 * Checks if the authentication completed. If false, check requestResponce() for the request responce code.
	 * @return whether or not the authentication was complete.
	 */
	public boolean wasComplete() {
		return authenticated;
	}
	

}
