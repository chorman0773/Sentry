package github.chorman0773.sentry.save.nbt;

import github.lightningcreations.lclib.Hash;

public abstract class NBTPrimitive extends NBTTagBase {
	public abstract int getInt();
	public float getFloat() {
		return getInt();
	}
	public double getDouble() {
		return getInt();
	}
	public long getLong() {
		return getInt();
	}
	public byte getByte() {
		return (byte)getInt();
	}
	public short getShort() {
		return (short)getInt();
	}
	public boolean getBoolean() {
		return getInt()!=0;
	}
	public int hashCode() {
		return Hash.hashcode(getInt())*31+super.hashCode();
	}
	public boolean equals(Object o) {
		return super.equals(o)&&getInt()==((NBTPrimitive)o).getInt();
	}
}
