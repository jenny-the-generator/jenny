package com.maddenabbott.jenny.command;

/**
 * A problem when creating a repository
 */
public class RenameCommandException extends CommandException {
  public static RenameCommandException unknown(final String currentAlias, final String newAlias) {
    String reason = "unknown!";
    String solution = "Sorry, no idea!";
    return new RenameCommandException(message(currentAlias, newAlias, reason, solution));
  }

  public static RenameCommandException uniqueName(final String currentAlias,
    final String newAlias) {
    String reason = "A repository named " + newAlias + " already exists.";
    String solution
      = "Choose a different alias for this repository or rename the other repository first.";
    return new RenameCommandException(message(currentAlias, newAlias, reason, solution));
  }

  public static RenameCommandException identical(final String currentAlias, final String newAlias) {
    String reason = "The new alias is identical to the current alias.";
    String solution = "Maybe you don't actually want to rename this repository?";
    return new RenameCommandException(message(currentAlias, newAlias, reason, solution));
  }

  public static RenameCommandException missing(final String currentAlias, final String newAlias) {
    String reason = "A repository named " + currentAlias + " doesn't exist.";
    String solution = "Try running, jen list, to see the names of repositories you have added.";
    return new RenameCommandException(message(currentAlias, newAlias, reason, solution));
  }

  public RenameCommandException(final String message) {
    super(message);
  }

  private static String message(final String currentAlias, final String newAlias,
    final String reason, final String solution) {
    return "The repository named " + currentAlias + " can't be renamed to " + newAlias + ".%n%n"
      + "REASON: " + reason + "%n%n" + "SOLUTION: " + solution + "%n";
  }
}
