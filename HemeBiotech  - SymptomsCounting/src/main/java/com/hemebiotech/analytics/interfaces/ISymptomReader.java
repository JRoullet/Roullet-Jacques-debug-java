package com.hemebiotech.analytics.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * Defines a contract for reading symptom data from a data source.
 * Implementations return a raw list of symptoms, where duplicates
 * represent multiple occurrences.
 */
public interface ISymptomReader {

	/**
	 * Retrieves all symptoms from the data source.
	 * @return a list of symptom names, possibly containing duplicates
	 */
	List<String> getSymptoms() throws IOException;
}
