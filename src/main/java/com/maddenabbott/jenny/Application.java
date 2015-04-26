package com.maddenabbott.jenny;

import java.util.Arrays;

import com.maddenabbott.jenny.command.AddCommand;
import com.maddenabbott.jenny.command.Command;
import com.maddenabbott.jenny.command.CommandException;
import com.maddenabbott.jenny.command.ListCommand;
import com.maddenabbott.jenny.command.NewCommand;
import com.maddenabbott.jenny.command.RemoveCommand;
import com.maddenabbott.jenny.command.RenameCommand;
import com.maddenabbott.jenny.command.help.HelpCommand;

/**
 * The main entry point for Jenny.
 */
public class Application {
  private static final String defaultCommand = "help";

  public static void main(String[] args) {
    String commandName = getCommandName(args, defaultCommand);
    String[] parameters = getParameters(args);
    Command command = getCommand(commandName, parameters);
    command.run();
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
    return new String[]{ };
  }

  private static Command buildHelpCommand(final String[] parameters) {
    List<Class<? extends Command>> commands = asList(HelpCommand.class, AddCommand.class,
        RemoveCommand.class, RenameCommand.class, ListCommand.class, NewCommand.class);

    switch (parameters.length) {
      case 0: return new HelpCommand(commands);
      default: throw new CommandException("Incorrect number of parameters");
    }
  }

  private static Command getCommand(final String name, final String[] parameters) {
    switch (name) {
      case "help": return buildHelpCommand(parameters);
      default: throw new CommandException("Unknown command");
    }
    }
  }
