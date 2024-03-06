package io.github.amayaframework.events;

/**
 *
 */
public class UnsupportedFirePolicy extends IllegalStateException {
    private final FirePolicy policy;

    /**
     * @param message
     * @param policy
     */
    public UnsupportedFirePolicy(String message, FirePolicy policy) {
        super(message);
        this.policy = policy;
    }

    /**
     * @param policy
     */
    public UnsupportedFirePolicy(FirePolicy policy) {
        this("Unsupported fire policy: " + policy, policy);
    }

    /**
     * @return
     */
    public FirePolicy getPolicy() {
        return policy;
    }
}
