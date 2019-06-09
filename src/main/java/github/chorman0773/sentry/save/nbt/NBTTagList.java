package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTTagBase implements Iterable<NBTTagBase> {
	private List<NBTTagBase> tags = new ArrayList<>();
	private int listTagType;
	public NBTTagList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_LIST;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(tags.toArray());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(tags.size());
		out.writeByte(listTagType);
		for(NBTTagBase tag:tags)
			tag.write(out);
	}

	@Override
	public void read(DataInput in) throws IOException {
		int size = in.readInt();
		listTagType = in.readUnsignedByte();
		for(int i = 0;i<size;i++) {
			NBTTagBase tag = constructors[listTagType].get();
			tag.read(in);
			tags.add(tag);
		}
	}

	@Override
	public Iterator<NBTTagBase> iterator() {
		// TODO Auto-generated method stub
		return tags.iterator();
	}
	
	public boolean add(NBTTagBase b) {
		if(tags.size()==0) {
			tags.add(b);
			listTagType = b.getTagType();
			return true;
		}else if(listTagType==b.getTagType())
			return tags.add(b)||true;
		return false;
	}
	public NBTTagBase get(int idx) {
		return tags.get(idx);
	}
}
