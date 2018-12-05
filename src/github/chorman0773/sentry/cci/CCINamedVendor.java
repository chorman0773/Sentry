package github.chorman0773.sentry.cci;

import java.security.PublicKey;
import java.util.UUID;

import github.chorman0773.sentry.cci.core.ICCICore;

class CCINamedVendor implements CCIVendor {

	private String name;
	private String id;

	public CCINamedVendor(String name,String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public boolean isVerifiable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verify(ICCICore core) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getVendorId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getVendorName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public PublicKey getPublicKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumVendorTrustLevel getTrustLevel() {
		// TODO Auto-generated method stub
		return EnumVendorTrustLevel.UNVERIFIED;
	}

	@Override
	public void trust() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void block() {
		// TODO Auto-generated method stub
		
	}

}
