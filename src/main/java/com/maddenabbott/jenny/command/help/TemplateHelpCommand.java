package com.maddenabbott.jenny.command.help;

import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.command.Command;

@Summary("You can also get help with a specific template.")
public class TemplateHelpCommand implements Command {
  @Parameter("the name of the repository containing the template.")
  private final String alias;
  @Parameter("the name of the template that you want help with.")
  private final String template;

  public TemplateHelpCommand(final String alias, final String template) {
    this.alias = alias;
    this.template = template;
  }

  @Override
  public void run() {

  }
}
