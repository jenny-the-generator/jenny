package com.maddenabbott.jenny;

import com.maddenabbott.jenny.cli.Console;
import com.maddenabbott.jenny.cli.Name;
import com.maddenabbott.jenny.cli.SubCommands;
import com.maddenabbott.jenny.command.AddCommand;
import com.maddenabbott.jenny.command.Command;
import com.maddenabbott.jenny.command.CommandException;
import com.maddenabbott.jenny.command.ListCommand;
import com.maddenabbott.jenny.command.NewCommand;
import com.maddenabbott.jenny.command.RemoveCommand;
import com.maddenabbott.jenny.command.RenameCommand;
import com.maddenabbott.jenny.command.help.CommandHelpCommand;
import com.maddenabbott.jenny.command.help.HelpCommand;
import com.maddenabbott.jenny.command.help.TemplateHelpCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * The main entry point for Jenny.
 */
public class Application {
  private static final String defaultCommand = "help";

  public static void main(String[] args) {
    try {
      String commandName = getCommandName(args, defaultCommand);
      String[] parameters = getParameters(args);
      Command command = getCommand(commandName, parameters);
      command.run();
    } catch (CommandException e) {
      Console.print(e.getMessage());
    }
  }

  private static String getCommandName(final String[] args, final String defaultCommandName) {
    if (args.length > 0 && args[0] != null) {
      return args[0];
    } else {
      return defaultCommandName;
    }
  }

  private static String[] getParameters(final String[] args) {
    if (args.length > 1) {
      return Arrays.copyOfRange(args, 1, args.length);
    }
    return new String[]{};
  }

  private static Command buildHelpCommand(final String[] parameters) {
    List<Class<? extends Command>> commands = asList(HelpCommand.class, AddCommand.class,
      RemoveCommand.class, RenameCommand.class, ListCommand.class, NewCommand.class);

    Map<String, List<Class<? extends Command>>> commandGroups = new HashMap<>();

    for (Class<? extends Command> command : commands) {
      Name name = command.getAnnotation(Name.class);
      SubCommands subCommands = command.getAnnotation(SubCommands.class);
      if (name != null) {
        List<Class<? extends Command>> commandGroup = new ArrayList<>();
        commandGroup.add(command);
        if (subCommands != null) {
          commandGroup.addAll(asList(subCommands.value()));
        }
        commandGroups.put(name.value(), commandGroup);
      }
    }

    switch (parameters.length) {
      case 0:
        return new HelpCommand(commands);
      case 1:
        return new CommandHelpCommand(commandGroups, parameters[0]);
      case 2:
        return new TemplateHelpCommand(parameters[0], parameters[1]);
      default:
        throw handleIncorrectParameters("help", parameters, "1 or 2");
    }
  }

  private static Command buildAddCommand(final String[] parameters) {
    switch (parameters.length) {
      case 2:
        return new AddCommand(parameters[0], parameters[1]);
      default:
        throw handleIncorrectParameters("add", parameters, "2");
    }
  }

  private static Command buildListCommand(final String[] parameters) {
    switch (parameters.length) {
      case 0:
        return new ListCommand();
      default:
        throw handleIncorrectParameters("list", parameters, "0");
    }
  }

  private static Command buildRemoveCommand(final String[] parameters) {
    switch (parameters.length) {
      case 1:
        return new RemoveCommand(parameters[0]);
      default:
        throw handleIncorrectParameters("remove", parameters, "1");
    }
  }

  private static Command buildRenameCommand(final String[] parameters) {
    switch (parameters.length) {
      case 2:
        return new RenameCommand(parameters[0], parameters[1]);
      default:
        throw handleIncorrectParameters("rename", parameters, "2");
    }
  }

  private static Command getCommand(final String name, final String[] parameters) {
    switch (name) {
      case "help":
        return buildHelpCommand(parameters);
      case "add":
        return buildAddCommand(parameters);
      case "list":
        return buildListCommand(parameters);
      case "remove":
        return buildRemoveCommand(parameters);
      case "rename":
        return buildRenameCommand(parameters);
      default:
        throw new CommandException("Unknown command");
    }
  }

  private static CommandException handleIncorrectParameters(final String commandName,
    final String[] parameters, final String expected) {
    return new CommandException(
      "Incorrect number of parameters. Expected " + expected + " but was " + parameters.length
        + ". For help with this command, run: jen help " + commandName + ".");
  }
}
