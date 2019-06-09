package github.chorman0773.sentry.cci;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;


import github.lightningcreations.lclib.security.SecurityUtils;

public class DestroyableByteArray implements Destroyable {
	private boolean destroyed;
	private byte[] data;
	public DestroyableByteArray(byte[] data) {
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.Destroyable#destroy()
	 */
	@Override
	public void destroy() throws DestroyFailedException {
		if(destroyed)
			throw new DestroyFailedException("Already Destroyed");
		SecurityUtils.destroy(data);
		destroyed = true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.Destroyable#isDestroyed()
	 */
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}
	
	public byte get(int i) {
		if(destroyed)
			throw new IllegalStateException("This Array has been Destroyed");
		return data[i];
	}
	
	

}
