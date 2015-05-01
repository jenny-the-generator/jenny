package com.maddenabbott.jenny.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import com.maddenabbott.jenny.template.Template;
import org.eclipse.jgit.api.Git;

/**
 * Represents a repository of templates.
 */
public class Repository {
  private final String name;
  private Git git;
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
    
    return stream(directories).filter(File::isDirectory).map(Template::new).collect(toList());
  }
}
