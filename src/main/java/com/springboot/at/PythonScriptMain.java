package com.springboot.at;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Value;

public class PythonScriptMain {

  public static void main(String[] args) {
    // The command to execute

    String[] command = {"python3", "src/main/resources/scripts/import_data.py"};
    // Create the ProcessBuilder object with the command
    ProcessBuilder processBuilder = new ProcessBuilder(command);
    // Redirect the error stream to the input stream
    processBuilder.redirectErrorStream(true);
    try {
      // Start the process

      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder output = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
      }
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        System.out.println("output-->" + output.toString());
        //return output.toString();
      } else {
        System.out.println("Python script execution failed with exit code " + exitCode);
      }
    } catch (IOException | InterruptedException e) {
      new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
