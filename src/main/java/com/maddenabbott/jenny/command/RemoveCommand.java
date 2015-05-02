package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;

@Name("remove")
@Summary("Remove an existing repository of templates from Jenny.")
@Description("When you no longer need any templates in a repository, you can remove it, using the%n"
    + "same name you gave that repository, when you added it. The templates in that repository%n"
    + "will then no longer be available to Jenny.")
public class RemoveCommand implements Command {
  @Parameter("The name of the repository to remove.")
  private String alias;

  @Override
  public void run() {

  }
}
