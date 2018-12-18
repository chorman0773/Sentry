package github.chorman0773.sentry.serial;

public class SerialPortAccessPermission extends SerialPortPermission {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4085426337117175575L;
	private String operation;
	public SerialPortAccessPermission(String operation, int port) {
		super(operation, port);
		this.operation = operation;
	} 
	

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return "SerialPort.Read::Header,"+operation;
	}

}
