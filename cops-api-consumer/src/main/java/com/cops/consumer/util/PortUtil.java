package com.cops.consumer.util;

import java.io.IOException;
import java.net.ServerSocket;

public class PortUtil {

    private static PortUtil INSTANCE;

    private int port;

    private PortUtil() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            this.port = serverSocket.getLocalPort();
            System.setProperty("RANDOM_PORT", String.valueOf(this.port));
        }
        catch (IOException ignored) {
        }
    }

    public static PortUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PortUtil();
        }
        return INSTANCE;
    }

    public int getPort() {
        return this.port;
    }
}
