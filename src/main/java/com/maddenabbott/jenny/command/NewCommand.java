package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;

@Name("new")
@Summary("Create a new project from a template.")
@Description("If the template needs more information, it will prompt you for it.")
public class NewCommand implements Command {
  @Parameter("The name of the repository containing the template.")
  private final String alias;
  @Parameter("The name of the template.")
  private final String template;

  public NewCommand(final String alias, final String template) {
    this.alias = alias;
    this.template = template;
  }

  @Override
  public void run() {

  }
}
