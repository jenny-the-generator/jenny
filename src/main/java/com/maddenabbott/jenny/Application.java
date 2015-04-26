package com.maddenabbott.jenny;

/**
 * The main entry point for Jenny.
 */
public class Application {
  private static final String defaultCommand = "help";

  public static void main(String[] args) {
    String commandName = getCommandName(args, defaultCommand);
  }

  private static String getCommandName(final String[] args, final String defaultCommandName) {
    if (args.length > 0 && args[0] != null) {
      return args[0];
    } else {
      return defaultCommandName;
    }
  }
  }
