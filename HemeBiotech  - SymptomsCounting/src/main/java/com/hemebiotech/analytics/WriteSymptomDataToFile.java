package com.hemebiotech.analytics;

import com.hemebiotech.analytics.interfaces.ISymptomWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Writes symptom data to a file.
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    private final String outputFile;

    /**
     * @param outputFile name of the output file
     */
    public WriteSymptomDataToFile(String outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error while writing symptom data", e);
        } finally {
            // Ensure the BufferedWriter is closed to release the file resource
            if (writer != null) {
                writer.close();
            }
        }
    }
}
