package com.iafenvoy.neptune.util.function;

@FunctionalInterface
public interface Int2BooleanBiFunction {
    boolean applyAsBoolean(int a, int b);
}
