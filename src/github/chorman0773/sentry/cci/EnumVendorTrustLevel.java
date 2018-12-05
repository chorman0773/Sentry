package github.chorman0773.sentry.cci;

public enum EnumVendorTrustLevel {
	BLACKLISTED, BLOCKED, UNVERIFIED, TRUSTED, VERIFIED;
	public boolean isLocked() {
		return this==BLACKLISTED||this==VERIFIED;
	}
}
