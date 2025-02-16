package com.iafenvoy.neptune.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Event<T> {
    private final List<T> listeners = new ArrayList<>();
    private final Function<List<T>, T> provider;

    protected Event(Function<List<T>, T> provider) {
        this.provider = provider;
    }

    public static <T> Event<T> of(Function<List<T>, T> provider) {
        return new Event<>(provider);
    }

    public void register(T listener) {
        this.listeners.add(listener);
    }

    public void unregister(T listener) {
        this.listeners.remove(listener);
    }

    public T invoker() {
        return this.provider.apply(this.listeners);
    }
}
