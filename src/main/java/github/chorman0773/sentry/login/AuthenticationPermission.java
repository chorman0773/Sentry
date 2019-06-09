package github.chorman0773.sentry.login;

import java.security.Permission;
import java.util.Arrays;
import java.util.Set;


public final class AuthenticationPermission extends Permission {
	private Set<String> scopes;
	private static final Set<String> allScopes = Set.of("launcher","game:*","general","read:game","write:game","delete:game","read:general","write:general","delete:general","stats:game","delete:account","change:sec","change:pass");
	public AuthenticationPermission(String name) {
		super("auth:"+name);
		if(name.equals("*"))
			scopes = allScopes;
		else
			scopes = Set.of(name.split(";"));
	}
	public AuthenticationPermission() {
		this("*");
	}

	@Override
	public boolean implies(Permission permission) {
		if(permission instanceof AuthenticationPermission) {
			AuthenticationPermission auth = (AuthenticationPermission)permission;
			if(scopes==null)
				return true;
			else if(scopes.contains("game:*")&&allScopes.stream().anyMatch(s->s.startsWith("game:")))
				return allScopes.parallelStream().allMatch(s->s.startsWith("game:")||scopes.contains(s));
			else
				return scopes.containsAll(auth.scopes);
			
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		else if(obj==this)
			return true;
		else if(!(obj instanceof AuthenticationPermission))
			return false;
		return scopes.equals(((AuthenticationPermission)obj).scopes);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return scopes.hashCode();
	}

	@Override
	public String getActions() {
		return String.join(";",scopes.stream().toArray(String[]::new));
	}

}
