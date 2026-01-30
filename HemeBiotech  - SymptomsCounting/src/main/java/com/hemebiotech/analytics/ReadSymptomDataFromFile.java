package com.hemebiotech.analytics;

import com.hemebiotech.analytics.interfaces.ISymptomReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads symptom data from a text file.
 * Each line of the file is expected to contain a single symptom.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	/**
	 * Creates a reader for the given InputStream.
	 *
	 * @param is input stream used to read symptom data
	 */
	private final InputStream is;

	public ReadSymptomDataFromFile(InputStream is) {
		this.is = is;
	}

	/**
	 * Reads all symptoms from the file.
	 *
	 * @return a list of symptoms read from the file
	 */
	@Override
	public List<String> getSymptoms() throws IOException {
		ArrayList<String> result = new ArrayList<>();

		if (is != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				String line = reader.readLine();

				while (line != null) {
					// Normalize input and ignore empty lines
					line = line.trim();
					if (!line.isBlank()) {
						result.add(line);
					}
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				throw new IOException("Error while getting symptom data", e);
			}
		}

		return result;
	}
}
