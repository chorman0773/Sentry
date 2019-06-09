package github.chorman0773.sentry.authentication;

import java.io.Closeable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.URL;

import javax.net.ssl.SSLEngine;

/**
 * AuthenticationService is a class which allows users to create customized, secure, Thread-safe, and versatile 
 * Authenticator.
 * <br/> Note that only the thread that constructs the Service can call any of its methods. This is to ensure
 * Thread-safety. 
 * @author Connor
 */
public abstract class AuthenticationService implements Cloneable, Closeable, Serializable {
	private Thread owner;
	protected AuthenticationService(){
		owner = Thread.currentThread();
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -1551232307540583023L;
	@Override
	public final void close() throws IOException {
			if(Thread.currentThread()!=owner)throw new IllegalAccessError("The current thread does not have access to this call");
			doClose();
	}
	public abstract void doClose() throws IOException;
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
	public final AuthenticationService clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("cannot clone a Authentication class");
	}
	public final AuthenticationResponce authenticateInformation(String username, byte[] password){
		if(Thread.currentThread()!=owner)throw new IllegalAccessError("This object is unavailable to the current thread");
		return doAuthentication(username, password);
	}
	protected abstract AuthenticationResponce doAuthentication(String username, byte[] password);

}
