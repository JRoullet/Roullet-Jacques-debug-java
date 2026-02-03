package com.hemebiotech.analytics;

import com.hemebiotech.analytics.impl.ReadSymptomDataFromFile;
import com.hemebiotech.analytics.impl.WriteSymptomDataToFile;
import com.hemebiotech.analytics.interfaces.ISymptomReader;
import com.hemebiotech.analytics.interfaces.ISymptomWriter;
import com.hemebiotech.analytics.service.AnalyticsCounter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Processing orchestration
 */
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
        // -> Read -> Count -> Sort -> Write
        List<String> filedSymptoms = counter.getSymptoms();
        Map<String,Integer> countedSymptoms = counter.countSymptoms(filedSymptoms);
        Map<String,Integer> sortedSymptoms = counter.sortSymptoms(countedSymptoms);
        counter.writeSymptoms(sortedSymptoms);

        System.out.println("Les symptômes ont été analysés, comptés, ordonnés et inscrits dans le fichier : result.out");
    }
}