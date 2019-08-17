package github.chorman0773.sentry.authorization;

/**
 * List of Sentry Scopes
 * @author chorm
 *
 */
public interface Scopes {
	
	String GENERAL = "general";
	
	public static interface Info{
		String READ = "info:read",
				WRITE = "info:write",
				READ_EMAIL = "email:read",
				WRITE_EMAIL = "email:write";
	}
	
	public static interface Game{
		String GAME = "game",
				READ_GAME = "game:read",
				WRITE_GAME = "game:write",
				DELETE_GAME = "game:delete",
				STATS_GAME = "game:stats";
	}
	
	public static interface Security{
		String SECURITY_READ = "security:read",
				SECURITY_WRITE = "security:write";
	}
	
	public static interface Session{
		String ISSUE = "session:issue",
				ELEVATE = "session:elevate",
				DELETE = "session:delete";
	}
	
	public static interface Account{
		String READ = "account:read",
				WRITE = "account:write",
				DELETE = "account:dummy_delete",
				ACTUAL_DELETE = "account:delete";
	}
	
}
