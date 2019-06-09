package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import github.lightningcreations.lclib.Iterators;
import github.lightningcreations.lclib.primitive.IntIterable;
import github.lightningcreations.lclib.primitive.IntIterator;

public class NBTTagIntArray extends NBTTagBase implements IntIterable {
	private int[] array;
	public NBTTagIntArray() {
		// TODO Auto-generated constructor stub
	}
	public NBTTagIntArray(int i) {
		array = new int[i];
	}
	public NBTTagIntArray(int[] i) {
		array = new int[i.length];
		System.arraycopy(i, 0, array, 0, i.length);
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_INT_ARRAY;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "I"+Arrays.toString(array);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(array.length);
		for(int i:array)
			out.writeInt(i);
	}

	@Override
	public void read(DataInput in) throws IOException {
		int len = in.readInt();
		array = new int[len];
		for(int i = 0;i<len;i++)
			array[i] = in.readInt();
	}
	public int get(int idx) {
		return array[idx];
	}
	public void set(int idx,int val) {
		array[idx] = val;
	}
	
	public int[] getArrayCopy() {
		return Arrays.copyOf(array, array.length);
	}
	public IntIterator iterator() {
		return Iterators.over(array);
	}

}
