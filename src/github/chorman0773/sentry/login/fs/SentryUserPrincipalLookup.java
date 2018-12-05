package github.chorman0773.sentry.login.fs;

import java.io.IOException;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

class SentryUserPrincipalLookup extends UserPrincipalLookupService {
	class User implements UserPrincipal{

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "user";
		}
	}
	class Group implements GroupPrincipal{

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "user";
		}
		
	}
	public SentryUserPrincipalLookup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserPrincipal lookupPrincipalByName(String name) throws IOException {
		// TODO Auto-generated method stub
		return new User();
	}

	@Override
	public GroupPrincipal lookupPrincipalByGroupName(String group) throws IOException {
		// TODO Auto-generated method stub
		return new Group();
	}

}
