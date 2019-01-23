package github.chorman0773.sentry.rollback;

import github.chorman0773.sentry.Timestamp;

/**
 * A legacy class provided for the purposes of storing a copy of the game-state so that it could be possible to revert to this copy.
 * One requirement necessitated by this, was that a deep-copy of the state is made otherwise its basically useless.
 * 
 * @author chorm
 *
 * @param <T> the type of the value
 * @deprecated this class is a legacy type, for the legacy Rollback API. It is intended that if the rollback API is used, a case-specific implementation is used. This class will be removed in a later version
 */
@Deprecated(forRemoval=true)
public class StateCopy<T> {
	public final T value;
	public final Timestamp time;
	public StateCopy(T value) {
		this.value = value;
		this.time = new Timestamp();
	}
}
