package com.maddenabbott.jenny.command;

/**
 * For errors relating to the execution of commands.
 */
public class CommandException extends RuntimeException {
  public CommandException(final String message) {
    super(message);
  }

  public CommandException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
