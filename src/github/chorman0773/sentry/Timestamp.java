package github.chorman0773.sentry;

import java.security.SecureRandom;
import java.util.UUID;

public final class Timestamp {
	private static SecureRandom uuidRandom = new SecureRandom();
	private final long timeMilis;
	private UUID ret;
	public Timestamp() {
		timeMilis = System.currentTimeMillis()&0xfffffffffffffffL;
	}
	public Timestamp(final long time){
		timeMilis = time;
	}
	
	public UUID getUUID(){
		if(ret==null){
		long mostSig = timeMilis&0xffffffff<<32+(timeMilis>>32)&0xffff<<16+1<<4+(timeMilis>>48)&0xfff;
		long leastSig = 0x21ce01<<20+(uuidRandom.nextLong()&0x7fffffffffL);
		ret = new UUID(mostSig,leastSig);
		}
		
		return ret;
	}
	public static final UUID getTimebasedUUID(){
		return new Timestamp().getUUID();
	}

}
