package com.maddenabbott.jenny.repository;

import com.maddenabbott.jenny.template.Template;

import org.eclipse.jgit.api.Git;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Represents a repository of templates.
 */
public class Repository implements Comparable<Repository> {
  private final String name;
  private final Git git;
  private final File directory;

  public Repository(final String name, final Git git, final File directory) {
    this.name = name;
    this.git = git;
    this.directory = directory;
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

  public List<Template> getTemplates() {
    File[] directories = new File(directory, "templates").listFiles();
    if (directories == null) {
      return new ArrayList<>();
    }

    return stream(directories).filter(File::isDirectory).map(Template::new).sorted()
      .collect(toList());
  }

  @Override
  public int compareTo(final Repository other) {
    return this.getName().compareTo(other.getName());
  }
}
