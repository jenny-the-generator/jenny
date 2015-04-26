package com.maddenabbott.jenny.command.help;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.maddenabbott.jenny.cli.Parameter;
import com.maddenabbott.jenny.cli.Summary;
import com.maddenabbott.jenny.command.Command;

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

  private String getName(final Field field) {
    return field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase();
  }
  }
