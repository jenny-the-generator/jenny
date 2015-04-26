package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;

@Name("rename")
@Summary("Give a new name to an existing repository of templates.")
@Description("If you don't like the name you have chosen for a repository, you can rename it to\n"
    + "something else. You'll then need to use the new name to refer to that repository in the\n"
    + "future.")
public class RenameCommand implements Command {
  @Parameter("The current name of the repository to rename.")
  private String currentAlias;

  @Parameter("The new name for the repository.")
  private String newAlias;

  @Override
  public void run() {

  }
}
