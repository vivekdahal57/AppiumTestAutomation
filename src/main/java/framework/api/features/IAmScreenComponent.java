package framework.api.features;

/**
 * Represents a LIVE screen component where events, interactions & LIVE
 * state query can happen.
 *
 * @author bibdahal
 */
public interface IAmScreenComponent {

    /**
     * Checks if this component is fully loaded. Each web component should
     * provide it's own logic of being fully loaded. If returned true then this
     * component is ready for any events, interactions & LIVE query.
     *
     * @return
     */
    public boolean isFullyLoaded();

    /**
     * Wait till this component is fully loaded or till the given time out
     * period expires.
     *
     * @param waitTimeInSecs
     * @return
     */
    public IAmScreenComponent doWaitTillFullyLoaded(long waitTimeInSecs);

}
