package github.chorman0773.sentry.save.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import github.lightningcreations.lclib.Version;

public class NBTTagCompound extends NBTTagBase {
	private Map<String,NBTTagBase> underlying = new TreeMap<>();
	public NBTTagCompound() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTagType() {
		// TODO Auto-generated method stub
		return TAG_COMPOUND;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("{");
		String sep = "";
		for(String s:underlying.keySet()) {
			ret.append(sep);
			sep = ", ";
			ret.append(s);
			ret.append(":");
			ret.append(underlying.get(s));
		}
		return ret.append("}").toString();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		for(String s:underlying.keySet()) {
			NBTTagBase tag = underlying.get(s);
			out.writeByte(tag.getTagType());
			out.writeUTF(s);
			tag.write(out);
		}
		out.writeByte(0);
	}

	@Override
	public void read(DataInput in) throws IOException {
		int type;
		while((type = in.readUnsignedByte())!=0) {
			String name = in.readUTF();
			NBTTagBase tag = constructors[type].get();
			tag.read(in);
			underlying.put(name, tag);
		}
	}
	public boolean hasTag(String name) {
		return underlying.containsKey(name);
	}
	public boolean hasTag(String name,int tagType) {
		if(!hasTag(name))
			return false;
		else if(tagType==TAG_ANY_NUMERIC)
			return underlying.get(name) instanceof NBTPrimitive;
		else
			return underlying.get(name).getTagType()==tagType;
	}
	public NBTTagBase get(String name) {
		return underlying.get(name);
	}
	public void setTag(String name,NBTTagBase tag) {
		underlying.put(name, tag);
	}
	private NBTPrimitive getPrimitive(String name) {
		return (NBTPrimitive)get(name);
	}
	public NBTTagCompound getTagCompound(String name) {
		if(hasTag(name,TAG_COMPOUND))
			return (NBTTagCompound)get(name);
		else if(!hasTag(name)) {
			underlying.put(name, new NBTTagCompound());
			return (NBTTagCompound) get(name);
		}else
			return null;
	}
	public NBTTagList getList(String name) {
		if(hasTag(name,TAG_LIST)) 
			return (NBTTagList)get(name);
		else if(!hasTag(name)) {
			underlying.put(name, new NBTTagCompound());
			return (NBTTagList)get(name);
		}else
			return null;
	}
	public int getInt(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getInt();
		else
			return 0;
	}
	public byte getByte(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getByte();
		else
			return 0;
	}
	public short getShort(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getShort();
		else
			return 0;
	}
	public long getLong(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getShort();
		else
			return 0;
	}
	public float getFloat(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getFloat();
		else
			return 0;
	}
	public double getDouble(String name) {
		if(hasTag(name,TAG_ANY_NUMERIC))
			return getPrimitive(name).getDouble();
		else
			return 0;
	}
	public boolean getBoolean(String name) {
		return hasTag(name,TAG_ANY_NUMERIC)&&getPrimitive(name).getBoolean();
	}
	public UUID getUUID(String name) {
		return new UUID(getLong(name+"Most"),getLong(name+"Least"));
	}
	public Version getVersion(String name) {
		return new Version(getShort(name));
	}
	public String getString(String name) {
		if(hasTag(name,TAG_STRING))
			return get(name).toString();
		else
			return "";
	}
	public void setInt(String name,int i) {
		setTag(name,new NBTTagInt(i));
	}
	public void setLong(String name,long l) {
		setTag(name,new NBTTagLong(l));
	}
	public void setByte(String name,byte b) {
		setTag(name,new NBTTagByte(b));
	}
	public void setShort(String name,short s) {
		setTag(name,new NBTTagShort(s));
	}
	public void setFloat(String name,float f) {
		setTag(name,new NBTTagFloat(f));
	}
	public void setDouble(String name,double d) {
		setTag(name,new NBTTagDouble(d));
	}
	public void setBoolean(String name,boolean b) {
		setTag(name,new NBTTagByte((byte)(b?1:0)));
	}
	public void setVersion(String name,Version v) {
		setTag(name,new NBTTagShort((short)v.getEncoded()));
	}
	public void setUUID(String name,UUID id) {
		setLong(name+"Most",id.getMostSignificantBits());
		setLong(name+"Least",id.getLeastSignificantBits());
	}
	public Set<String> getKeys(){
		return underlying.keySet();
	}
	public Collection<NBTTagBase> values(){
		return underlying.values();
	}

	public void setString(String name, String str) {
		setTag(name,new NBTTagString(str));
	}
	
	public void setInstant(String name,Instant i) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setLong("Seconds", i.getEpochSecond());
		comp.setInt("Nanos", i.getNano());
		setTag(name,comp);
	}
	public void setDuration(String name,Duration d) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setLong("Seconds", d.getSeconds());
		comp.setInt("Nanos", d.getNano());
		setTag(name,comp);
	}
	public Instant getInstant(String name) {
		NBTTagCompound comp = getTagCompound(name);
		return Instant.ofEpochSecond(comp.getLong("Seconds"), comp.getInt("Nanos"));
	}
	public Duration getDuration(String name) {
		NBTTagCompound comp = getTagCompound(name);
		return Duration.ofSeconds(comp.getLong("Seconds"), comp.getInt("Nanos"));
	}
	public void setByteArray(String name,byte[] b) {
		setTag(name,new NBTTagByteArray(b));
	}
	public void setByteArray(String name,int[] ub) {
		setTag(name,new NBTTagByteArray(ub));
	}
	public void setIntArray(String name,int[] i) {
		setTag(name,new NBTTagIntArray(i));
	}
	public void setLongArray(String name,long[] l) {
		setTag(name,new NBTTagLongArray(l));
	}
	public byte[] getByteArray(String name) {
		if(!hasTag(name,TAG_BYTE_ARRAY))
			return new byte[0];
		else 
			return ((NBTTagByteArray)get(name)).getArrayCopy();
	}
	public int[] getUnsignedByteArray(String name) {
		if(!hasTag(name,TAG_BYTE_ARRAY))
			return new int[0];
		else
			return ((NBTTagByteArray)get(name)).getUnsignedArrayCopy();
	}
	public int[] getIntArray(String name) {
		if(!hasTag(name,TAG_INT_ARRAY))
			return new int[0];
		else
			return ((NBTTagIntArray)get(name)).getArrayCopy();
	}
	public long[] getLongArray(String name) {
		if(!hasTag(name,TAG_LONG_ARRAY))
			return new long[0];
		else
			return ((NBTTagLongArray)get(name)).getArrayCopy();
	}
}
