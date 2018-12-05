package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;

import github.chorman0773.sentry.GameBasic;

public class GameFilesystemProvider extends FileSystemProvider {
	private SentryFS fs;
	public GameFilesystemProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return "game:/";
	}

	@Override
	public FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException {
		if(fs!=null)
			throw new IllegalStateException("FileSystem already created");
		if(uri.getScheme()=="game:/")
			return fs = new SentryFS(this,env);
		else
			throw new UnsupportedOperationException("Only game scheme URIs are valid");
	}

	@Override
	public FileSystem getFileSystem(URI uri) {
		// TODO Auto-generated method stub
		return fs;
	}

	@Override
	public Path getPath(URI uri) {
		// TODO Auto-generated method stub
		return fs.getPath(uri.getPath());
	}

	@Override
	public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
			throws IOException {
		try {
		return new SentryRemoteResourceByteChannel(fs.session,path);
		}catch(InterruptedException e) {
			throw new IOException(e);
		}
	}

	@Override
	public DirectoryStream<Path> newDirectoryStream(Path dir, Filter<? super Path> filter) throws IOException {
		return new SentryDirectoryStream(dir.toString(),fs,p->{try{return filter.accept(p);}catch(IOException e) {return false;}});
	}

	@Override
	public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
		fs.session.post("/fs/path"+dir+"/mkdir", null);
	}

	@Override
	public void delete(Path path) throws IOException {
		fs.session.delete("/fs/current"+path);
	}

	@Override
	public void copy(Path source, Path target, CopyOption... options) throws IOException {
		fs.session.post("/fs/path"+source+"/copy", target.toString());
	}

	@Override
	public void move(Path source, Path target, CopyOption... options) throws IOException {
		fs.session.post("/fs/path"+source+"/move", target.toString());

	}

	@Override
	public boolean isSameFile(Path path, Path path2) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHidden(Path path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileStore getFileStore(Path path) throws IOException {
		// TODO Auto-generated method stub
		return fs.store;
	}

	@Override
	public void checkAccess(Path path, AccessMode... modes) throws IOException {
	}

	@Override
	public <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {
		// TODO Auto-generated method stub

	}

}
