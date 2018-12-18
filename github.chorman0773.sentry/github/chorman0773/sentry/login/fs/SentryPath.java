package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.util.List;

import github.chorman0773.sentry.GameBasic;

class SentryPath implements Path {
	private SentryFS fs;
	private String[] parts;
	private boolean abs;
	SentryPath(SentryFS fs,String[] parts,boolean absolute) {
		this.fs = fs;
		this.parts = parts;
		this.abs = absolute;
	}

	@Override
	public FileSystem getFileSystem() {
		// TODO Auto-generated method stub
		return fs;
	}

	@Override
	public boolean isAbsolute() {
		// TODO Auto-generated method stub
		return abs;
	}

	@Override
	public Path getRoot() {
		if(parts.length!=0)
			return new SentryPath(fs,new String[]{parts[0]},abs);
		else
			return null;
	}

	@Override
	public Path getFileName() {
		if(parts.length!=0)
			return new SentryPath(fs,new String[]{parts[parts.length-1]},false);
		else
			return null;
	}

	@Override
	public Path getParent() {
		if(parts.length!=0) {
			String[] nparts = new String[parts.length-1];
			System.arraycopy(parts, 0, nparts, 0, nparts.length);
			return new SentryPath(fs,nparts,abs);
		}
		return null;
	}

	@Override
	public int getNameCount() {
		// TODO Auto-generated method stub
		return parts.length;
	}

	@Override
	public Path getName(int index) {
		// TODO Auto-generated method stub
		return new SentryPath(fs,new String[] {parts[index]},index==0);
	}

	@Override
	public Path subpath(int beginIndex, int endIndex) {
		if(parts.length!=0) {
			String[] nparts = new String[endIndex-beginIndex];
			System.arraycopy(parts, beginIndex, nparts, 0, nparts.length);
			return new SentryPath(fs,nparts,abs);
		}
		return null;
	}

	@Override
	public boolean startsWith(Path other) {
		return false;//TODO
	}

	@Override
	public boolean endsWith(Path other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Path normalize() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Path resolve(Path other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path relativize(Path other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI toUri() {
		// TODO Auto-generated method stub
		return URI.create((String)fs.session.query("fs/path/uri/"+String.join("/", parts)));
	}

	@Override
	public Path toAbsolutePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path toRealPath(LinkOption... options) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WatchKey register(WatchService watcher, Kind<?>[] events, Modifier... modifiers) throws IOException {
		
		return null;
	}

	@Override
	public int compareTo(Path other) {
		// TODO Auto-generated method stub
		return 0;
	}

}
