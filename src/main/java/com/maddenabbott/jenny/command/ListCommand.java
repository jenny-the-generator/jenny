package com.maddenabbott.jenny.command;

import java.util.List;
import java.util.StringJoiner;

import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.repository.Repository;
import com.maddenabbott.jenny.repository.RepositoryMapper;
import com.maddenabbott.jenny.template.Template;

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
  private final RepositoryMapper repositoryMapper;

  public ListCommand() {
    repositoryMapper = new RepositoryMapper();
  }

  @Override
  public void run() {
    StringJoiner lines = new StringJoiner("\n", "", "\n");
    List<Repository> repositories = repositoryMapper.all();
    if (repositories.isEmpty()) {
      lines.add("You haven't added any repositories to Jenny.");
      lines.add("");
      lines.add("To get started try:");
      lines.add("");
      lines.add("  jen help add");
    } else {
      lines.add("The following repositories are available:");
      lines.add("");
      for (Repository repository : repositories) {
        lines.add(repository.getName() + " (" + repository.getUri().orElse("Broken URL!") + ")");
        for (Template template : repository.getTemplates()) {
          lines.add("  " + template.getName());
        }
      }
    }
    System.out.print(lines);
  }
}
