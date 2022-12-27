package org.bluesoft.alternatives;

import jakarta.enterprise.inject.Alternative;

@Alternative
class Simple implements Rules {

    private int maxAttempts = 6;
    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }
}
