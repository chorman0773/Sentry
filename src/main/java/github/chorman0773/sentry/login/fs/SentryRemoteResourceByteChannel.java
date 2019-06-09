package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;

import github.chorman0773.sentry.login.Session;

class SentryRemoteResourceByteChannel implements SeekableByteChannel {
	private boolean open;
	private int fd;
	private Session s;
	private Path p;
	public SentryRemoteResourceByteChannel(Session s,Path p) throws InterruptedException {
		while((Boolean)s.post("/fs/resource/lock", p.toString()))
			Thread.sleep(1000);
		fd = Integer.parseInt((String)s.post("/fs/fd/open", p.toString()));
		open = true;
		this.s = s;
		this.p = p;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return open;
	}

	@Override
	public void close() throws IOException {
		s.post("/fs/fd/close",fd);
		s.post("/fs/resource/unlock", p.toString());
	}

	@Override
	public int read(ByteBuffer dst) throws IOException {
		return s.get("/fs/fd/"+fd+"/read", dst);
	}

	@Override
	public int write(ByteBuffer src) throws IOException {
		return (Integer)s.post("/fs/fd/"+fd+"/write", src);
	}

	@Override
	public long position() throws IOException {
		// TODO Auto-generated method stub
		return Long.parseLong((String)s.query("/fs/fd/"+fd+"/position"));
	}

	@Override
	public SeekableByteChannel position(long newPosition) throws IOException {
		s.post("/fs/fd/"+fd+"/position", String.valueOf(newPosition));
		return this;
	}

	@Override
	public long size() throws IOException {
		// TODO Auto-generated method stub
		return Long.parseLong((String)s.query("/fs/fd/"+fd+"/size"));
	}

	@Override
	public SeekableByteChannel truncate(long size) throws IOException {
		s.post("/fs/fd/"+fd+"/size", size);
		return this;
	}

}
