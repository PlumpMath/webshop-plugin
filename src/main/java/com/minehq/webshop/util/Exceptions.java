package com.minehq.webshop.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {
    public static void printFriendlyStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        System.out.println();
        Log.severe("Internal error!");
        Log.severe(String.format("If this bug hasn't been reported yet, please report it at %s as soon as possible.", "https://github.com/goldblattster/webshop-plugin/issues"));
        Log.severe("          ======= SNIP HERE =======");

        throwable.printStackTrace(printWriter);

        for (String line : stringWriter.toString().replace("\r", "").split("\n")) {
            Log.severe(line);
        }

        printWriter.close();
        try {
            stringWriter.close();
        } catch (IOException ignored) {}

        Log.severe("          ===== END SNIP HERE =====");
        System.out.println();
    }
}
