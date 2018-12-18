package github.chorman0773.sentry.rollback;
/**
 * A RollbackState is a State of a Rollable. The State needs access to the rollable,
 * and should most likely be a Inner Class of the Rollable. 
 * @author Connor
 *
 * @param <R> Restricts this RollbackState to a type of Rollable
 */
public interface RollbackState<R extends Rollable<R>> {
void rollback();
}
