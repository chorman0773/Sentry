package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import github.lightningcreations.lclib.Hash;

public class NBTTagLong extends NBTPrimitive {
	private long val;
	public NBTTagLong() {}
	public NBTTagLong(long val) {
		this.val = val;
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return (int)val;
	}
	@Override
	public long getLong() {
		return (long)val;
	}
	
	@Override
	public float getFloat() {
		return (float)val;
	}
	
	@Override
	public double getDouble() {
		return val;
	}
	
	@Override
	public boolean getBoolean() {
		return val!=0;
	}
	
	public boolean equals(Object o) {
		return o.getClass().equals(NBTTagLong.class)&&val==((NBTTagLong)o).val;
	}
	
	public int hashCode() {
		return Hash.hashcode(val)*31+getTagType();
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_LONG;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Long.toString(val);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(val);
	}

	@Override
	public void read(DataInput in) throws IOException {
		val = in.readLong();
	}

}
