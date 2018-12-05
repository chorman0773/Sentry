package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import github.lightningcreations.lclib.Hash;

public class NBTTagDouble extends NBTPrimitive {

	public NBTTagDouble() {
		// TODO Auto-generated constructor stub
	}
	public NBTTagDouble(double val) {
		this.val = val;
	}

	private double val;

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
		return o.getClass().equals(NBTTagDouble.class)&&val==((NBTTagDouble)o).val;
	}
	
	public int hashCode() {
		return Hash.hashcode(val)*31+getTagType();
	}
	public int getTagType() {
		return TAG_DOUBLE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Double.toString(val);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(val);
	}

	@Override
	public void read(DataInput in) throws IOException {
		val = in.readDouble();
	}

}
