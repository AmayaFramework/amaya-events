package io.github.amayaframework.events;

public class UnsupportedFirePolicy extends IllegalStateException {
    private final FirePolicy policy;

    public UnsupportedFirePolicy(String message, FirePolicy policy) {
        super(message);
        this.policy = policy;
    }

    public UnsupportedFirePolicy(FirePolicy policy) {
        this("Unsupported fire policy: " + policy, policy);
    }

    public FirePolicy getPolicy() {
        return policy;
    }
}
