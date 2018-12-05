package github.chorman0773.sentry.save.shade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;

import github.chorman0773.sentry.save.nbt.NBTConstants;
import github.chorman0773.sentry.save.nbt.NBTTagBase;
import github.chorman0773.sentry.save.nbt.NBTTagCompound;
import github.lightningcreations.lclib.security.SecurityUtils;
import github.lightningcreations.lclib.stream.LittleEndianDataInputStream;
import github.lightningcreations.lclib.stream.LittleEndianDataOutputStream;

public class ShadeFile {
	private ShadeHeader head;
	private NBTTagCompound comp;
	private final static SecureRandom rand = new SecureRandom();
	private final static MessageDigest digest;
	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	private ShadeFile(ShadeHeader head,NBTTagCompound comp) {
		this.head = head;
		this.comp = comp;
	}
	private static byte[] saltPassword(byte[] pwd,byte[] salt) {
		byte[] b = new byte[pwd.length+salt.length];
		System.arraycopy(pwd, 0, b, 0, pwd.length);
		System.arraycopy(salt, 0, b, pwd.length, salt.length);
		byte[] ret =digest.digest(b);
		try {
		SecurityUtils.destroy(b);
		}catch(DestroyFailedException e) {}
		return ret;
	}
	public static ShadeFile open(InputStream in,byte[] pwd) throws IOException, ShadeException {
		DataInput din = new DataInputStream(in);
		ShadeHeader head = new ShadeHeader((DataInputStream)din);
		if(head.isCrypto()) {
			try {
				byte[] blocks = new byte[head.getBlocks()*16];
				din.readFully(blocks, 0, blocks.length);
				SecretKey k = new SecretKeySpec(saltPassword(pwd,head.getSalt()),"AES");
				Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec ivSpec = new IvParameterSpec(head.getIv());
				c.init(Cipher.DECRYPT_MODE, k,ivSpec);
				blocks = c.doFinal(blocks);
				in = new ByteArrayInputStream(blocks);
				if(!head.isLittleEndian())
					din = new DataInputStream(in);
			}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				throw new ShadeException("Failed to read CryptoShade file",e);
			}
		}
		if(head.isLittleEndian())
			din = new LittleEndianDataInputStream(in);
		NBTTagBase tag = NBTTagBase.readFile(din);
		if(tag.getTagType()!=NBTConstants.TAG_COMPOUND)
			throw new ShadeException("Shade Requires TAG_COMPOUND");
		return new ShadeFile(head,(NBTTagCompound)tag);
	}
	public static ShadeFile newCrypto() {
		return new ShadeFile(new ShadeHeader(new ShadeHeader.Crypto(rand)),new NBTTagCompound());
	}
	public static ShadeFile newStandard() {
		return new ShadeFile(new ShadeHeader(),new NBTTagCompound());
	}
	
	public void write(OutputStream out,byte[] pwd) throws IOException, ShadeException {
		head.write(new DataOutputStream(out));
		if(head.isCrypto()) {
			try {
				ByteArrayOutputStream barrout = new ByteArrayOutputStream();
				if(head.isLittleEndian())
					NBTTagBase.writeFile(new LittleEndianDataOutputStream(barrout), comp);
				else
					NBTTagBase.writeFile(new DataOutputStream(barrout), comp);
				head.regenerateIv(rand);
				head.regenerateSalt(rand);
				SecretKey k = new SecretKeySpec(saltPassword(pwd,head.getSalt()),"AES");
				Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec ivSpec = new IvParameterSpec(head.getIv());
				c.init(Cipher.ENCRYPT_MODE, k,ivSpec);
				out.write(c.doFinal(barrout.toByteArray()));
			}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				throw new ShadeException("Failed to write CryptoShade File",e);
			}
		}
		else if(head.isLittleEndian())
			NBTTagBase.writeFile(new LittleEndianDataOutputStream(out), comp);
		else
			NBTTagBase.writeFile(new DataOutputStream(out), comp);
	}
	public NBTTagCompound getTagCompound() {
		return comp;
	}
	public boolean isEncrypted() {
		return head.isCrypto();
	}

}
