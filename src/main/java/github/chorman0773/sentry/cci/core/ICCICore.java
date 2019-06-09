package github.chorman0773.sentry.cci.core;

import github.chorman0773.sentry.cci.CCIVendor;

public interface ICCICore {
	CCIDatastore getDataStore();
	CCIVendor getVendor();
}
