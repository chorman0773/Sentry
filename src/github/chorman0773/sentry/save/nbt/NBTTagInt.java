package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTPrimitive {
	private int val;
	public NBTTagInt() {
		// TODO Auto-generated constructor stub
	}
	public NBTTagInt(int val) {
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
		return TAG_INT;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Integer.toString(val);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(val);
	}

	@Override
	public void read(DataInput in) throws IOException {
		val = in.readInt();
	}

}
