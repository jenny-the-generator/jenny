package com.maddenabbott.jenny.command;

/**
 * A problem when creating a repository
 */
public class AddCommandException extends CommandException {
  public static AddCommandException unknown(final String alias, final String url, Throwable cause) {
    String reason = "unknown!";
    String solution = "Sorry, no idea!";
    return new AddCommandException(message(alias, url, reason, solution), cause);
  }

  public static AddCommandException missing(final String alias, final String url) {
    String reason = "The URL is not a valid git repository.";
    String solution = "Double check that the URL you have used is correct.";
    return new AddCommandException(message(alias, url, reason, solution));
  }

  public static AddCommandException duplicate(final String alias, final String url) {
    String reason = "A repository already exists with the same alias and URL.";
    String solution = "Just use the existing repository!";
    return new AddCommandException(message(alias, url, reason, solution));
  }

  public static AddCommandException uniqueName(final String alias, final String url,
      final String existingUri) {
    String reason = "A repository already exists with the same alias but a different URL: "
        + existingUri;
    String solution
        = "Choose a different alias for the new repository or rename the existing repository.";
    return new AddCommandException(message(alias, url, reason, solution));
  }

  public static AddCommandException orphan(final String alias, final String url) {
    String reason = "A repository already exists with the same alias but no URL.";
    String solution
        = "If you no longer need the repository, remove it. Otherwise, choose a different alias.";
    return new AddCommandException(message(alias, url, reason, solution));
  }

  public static AddCommandException authenticated(final String alias, final String url) {
    String reason = "The repository requires authentication.";
    String solution = "None! Jenny doesn't support authenticated repositories yet!";
    return new AddCommandException(message(alias, url, reason, solution));
  }

  public AddCommandException(final String message) {
    super(message);
  }

  public AddCommandException(final String message, final Throwable cause) {
    super(message, cause);
  }

  private static String message(final String alias, final String url, final String reason,
      final String solution) {
    return "The repository " + url + " named " + alias + " can't be added.%n%n" + "REASON: "
        + reason + "%n%n" + "SOLUTION: " + solution + "%n";
  }
}
