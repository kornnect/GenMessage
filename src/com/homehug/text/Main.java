package com.homehug.text;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

public class Main {
  public static String calc(InputStream is) {
    String output;
    int read;
    byte[] buffer = new byte[8192];

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      while ((read = is.read(buffer)) > 0) {
        digest.update(buffer, 0, read);
      }
      byte[] hash = digest.digest();
      BigInteger bigInt = new BigInteger(1, hash);
      output = bigInt.toString(16);
      while (output.length() < 64) {
        output = "0" + output;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return output;
  }

  public static String calcFile(String filename) {
    String format = "SHA256 (%s) = %s";
    File f = new File(filename);
    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
      return String.format(format, f.getName(), calc(bis));
    } catch (IOException e) {
      System.err.println(e.getClass() + ": " + e.getMessage());
    }

    return null;
  }

  
}
