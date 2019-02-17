package com.example.cst8334project.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static com.example.cst8334project.emailservice.EmailConstants.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * Provides utility methods for verifying internet connectivity.
 */
public final class ConnectionUtils {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = ConnectionUtils.class.getSimpleName();

    /**
     * The timeout threshold for the connection in milliseconds.
     */
    private static final int TIMEOUT = 200;

    /**
     * Determine if the device is currently able to connect to the SMTP server.
     *
     * @return {@code true} if the connection was successful, {@code false} otherwise
     */
    public static boolean canConnectToSMTPServer(Context context) {
        return canConnectToServer(context, SMTP_SERVER, SMTP_PORT);
    }

    /**
     * Determine if the device is currently able to connect to the IMAP server.
     *
     * @return {@code true} if the connection was successful, {@code false} otherwise
     */
    public static boolean canConnectToIMAPServer(Context context) {
        return canConnectToServer(context, IMAP_SERVER, IMAP_PORT);
    }

    /**
     * Determine if the device is currently able to connect to the server identified by
     * the given hostname and port.
     *
     * @param hostname the hostname
     * @param port     the port
     * @return {@code true} if the connection was successful, {@code false} otherwise
     */
    private static boolean canConnectToServer(Context context, String hostname, int port) {
        // First check if the device is actually online
        if (!isOnline(context)) {
            return false;
        }

        Log.i(CLASS_NAME, "Checking connection for hostname: " + hostname + " and port: " + port);

        // Try connecting to the specified hostname
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostname, port), TIMEOUT);
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Failed to connect to " + hostname, e);
            return false;
        }

        Log.i(CLASS_NAME, "Successfully connected to " + hostname);
        return true;
    }

    /**
     * Determine if the device is currently connect to the internet.
     *
     * @param context the {@link Context} of the application
     * @return {@code true} if the device is connected to the internet, {@code false} otherwise
     */
    public static boolean isOnline(Context context) {
        Objects.requireNonNull(context, "Cannot check internet connection with null Context.");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isOnline = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        Log.i(CLASS_NAME, "The device is" + (isOnline ? "" : " not") + " online.");
        return isOnline;
    }

    /**
     * Prevent instantiation.
     */
    private ConnectionUtils() {
    }
}
