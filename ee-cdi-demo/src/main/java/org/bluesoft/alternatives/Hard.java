package org.bluesoft.alternatives;

import jakarta.enterprise.inject.Alternative;

@Alternative
class Hard implements Rules {

    private int maxAttempts = 3;

    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }
}
