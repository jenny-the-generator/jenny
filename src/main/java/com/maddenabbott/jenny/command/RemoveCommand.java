package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Console;
import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.repository.RepositoryMapper;

@Name("remove")
@Summary("Remove an existing repository of templates from Jenny.")
@Description("When you no longer need any templates in a repository, you can remove it, using the%n"
    + "same name you gave that repository, when you added it. The templates in that repository%n"
    + "will then no longer be available to Jenny.")
public class RemoveCommand implements Command {
  @Parameter("The name of the repository to remove.")
  private final String alias;

  private final RepositoryMapper repositoryMapper;

  public RemoveCommand(final String alias) {
    this.alias = alias;
    this.repositoryMapper = new RepositoryMapper();
  }

  @Override
  public void run() {
    if(repositoryMapper.delete(alias)) {
      Console.print("Repository " + alias + " has been removed from Jenny.");
    } else {
      Console.print("There is no repository named " + alias + ".");
    }
  }
}
