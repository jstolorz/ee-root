package org.bluesoft.alternatives;

import jakarta.inject.Named;

import java.io.Serializable;

@Named
public interface Rules  extends Serializable {
    public int getMaxAttempts();
}
