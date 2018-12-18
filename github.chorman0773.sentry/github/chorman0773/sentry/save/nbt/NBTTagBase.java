package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * Base Tag of the NBT Format.
 * The NBT Format is defined by Markus Person. This implementation follows Version 13313 of the Specification.
 * @author connor
 */
public abstract class NBTTagBase implements NBTConstants {
	@SuppressWarnings("unchecked")
	protected static final Supplier<NBTTagBase>[] constructors = (Supplier<NBTTagBase>[]) new Supplier[] {
			NBTTagEnd::new,
			NBTTagByte::new,
			NBTTagShort::new,
			NBTTagInt::new,
			NBTTagLong::new,
			NBTTagFloat::new,
			NBTTagDouble::new,
			NBTTagByteArray::new,
			NBTTagString::new,
			NBTTagList::new,
			NBTTagCompound::new,
			NBTTagIntArray::new,
			NBTTagLongArray::new
	};
	
	public NBTTagBase() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract int getTagType();
	public abstract String toString();
	public abstract void write(DataOutput out)throws IOException;
	public abstract void read(DataInput in)throws IOException;
	
	public int hashCode() {
		return getTagType();
	}
	public boolean equals(Object o) {
		return o.getClass().equals(this.getClass());
	}
	public static void writeFile(DataOutput out,NBTTagBase tag)throws IOException{
		NBTTagCompound comp = new NBTTagCompound();
		comp.setTag("", tag);
		comp.write(out);
	}
	public static NBTTagBase readFile(DataInput in)throws IOException{
		NBTTagCompound comp = new NBTTagCompound();
		comp.read(in);
		return (NBTTagBase)comp.values().toArray()[0];
	}
	
}
