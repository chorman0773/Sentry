package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import github.lightningcreations.lclib.Iterators;
import github.lightningcreations.lclib.primitive.ByteIterable;
import github.lightningcreations.lclib.primitive.ByteIterator;

public class NBTTagByteArray extends NBTTagBase implements ByteIterable{
	private byte[] array;
	public NBTTagByteArray() {}
	public NBTTagByteArray(int val) {
		array = new byte[val];
	}
	public NBTTagByteArray(byte[] b) {
		array = Arrays.copyOf(b, b.length);
	}
	public NBTTagByteArray(int[] b) {
		array = new byte[b.length];
		for(int i =0;i<b.length;i++)
			array[i] = (byte)b[i];
	}
	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_BYTE_ARRAY;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "B"+Arrays.toString(array);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(array.length);
		out.write(array);
	}

	@Override
	public void read(DataInput in) throws IOException {
		int len = in.readInt();
		array = new byte[len];
		in.readFully(array);
	}
	
	public byte get(int i) {
		return array[i];
	}
	public int getUnsigned(int i) {
		return array[i]&0xff;
	}
	public void set(int i,byte b) {
		array[i] = b;
	}
	public void set(int i,int v) {
		array[i] = (byte)v;
	}
	public byte[] getArrayCopy() {
		return Arrays.copyOf(array, array.length);
	}
	public int[] getUnsignedArrayCopy() {
		int[] ret = new int[array.length];
		for(int i = 0;i<array.length;i++)
			ret[i] = array[i]&0xff;
		return ret;
	}
	@Override
	public ByteIterator iterator() {
		return Iterators.over(array);
	}
}
