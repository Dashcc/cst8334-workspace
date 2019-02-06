package com.example.cst8334project.util;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A few utility methods pertaining to reading from and writing to files.
 */
public final class FileUtils {

    /**
     * Read the text from {@link File} identified by the given absolute path and return it as a String.
     *
     * @param absoluteFilePath the absolute path of the text file
     * @return a String that represents the text from the file
     */
    public static String readTextFromFile(String absoluteFilePath) {

        if (StringUtils.isBlank(absoluteFilePath)) {
            Log.w("FileUtils", "Cannot read a file with a null or empty absolute file path.");
            return null;
        }

        File file = new File(absoluteFilePath);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

        } catch (IOException e) {
            Log.e("FileUtils", "Error reading from file with path " + absoluteFilePath);
        }

        return stringBuilder.toString();
    }

    /**
     * Prevent instantiation.
     */
    private FileUtils() {
    }
}
