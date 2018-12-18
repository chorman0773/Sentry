package github.chorman0773.sentry.save;

import github.chorman0773.sentry.save.RawSaveData.ListNode;
/**
 * Interface describing the ability to read/write a portion of Game Data in the Sentry Save Format
 * @author connor
 * @deprecated The Sentry Save Format has been replaced with ShadeNBT and CryptoShade, as a primary Save Format
 */
@Deprecated(forRemoval=true)
public interface Saveable {
void save(ListNode l);
void load(ListNode l);
}
