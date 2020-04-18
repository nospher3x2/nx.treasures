package com.redenexus.nospher.treasures.util.builder;

import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author oNospher
 **/
public abstract class CacheBuilder<T> {

    @Getter
    public List<T> elements = new LinkedList<>();

    public abstract <V> T getElement(V value);

    public boolean contains(T element) {
        return elements.contains(element);
    }

    public void addElement(T element) {
        elements.add(element);
    }

    public boolean removeElement(T element) {
        return elements.remove(element);
    }

    public T findOne(Predicate<T> predicate) {
        return elements.stream()
                .filter(Objects::nonNull)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public ImmutableList<T> toImmutable() {
        return ImmutableList.copyOf(elements);
    }

    public Iterator<T> iterator() {
        return elements.iterator();
    }

    public int size() {
        return elements.size();
    }
}
