package com.maddenabbott.jenny.cli;

/**
 * Represents the command line console.
 */
public class Console {
  public static void print(final String message) {
    System.out.printf(message);
  }

  public static void print(final Object object) {
    print(String.valueOf(object));
  }
}
