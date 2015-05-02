package com.maddenabbott.jenny.command.help;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.maddenabbott.jenny.cli.Console;
import com.maddenabbott.jenny.cli.Description;
import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.command.Command;
import com.maddenabbott.jenny.command.CommandException;

@Summary("Pass the name of a command for help with that command.")
public class CommandHelpCommand implements Command {
  @Parameter("The name of the command you would like more help with.")
  private final String command;
  private final Map<String, List<Class<? extends Command>>> commandGroups;

  public CommandHelpCommand(
      final Map<String, List<Class<? extends Command>>> commandGroups,
      final String command) {
    this.command = command;
    this.commandGroups = commandGroups;
  }

  @Override
  public void run() {
    List<Class<? extends Command>> commands = this.commandGroups.get(command);

    if (commands == null || commands.isEmpty()) {
      throw new CommandException("There is no command named " + command + ".");
    }

    StringJoiner paragraphJoiner = new StringJoiner("%n%n", "", "%n");
    paragraphJoiner.add(getUsage(command, commands));
    for (Class<? extends Command> command : commands) {
      paragraphJoiner.merge(getHelp(command));
    }
    Console.print(paragraphJoiner);
  }

  private String getUsage(final String commandName, final List<Class<? extends Command>> commands) {
    boolean optionalParameters = false;
    StringJoiner groupJoiner = new StringJoiner("|");
    for (Class<? extends Command> command : commands) {
      StringJoiner parameterJoiner = new StringJoiner(" ");
      for (Field field : command.getDeclaredFields()) {
        if (field.getAnnotation(Parameter.class) != null) {
          parameterJoiner.add(getName(field));
        }
      }
      if (parameterJoiner.length() == 0) {
        optionalParameters = true;
      }
      groupJoiner.merge(parameterJoiner);
    }

    String parameters = groupJoiner.toString();

    if (parameters.length() == 0) {
      return "Usage: jen " + commandName;
    } else if (optionalParameters) {
      return "Usage: jen " + commandName + " [" + parameters + "]";
    } else {
      return "Usage: jen " + commandName + " " + parameters;
    }
  }

  private StringJoiner getHelp(final Class<? extends Command> command) {
    StringJoiner commandJoiner = new StringJoiner("%n%n");

    Summary summary = command.getAnnotation(Summary.class);
    if (summary == null) {
      return commandJoiner;
    }
    commandJoiner.add(summary.value());

    StringJoiner parameterJoiner = new StringJoiner("%n");
    for (Field field : command.getDeclaredFields()) {
      Parameter parameter = field.getAnnotation(Parameter.class);
      if (parameter != null) {
        String name = getName(field);
        String description = parameter.value();
        parameterJoiner.add("  " + name + "  " + description);
      }
    }
    if (parameterJoiner.length() > 0) {
      commandJoiner.add("Arguments:%n" + parameterJoiner);
    }

    Description description = command.getAnnotation(Description.class);
    if (description != null) {
      commandJoiner.add(description.value());
    }
    return commandJoiner;
  }

  private String getName(final Field field) {
    return field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase();
  }
}
