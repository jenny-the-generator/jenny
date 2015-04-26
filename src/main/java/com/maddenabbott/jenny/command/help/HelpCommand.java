package com.maddenabbott.jenny.command.help;

import java.util.List;

import com.maddenabbott.jenny.command.Command;

/**
 * Displays usage information for Jenny.
 */
public class HelpCommand implements Command {
  private final List<Class<? extends Command>> commands;

  public HelpCommand(List<Class<? extends Command>> commands) {
    this.commands = commands;
  }

  @Override
  public void run() {
  }
}
