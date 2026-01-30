package com.hemebiotech.analytics.interfaces;

import java.io.IOException;
import java.util.Map;

/**
 * Defines a contract for writing symptom data.
 */
public interface ISymptomWriter {

    /**
     * Writes symptoms and their occurrences to an output destination.
     *
     * @param symptoms a map containing symptoms as keys and their counts as values
     * @throws IOException if an error occurs while writing data
     */
    void writeSymptoms(Map<String, Integer> symptoms) throws IOException;
}
