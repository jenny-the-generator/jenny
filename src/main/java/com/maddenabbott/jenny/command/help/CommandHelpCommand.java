package com.maddenabbott.jenny.command.help;

import java.util.List;
import java.util.Map;

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
  }
  }
