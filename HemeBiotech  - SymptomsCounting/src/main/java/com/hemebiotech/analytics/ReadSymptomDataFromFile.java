package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads symptom data from a text file.
 * Each line of the file is expected to contain a single symptom.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * Creates a reader for the given file path.
	 *
	 * @param filepath path to the file containing symptom data
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Reads all symptoms from the file.
	 *
	 * @return a list of symptoms read from the file
	 */
	@Override
	public List<String> getSymptoms() {
		ArrayList<String> result = new ArrayList<>();

		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
