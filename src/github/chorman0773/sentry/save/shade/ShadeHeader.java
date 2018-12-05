package github.chorman0773.sentry.save.shade;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import github.lightningcreations.lclib.Version;
class ShadeHeader implements ShadeConstants {
	private int magic;
	private Version ver;
	private int flags;
	private Crypto cryptoHeader;
	private Crypto crypt;
	private static final Version FLAGS_VERSION = new Version(1,0);
	
	static class Crypto{
		byte[] iv = new byte[16];
		byte[] salt = new byte[32];
		int blocks;
		Crypto(DataInputStream in)throws IOException{
			in.readFully(iv);
			in.readFully(salt);
			blocks = in.readInt();
		}
		Crypto(byte[] iv,byte[] salt){
			System.arraycopy(iv, 0, this.iv, 0, 16);
			System.arraycopy(salt, 0, this.salt, 0, 32);
		}
		Crypto(SecureRandom rand){
			rand.nextBytes(iv);
			rand.nextBytes(salt);
		}
	}
	/*
	 * Enforce BE Requirements in Header
	 */
	ShadeHeader(DataInputStream in) throws IOException,ShadeException{
		magic = in.readInt();
		if(magic!=MAGIC&&magic!=CRYPTO_MAGIC)
			throw new ShadeException("Failed to parse: Invalid Magic");
		ver = Version.read(in);
		if(ver.compareTo(SHADE_VERSION)>0)
			throw new ShadeException("Failed to parse: Version unsupported");
		if(FLAGS_VERSION.compareTo(ver)<=0)
			flags = in.readUnsignedByte();
		if(magic==CRYPTO_MAGIC)
			crypt = new Crypto(in);
		
	}
	ShadeHeader(){
		magic = MAGIC;
		ver = SHADE_VERSION;
		flags = 0;
	}
	ShadeHeader(Version v,int flags){
		magic = MAGIC;
		ver = v;
		this.flags = flags;
	}
	ShadeHeader(Crypto c){
		crypt = c;
		magic = CRYPTO_MAGIC;
		ver = SHADE_VERSION;
		flags =0;
	}
	ShadeHeader(Version v,int flags,Crypto c){
		ver = v;
		this.flags = flags;
		crypt = c;
		magic= CRYPTO_MAGIC;
	}
	void write(DataOutputStream out) throws IOException {
		out.writeInt(magic);
		ver.write(out);
		if(ver.compareTo(FLAGS_VERSION)>=0)
			out.writeByte(flags);
	}
	boolean isCrypto() {
		return magic==CRYPTO_MAGIC;
	}
	public int getBlocks() {
		if(isCrypto())
			return crypt.blocks;
		else
			return 0;
	}
	public void setBlocks(int blocks) {
		if(isCrypto())
			crypt.blocks = blocks;
	}
	public byte[] getIv() {
		byte[] ret = new byte[16];
		System.arraycopy(crypt.iv, 0, ret, 0, 16);
		return ret;
	}
	public byte[] getSalt() {
		return crypt.salt;
	}
	public void regenerateSalt(SecureRandom rand) {
		rand.nextBytes(crypt.salt);
	}
	public void regenerateIv(SecureRandom rand) {
		rand.nextBytes(crypt.iv);
	}
	public boolean isLittleEndian() {
		return ver.compareTo(FLAGS_VERSION)<0||(flags&0x80)!=0;
	}

}
