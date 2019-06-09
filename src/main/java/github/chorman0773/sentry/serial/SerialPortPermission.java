package github.chorman0773.sentry.serial;

import java.security.Permission;

public abstract class SerialPortPermission extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7141901874050169287L;
	private int port;
	
	public SerialPortPermission(String operation,int port) {
		super(operation);
		this.port = port;
	}

	@Override
	public boolean implies(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof SerialPortPermission))
			return false;
		return obj.hashCode()==hashCode();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.security.Permission#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName()+":"+port;
	}
	

}
