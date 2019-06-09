package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagEnd extends NBTTagBase {

	public NBTTagEnd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_END;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void write(DataOutput out) throws IOException {}

	@Override
	public void read(DataInput in) throws IOException {}

}
