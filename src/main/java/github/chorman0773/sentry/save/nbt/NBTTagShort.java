package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTPrimitive {
	private short val;
	public NBTTagShort() {}
	public NBTTagShort(short val) {
		this.val = val;
	}
	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_SHORT;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Short.toString(val);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeShort(val);
	}

	@Override
	public void read(DataInput in) throws IOException {
		val = in.readShort();
	}

}
