package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTPrimitive {
	private byte b;
	public NBTTagByte(byte value) {
		b = value;
	}
	public NBTTagByte(int value) {
		b = (byte)value;
	}
	public NBTTagByte() {}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return b;
	}
	
	public int getUnsigned() {
		return b&0xff;
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_BYTE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Byte.toString(b);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeByte(b);
	}

	@Override
	public void read(DataInput in) throws IOException {
		b = in.readByte();
	}

}
