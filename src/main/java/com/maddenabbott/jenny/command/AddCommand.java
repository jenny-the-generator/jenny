package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;
/**
 * Add a new repository of templates to Jenny.
 */
@Name("add")
@Summary("Add a new repository of templates to Jenny.")
@Description("Jenny creates projects for you from templates. Templates are stored in repositories\n"
    + "and must be downloaded to your machine before you can use them.\n\n"
    + "When you add a repository to Jenny, you must provide an alias which is then used in the\n"
    + "future to refer to templates in that repository. It's just a bit shorter than having to\n"
    + "use the URL every time! You can use any alias you want as long as you haven't already\n"
    + "used it.")
public class AddCommand implements Command {
  @Parameter("A name to use to refer to the repository in the future.")
  private final String alias;
  @Parameter("The full URL of the repository.")
  private final String url;

  public AddCommand(final String alias, final String url) {
    this.alias = alias;
    this.url = url;
  }

  @Override
  public void run() {
  }
}
