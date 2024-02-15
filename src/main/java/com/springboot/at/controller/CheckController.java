package com.springboot.at.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckController {

  @Value("${import.products.script}")
  private String importProductsScript;

  @GetMapping("/api/v1/health-check")
  public String healthCheck() {
    return "server is up and running";
  }

  @GetMapping("/api/v1/import-python-script")
  public String importPythonData() {
    String importProductsResponse = "";
    ;
    System.out.println("called import python script");
    String pythonExecutable = "python3";
    String pythonScriptPath = importProductsScript;
    String[] command = {pythonExecutable, pythonScriptPath};

    // Execute the Python script
    try {
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder output = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
      }
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        importProductsResponse = output.toString();
      } else {
        importProductsResponse = "Python script execution failed with exit code " + exitCode;
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      importProductsResponse = "Error executing Python script: " + e.getMessage();
    }

    return importProductsResponse;

  }
}
