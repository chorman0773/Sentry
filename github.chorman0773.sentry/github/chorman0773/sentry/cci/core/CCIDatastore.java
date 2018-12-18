package github.chorman0773.sentry.cci.core;

import java.io.InputStream;

public interface CCIDatastore {
	byte[] datastoreHash();
	byte[] datastoreValue();
	byte[] datastoreSignature();
}
