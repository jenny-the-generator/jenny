package com.maddenabbott.jenny.repository;

import java.util.Optional;
import static java.util.Optional.ofNullable;

import org.eclipse.jgit.api.Git;

/**
 * Represents a repository of templates.
 */
public class Repository {
  private final String name;
  private final Git git;

  public Repository(final String name, final Git git) {
    this.name = name;
    this.git = git;
  }

  public Optional<String> getUri() {
    return ofNullable(git.getRepository().getConfig().getString("remote", "origin", "url"));
  }

  public String getName() {
    return name;
  }

  public void close() {
    this.git.close();
  }
}
