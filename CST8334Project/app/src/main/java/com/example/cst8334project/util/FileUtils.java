package com.example.cst8334project.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.cst8334project.config.HeartHouseHospiceApp;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * A few utility methods pertaining to interacting with files.
 */
public final class FileUtils {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = FileUtils.class.getSimpleName();

    /**
     * Read the text from {@link File} identified by the given file name and return it as a String.
     *
     * @param fileName the name of the text file to read
     * @return a String that represents the text from the file
     */
    public static String readTextFromFile(String fileName) {
        // Method parameter validation
        if (StringUtils.isBlank(fileName)) {
            Log.w(CLASS_NAME, "Cannot read file with a null or empty file name.");
            return null;
        }

        Log.i(CLASS_NAME, "Received request to read text from file with name: " + fileName);

        StringBuilder fileContents = new StringBuilder();

        try (InputStream inputStream = HeartHouseHospiceApp.getAppContext().openFileInput(fileName);
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Error reading from file with name " + fileName, e);
            return null;
        }

        String result = fileContents.toString();
        Log.i(CLASS_NAME, "The text extracted from the file with name: " + fileName + " is \n" + result);
        return result;
    }

    /**
     * Creates a new file with the provided file name, or overwrites the file if it already exists,
     * and writes the given text to it.
     *
     * @param fileName     the name of the file
     * @param fileContents the text to write to the file
     */
    public static void writeTextToFile(String fileName, String fileContents) {
        // Method parameter validation
        if (StringUtils.isAnyBlank(fileName, fileContents)) {
            Log.e(CLASS_NAME, "Cannot write to file with a null or empty file name or file contents.");
            return;
        }

        try (Writer writer = new OutputStreamWriter(HeartHouseHospiceApp.getAppContext().openFileOutput(fileName, Context.MODE_PRIVATE))) {
            writer.write(fileContents);
            Log.i(CLASS_NAME, "Successfully wrote the following to file with name: " + fileName + "\n" + fileContents);
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Failed to write to file with name: " + fileName, e);
        }
    }

    /**
     * Delete a file identified by the given file name from the internal storage directory.
     *
     * @param fileName the name of the file to delete from the internal storage directory
     */
    public static void deleteFileFromStorage(String fileName) {
        // Method parameter validation
        if (StringUtils.isBlank(fileName)) {
            Log.e(CLASS_NAME, "Cannot delete file with null or empty file name.");
            return;
        }

        Log.i(CLASS_NAME, "Received request to delete file with name: " + fileName);

        boolean deleted = HeartHouseHospiceApp.getAppContext().deleteFile(fileName);

        Log.i(CLASS_NAME, (deleted ? "Successfully deleted" : "Failed to delete")
                + " file with file name: " + fileName);
    }

    /**
     * Get the absolute path to the directory on the filesystem where the file with the
     * given name is stored.
     *
     * @param fileName the name of the file
     * @return the absolute path to the directory on the filesystem where the file with the
     * given name is stored
     */
    public static String getAbsoluteFilePath(String fileName) {
        // Method parameter validation
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("Cannot get file path with null or empty file name.");
        }

        Log.i(CLASS_NAME, "Received request to get the absolute path for file with name: " + fileName);

        String absolutePath = HeartHouseHospiceApp.getAppContext().getFilesDir().getPath() + "/" + fileName;
        Log.i(CLASS_NAME, "The absolute path for file with file name: " + fileName
                + " is: " + absolutePath);
        return absolutePath;
    }

    /**
     * Determine the existence of the file with the given file name in the user's internal
     * storage directory.
     *
     * @param fileName the name of the file
     * @return {@code true} if the file identified by the given file name exists in the user's
     * internal storage directory, {@code false} otherwise
     */
    public static boolean fileExists(String fileName) {
        // Method parameter validation
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("Cannot determine existence of file with null or empty file name.");
        }

        Log.i(CLASS_NAME, "Received request to determine the existence of file with name: " + fileName);

        boolean fileExists = new File(getAbsoluteFilePath(fileName)).exists();

        Log.i(CLASS_NAME, "File with name: " + fileName + (fileExists ? " exists." : " does not exist."));
        return fileExists;
    }

    /**
     * Write the given key-value pair to the application's {@link SharedPreferences} file.
     *
     * @param key   the name of the preference to modify
     * @param value the new value for the preference
     */
    public static void writeToSharedPreferences(String key, String value) {
        // Method parameter validation
        if (StringUtils.isAnyBlank(key, value)) {
            Log.e(CLASS_NAME, "Cannot write to SharedPreferences with null/empty key or value");
            return;
        }

        Log.i(CLASS_NAME, "Writing to SharedPreferences with key/value of {" + key + " : " + value + "}.");
        Context context = HeartHouseHospiceApp.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * Retrieve the string value corresponding to the given key from the {@link SharedPreferences}
     * file for the application if it exists. Otherwise, the provided default value is returned.
     *
     * @param key          the name of the preference to retrieve
     * @param defaultValue the value that will be returned if the key does not exist
     * @return the preference value corresponding to the key if it exists, the provided default
     * value otherwise
     */
    public static String readFromSharedPreferences(String key, String defaultValue) {
        // Method parameter validation
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Cannot read from SharedPreferences with null or empty key.");
        }

        Log.i(CLASS_NAME, "Received request to read from SharedPreferences with key: " + key
                + " and default value: " + defaultValue);
        Context context = HeartHouseHospiceApp.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String result = sharedPreferences.getString(key, defaultValue);
        Log.i(CLASS_NAME, "Result from SharedPreferences for key: " + key + " is: " + result);
        return result;
    }

    /**
     * Prevent instantiation.
     */
    private FileUtils() {
    }
}
