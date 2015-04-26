package com.maddenabbott.jenny;

import java.util.Arrays;

/**
 * The main entry point for Jenny.
 */
public class Application {
  private static final String defaultCommand = "help";

  public static void main(String[] args) {
    String commandName = getCommandName(args, defaultCommand);
    String[] parameters = getParameters(args);
  }

  private static String getCommandName(final String[] args, final String defaultCommandName) {
    if (args.length > 0 && args[0] != null) {
      return args[0];
    } else {
      return defaultCommandName;
    }
  }

  private static String[] getParameters(final String[] args) {
    if (args.length > 1) {
      return Arrays.copyOfRange(args, 1, args.length);
    }
    return new String[]{ };
  }
  }
