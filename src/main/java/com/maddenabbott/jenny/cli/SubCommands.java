package com.maddenabbott.jenny.cli;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.maddenabbott.jenny.command.Command;

@Retention(RetentionPolicy.RUNTIME)
public @interface SubCommands {
  Class<? extends Command>[] value();
}
