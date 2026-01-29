package com.hemebiotech.analytics;

import com.hemebiotech.analytics.Interface.ISymptomReader;
import com.hemebiotech.analytics.Interface.ISymptomWriter;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        // Load the symptoms file from the classpath
        InputStream is = Main.class.getClassLoader().getResourceAsStream("symptoms.txt");

        if (is == null) {
            throw new IllegalStateException("symptoms.txt not found in classpath");
        }

        // Instantiate reader and writer implementations
        ISymptomReader reader = new ReadSymptomDataFromFile(is);
        ISymptomWriter writer = new WriteSymptomDataToFile("result.out");

        // Create the analytics service
        AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

        // Execute the application workflow
        counter.writeSymptoms(
                counter.sortSymptoms(
                        counter.countSymptoms(
                                counter.getSymptoms())));

        System.out.println("Les symptômes ont été analysés, comptés, ordonnés et inscrits dans le fichier : result.out");
    }
}