package github.chorman0773.sentry.rollback;
/**
 * A  Rollable is an object that can be rolled back, a number of states and holds a current state.
 * @author Connor
 *
 * @param <R>
 */
public interface Rollable<R extends Rollable<R>> {
/**
 * Returns and/or updates the current state of the rollable
 * @return
 */
RollbackState<? extends R> currentState();
/**
 * Restores as many states as possible up to the number given in maxStates.
 * If the number of States exceeds maxStates, that many states are rolled back.
 * If the number of states is less than or equal to maxStates, the number of available states -1 are rolled back.
 * @param maxStates
 */
void rollback(int maxStates);
/**
 * Advances a state.
 */
void advance();
}
