package com.hemebiotech.analytics;

import com.hemebiotech.analytics.Interface.ISymptomReader;
import com.hemebiotech.analytics.Interface.ISymptomWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Main application class.
 * This class reads symptom data from a text file, counts occurrences
 * of specific symptoms, and writes the results to an output file.
 */
public class AnalyticsCounter {

	// Dependencies injection of interfaces  => loosely coupled, instead of instances
    private final ISymptomReader symptomReader;
    private final ISymptomWriter symptomWriter;

	public AnalyticsCounter(ISymptomReader symptomReader, ISymptomWriter symptomWriter) {
        this.symptomReader = symptomReader;
        this.symptomWriter = symptomWriter;
    }
	/**
	 * Delegate reading to the ISymptomReader
	 */
	public List<String> getSymptoms(){
		return symptomReader.getSymptoms();
	}

	/**
	 * Counts occurrences of each symptom from the list. Returns an unordered map
	 * @param symptoms
	 * @return Map - unordered
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
	 * Returns a new TreeMap containing the symptoms sorted alphabetically by key
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		return new TreeMap<>(symptoms);
	}

	/**
	 * Delegate writing to the ISymptomWriter
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
		symptomWriter.writeSymptoms(symptoms);
	}


	/**
	 * Application entry point.
	 *
	 * @param args command-line arguments (not used)
	 * @throws Exception if an unrecoverable error occurs
	 */
	public static void main(String args[]) throws Exception {

		/**
		 Load the symptoms file from the classpath.
		 */
		InputStream is = AnalyticsCounter
				.class
				.getClassLoader()
				.getResourceAsStream("symptoms.txt");

		if (is == null) {
			throw new IllegalStateException("symptoms.txt not found in classpath");
		}

		/**
		 * Read the file line by line and count symptom occurrences.
		 */
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is, StandardCharsets.UTF_8)
		);

		String line = reader.readLine();
	}
}
