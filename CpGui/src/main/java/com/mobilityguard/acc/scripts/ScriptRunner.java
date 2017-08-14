package com.mobilityguard.acc.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class ScriptRunner {

    private static String runScript(final String[] cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            final BufferedReader input = new BufferedReader(new InputStreamReader( process.getInputStream()));
            return input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "yoyo";
    }

    /**
     *get network interfaces.
     */
    public static String getNetworkInterfaces() {

        String[] cmd = {"python", "/opt/acc/scripts/dummy_list_interfaces.py"};
        String result = ScriptRunner.runScript(cmd);
        return result;
    }
}
