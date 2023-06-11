/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

/**
 * The HitNotifier interface provides methods for managing HitListener objects
 * and notifying them of hit events.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}