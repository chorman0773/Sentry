package github.chorman0773.sentry.save;

import github.chorman0773.sentry.save.nbt.NBTTagCompound;

public interface NBTSerializable {
	void writeNBT(NBTTagCompound comp);
	void readNBT(NBTTagCompound comp);
	default NBTTagCompound serializeNBT() {
		NBTTagCompound write = new NBTTagCompound();
		writeNBT(write);
		return write;
	}
}
