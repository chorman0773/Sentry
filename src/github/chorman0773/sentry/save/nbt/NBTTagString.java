package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagString extends NBTTagBase {
	private String str;
	public NBTTagString() {
		// TODO Auto-generated constructor stub
	}
	public NBTTagString(String str) {
		this.str = str;
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_STRING;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(str);
	}

	@Override
	public void read(DataInput in) throws IOException {
		str = in.readUTF();
	}
}
