package github.chorman0773.sentry.serial;

public class SerialPortConnectPermission extends SerialPortPermission {

	public SerialPortConnectPermission(String operation, int port) {
		super(operation, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return super.getName();
	}

}
