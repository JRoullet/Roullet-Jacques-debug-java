package com.hemebiotech.analytics;

import com.hemebiotech.analytics.interfaces.ISymptomReader;
import com.hemebiotech.analytics.interfaces.ISymptomWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Core business logic class responsible for symptom analysis.
 *
 * This class provides the operations required to process symptom data:
 * counting occurrences, sorting results, and delegating input/output
 * operations to dedicated abstractions.
 *
 * It relies only on interfaces to remain loosely coupled from
 * concrete implementations.
 */
public class AnalyticsCounter {

	/**
	 * Reader abstraction used to retrieve raw symptom data.
	 */
    private final ISymptomReader symptomReader;

	/**
	 * Writer abstraction used to output processed symptom data.
	 */
    private final ISymptomWriter symptomWriter;

	/**
	 * Constructs an AnalyticsCounter with its required dependencies.
	 *
	 * @param symptomReader reader used to retrieve symptom data
	 * @param symptomWriter writer used to persist processed results
	 */
	public AnalyticsCounter(ISymptomReader symptomReader, ISymptomWriter symptomWriter) {
        this.symptomReader = symptomReader;
        this.symptomWriter = symptomWriter;
    }

	/**
	 * Retrieves the raw list of symptoms.
	 * This method delegates the reading responsibility to the ISymptomReader.
	 *
	 * @return a list of symptoms, possibly containing duplicates
	 * @throws IOException if reading fails
	 */
	public List<String> getSymptoms() throws IOException {
		return symptomReader.getSymptoms();
	}

	/**
	 * Counts the occurrences of each symptom in the provided list.
	 *
	 * @param symptoms list of raw symptoms
	 * @return an unordered map containing each symptom and its occurrence count
	 */
	public Map<String, Integer> countSymptoms(List<String> symptoms){
		Map<String, Integer> occurrences = new HashMap<>();
		for (String s : symptoms ){
			// if key is absent, adds it and attributes value : 1 otherwise, sum (1 + previous value)
			occurrences.merge(s, 1, Integer::sum);
		}
		return occurrences;
	}

	/**
	 * Sorts the symptoms alphabetically.
	 *
	 * @param symptoms map of symptoms and their occurrences
	 * @return a sorted map ordered by symptom name
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		return new TreeMap<>(symptoms);
	}

	/**
	 * Writes the processed symptom data using the ISymptomWriter.
	 *
	 * @param symptoms map of sorted symptoms and their occurrences
	 * @throws IOException if writing fails
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
		symptomWriter.writeSymptoms(symptoms);
	}

}
