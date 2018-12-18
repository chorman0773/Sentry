package github.chorman0773.sentry.rollback;

import github.chorman0773.sentry.Timestamp;

public class StateCopy<T> {
	public final T value;
	public final Timestamp time;
	public StateCopy(T value) {
		this.value = value;
		this.time = new Timestamp();
	}
}
