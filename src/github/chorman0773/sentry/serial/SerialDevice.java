package github.chorman0773.sentry.serial;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Represents a Device Connected via a Serial Port
 * @author connor
 *
 */
public final class SerialDevice implements Closeable {
	private int port;
	private SerialDevice(int port) {
		this.port = port;
	}
	public static int[] getActiveDevices(short cl) {
		return Arrays.stream(PortData.getActivePorts()).filter(p->PortData.getClassId(p)==cl)
			.toArray();
	}
	public static SerialDevice open(int port) {
		PortData.lockPort(port);
		return new SerialDevice(port);
	}
	@Override
	public void close() throws IOException {
		PortData.unlockPort(port);
	}
	public String getLabel() {
		return PortData.getLabel(port);
	}
	public short getVendorId() {
		return PortData.getVid(port);
	}
	public short getProductId() {
		return PortData.getPid(port);
	}
	public short getClassId() {
		return PortData.getClassId(port);
	}
	public short getSubclassId() {
		return PortData.getSubclassId(port);
	}
	public static interface ClassIds{
		public static short UNSPECIFIED = 0,
				AUDIO_DEVICE = 1, COMMUNICATION_DEVICE = 2,
				HUMAN_INTERFACE_DEVICE = 3, PHYSICAL_INTERFACE_DEVICE = 5,
				IMAGE = 6, PRINTER = 7, MASS_STORAGE = 8,
				HUB = 9, COMMUNICATION_DATA = 10,
				SMART_CARD = 11, SECURITY_DEVICE = 13,
				VIDEO = 14, PHCD = 15,
				AV_DEVICE = 16, DIAGNOSTIC_DEVICE = 0xDC,
				WIRELESS_ADAPATER = 0xe0,
				MISC= 0xef,
				APPLICATION_SPECIFIC = 0xfe,
				VENDOR_SPECIFIC = 0xff;
	}
	public InputStream getInputStream() {
		return PortData.getInputStream(port);
	}
	public OutputStream getOutputStream() {
		return PortData.getOutputStream(port);
	}
	
}
