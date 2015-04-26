package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Summary;

@Name("list")
@Summary("Display all available templates by repository.")
@Description("This shows all of the repositories of templates that you have already added to\n"
    + "Jenny along with the names of each template in the following format:\n"
    + "  repo_1 (repo_1_url)\n"
    + "    template_1\n"
    + "    template_2\n"
    + "  repo_2 (repo_2_url)\n"
    + "    template_1")
public class ListCommand implements Command {
  @Override
  public void run() {

  }
}
