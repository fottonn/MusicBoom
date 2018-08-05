package ru.bugmakers.mappers.enrichers;

/**
 * Created by Ayrat on 23.01.2018.
 */
public interface MBEnricher<S, T> {
    void enrich(S source, T target);
}
