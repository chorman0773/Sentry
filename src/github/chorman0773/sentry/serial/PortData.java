package github.chorman0773.sentry.serial;

import java.io.InputStream;
import java.io.OutputStream;

final class PortData {
	private static native void registerNatives();
	static{
		System.loadLibrary("serial");
		registerNatives();
	}
	private PortData() {
		// TODO Auto-generated constructor stub
	}
	static int getType(int port)throws SecurityException{
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::Type",port));
		return getType0(port);
	}
	private native static int getType0(int port);
	
	static String getLabel(int port)throws SecurityException{
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::Label",port));
		return getLabel0(port);
	}
	private static native String getLabel0(int port);
	
	static boolean isOperationAllowed(String operation, int port)throws SecurityException{
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::Operations",port));
		return isOperationAllowed(operation,port);
	}
	private static native boolean isOperationAllowed0(String operation, int port);
	
	static void connect(int port)throws SecurityException{
		System.getSecurityManager().checkPermission(new SerialPortConnectPermission("SerialPort.Connect::Generic",port));
		connect0(port);
	}
	private static native void connect0(int port);
	
	static InputStream getInputStream(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::IOStream",port));
		return getInputStream0(port);
	}
	private static native InputStream getInputStream0(int port);
	
	static OutputStream getOutputStream(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::IOStream",port));
		return getOutputStream0(port);
	}
	private static native OutputStream getOutputStream0(int port);
	static int[] getActivePorts() {
		System.getSecurityManager().checkPermission(new SerialPortConnectPermission("SerialPort.Connect::ScanActive",-1));
		return getActivePorts0();
	}
	private static native int[] getActivePorts0();
	
	static void lockPort(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Lock::Unique",port));
		lockPort0(port);
	}
	private static native void lockPort0(int port);
	static void unlockPort(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Lock::Release",port));
		unlockPort0(port);
	}
	private static native void unlockPort0(int port);
	
	static short getVid(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::VendorId",port));
		return getVid0(port);
	}
	private static native short getVid0(int port);
	static short getPid(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::ProductId",port));
		return getPid0(port);
	}
	private static native short getPid0(int port);
	static short getClassId(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::ClassId",port));
		return getClassId0(port);
	}
	private static native short getClassId0(int port);
	static short getSubclassId(int port) {
		System.getSecurityManager().checkPermission(new SerialPortAccessPermission("SerialPort.Get::SubclassId",port));
		return getSubclassId0(port);
	}
	private static native short getSubclassId0(int port);
}
