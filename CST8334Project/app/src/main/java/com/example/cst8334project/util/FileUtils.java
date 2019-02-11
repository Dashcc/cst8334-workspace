package com.example.cst8334project.util;

import android.content.Context;
import android.util.Log;

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
     * @param context  the {@link Context} of the application
     * @param fileName the name of the text file to read
     * @return a String that represents the text from the file
     */
    public static String readTextFromFile(Context context, String fileName) {
        // Method parameter validation
        if (context == null || StringUtils.isBlank(fileName)) {
            Log.w(CLASS_NAME, "Cannot read a file with a null context or null/empty file name.");
            return null;
        }

        Log.i(CLASS_NAME, "Received request to read text from file with name: " + fileName);

        StringBuilder fileContents = new StringBuilder();

        try (InputStream inputStream = context.openFileInput(fileName)) {
            if (inputStream == null) {
                Log.e(CLASS_NAME, "File with name " + fileName + " does not exist.");
                return null;
            }

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Error reading from file with name " + fileName, e);
            return null;
        }

        String result = fileContents.toString();
        Log.i(CLASS_NAME, "The text extracted from the file with file name: " + fileName + " is \n" + result);
        return result;
    }

    /**
     * Creates a new file with the provided file name and writes the given text to it.
     *
     * @param context      the {@link Context} of the application
     * @param fileName     the name of the file
     * @param fileContents the text to write to the file
     */
    public static void writeTextToFile(Context context, String fileName, String fileContents) {
        // Method parameter validation
        if (context == null || StringUtils.isAnyBlank(fileName, fileContents)) {
            Log.e(CLASS_NAME, "Cannot write to file with null context or null/empty file name or file contents.");
            return;
        }

        try (Writer writer = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))) {
            writer.write(fileContents);
            Log.i(CLASS_NAME, "Successfully wrote the following to file with name: " + fileName + "\n" + fileContents);
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Failed to write to file with file name: " + fileName, e);
        }
    }

    /**
     * Delete a file identified by the given file name from the internal storage directory.
     *
     * @param context  the {@link Context} of the application
     * @param fileName the name of the file to delete from the internal storage directory
     */
    public static void deleteFileFromStorage(Context context, String fileName) {
        // Method parameter validation
        if (context == null || StringUtils.isBlank(fileName)) {
            Log.e(CLASS_NAME, "Cannot delete file with null context or null/empty file name.");
            return;
        }

        Log.i(CLASS_NAME, "Received request to delete file with file name: " + fileName);

        boolean deleted = context.deleteFile(fileName);

        Log.i(CLASS_NAME, (deleted ? "Successfully deleted" : "Failed to delete")
                + " file with file name: " + fileName);
    }

    /**
     * Get the absolute path to the directory on the filesystem where the file with the
     * given name is stored.
     *
     * @param context  the {@link Context} of the application
     * @param fileName the name of the file
     * @return the absolute path to the directory on the filesystem where the file with the
     * given name is stored
     */
    public static String getAbsoluteFilePath(Context context, String fileName) {
        // Method parameter validation
        if (context == null || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("Cannot get file path with null context or null/empty file name.");
        }

        Log.i(CLASS_NAME, "Received request to get the absolute path for file with file name: " + fileName);

        String absolutePath = context.getFilesDir().getPath() + "/" + fileName;
        Log.i(CLASS_NAME, "The absolute path for file with file name: " + fileName
                + " is: " + absolutePath);
        return absolutePath;
    }

    /**
     * Prevent instantiation.
     */
    private FileUtils() {
    }
}
