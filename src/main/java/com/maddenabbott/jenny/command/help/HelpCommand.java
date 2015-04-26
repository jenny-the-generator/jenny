package com.maddenabbott.jenny.command.help;

import java.util.List;
import java.util.StringJoiner;

import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.SubCommands;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.command.Command;

/**
 * Displays usage information for Jenny.
 */
@Name("help")
@Summary("Tells you how to use Jenny.")
@SubCommands({ CommandHelpCommand.class, TemplateHelpCommand.class })
public class HelpCommand implements Command {
  private final List<Class<? extends Command>> commands;

  public HelpCommand(List<Class<? extends Command>> commands) {
    this.commands = commands;
  }

  @Override
  public void run() {
    StringJoiner lineJoiner = new StringJoiner("\n", "", "\n");
    lineJoiner.add("Usage: jen COMMAND [ARGS]");
    lineJoiner.add("");
    lineJoiner.add("Available commands:");

    for(Class<? extends Command> command : commands) {
      Name name = command.getAnnotation(Name.class);
      Summary summary = command.getAnnotation(Summary.class);
      if (name != null && summary != null) {
        lineJoiner.add("  " + name.value() + "  " + summary.value());
      }
    }
    System.out.print(lineJoiner);
  }
}
