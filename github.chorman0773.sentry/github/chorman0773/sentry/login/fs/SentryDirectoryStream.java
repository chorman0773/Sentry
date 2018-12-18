package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import github.chorman0773.sentry.GameBasic;

class SentryDirectoryStream implements DirectoryStream<Path> {
	private List<Path> subpaths;
	public SentryDirectoryStream(String target,SentryFS fs,Predicate<? super Path> p) {
		subpaths = Arrays.asList(Arrays.stream(((String)fs.session.query("dir/entries/"+target)).split(";"))
					.map(n->fs.getPath(n)).filter(p).toArray(Path[]::new));
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Path> iterator() {
		// TODO Auto-generated method stub
		return subpaths.iterator();
	}

}
