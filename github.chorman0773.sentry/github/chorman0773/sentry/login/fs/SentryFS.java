package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.login.Session;

class SentryFS extends FileSystem {
	private GameFilesystemProvider provider;
	private boolean open = true;
	SentryFileStore store;
	private Set<FileStore> stores;
	Session session;
	public SentryFS(GameFilesystemProvider prov,Map<String,?> env) {
		@SuppressWarnings("unchecked")
		Map<String,Object> menv = (Map<String,Object>)env;
		provider = prov;
		this.store = new SentryFileStore(this);
		stores = Set.of(store);
		session = (Session) menv.getOrDefault("SentryAccountSession", GameBasic.getInstance().getSession());
	}

	@Override
	public FileSystemProvider provider() {
		// TODO Auto-generated method stub
		return provider;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		open = false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return open;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSeparator() {
		// TODO Auto-generated method stub
		return "/";
	}

	@Override
	public Iterable<Path> getRootDirectories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<FileStore> getFileStores() {
		// TODO Auto-generated method stub
		return stores;
	}

	@Override
	public Set<String> supportedFileAttributeViews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getPath(String first, String... more) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathMatcher getPathMatcher(String syntaxAndPattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPrincipalLookupService getUserPrincipalLookupService() {
		// TODO Auto-generated method stub
		return new SentryUserPrincipalLookup();
	}

	@Override
	public WatchService newWatchService() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
