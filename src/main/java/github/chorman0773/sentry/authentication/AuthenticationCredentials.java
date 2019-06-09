package github.chorman0773.sentry.authentication;

import java.io.Closeable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;

public final class AuthenticationCredentials implements Cloneable, Closeable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 554711207138115033L;
	private final String username;
	private final byte[] password;
	public AuthenticationCredentials(String username, byte[] passwordBytes) {
		this.username = username;
		this.password = passwordBytes;
	}

	@Override
	public void close() throws IOException {
		Arrays.fill(password,(byte) 0);
		

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
	public final AuthenticationCredentials clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("cannot clone a Authentication class");
	}
	public synchronized AuthenticationResponce sendAuthenticationCredentials(AuthenticationService authenticator){
		return authenticator.authenticateInformation(username, password);
	}

}
