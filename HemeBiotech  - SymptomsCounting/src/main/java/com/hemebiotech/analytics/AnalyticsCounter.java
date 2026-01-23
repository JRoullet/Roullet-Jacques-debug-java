package com.hemebiotech.analytics;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Main application class.
 * This class reads symptom data from a text file, counts occurrences
 * of specific symptoms, and writes the results to an output file.
 */
public class AnalyticsCounter {

	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int pupilCount = 0;

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

		while (line != null) {
			if (line.equals("headache")) {
				headacheCount++;
			} else if (line.equals("rush")) {
				rashCount++;
			} else if (line.contains("pupils")) {
				pupilCount++;
			}
			line = reader.readLine();
		}

		/**
		 * Write the results to the output file.
		 */
		FileWriter writer = new FileWriter("result.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + pupilCount + "\n");
		writer.close();
	}
}
