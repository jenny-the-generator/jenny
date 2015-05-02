package com.maddenabbott.jenny.repository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.maddenabbott.jenny.command.AddCommandException;
import com.maddenabbott.jenny.command.CommandException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.NotSupportedException;

/**
 * Creates repositories.
 */
public class RepositoryMapper {
  public Repository create(final String name, final String uri) {
    File repositoryDirectory = getRepositoryDirectory(name);
    open(repositoryDirectory).ifPresent((git) -> {
      Repository repository = new Repository(name, git, repositoryDirectory);
      String existingUri = repository.getUri()
          .orElseThrow(() -> AddCommandException.orphan(name, uri));
      if (Objects.equals(existingUri, uri)) {
        throw AddCommandException.duplicate(name, uri);
      } else {
        throw AddCommandException.uniqueName(name, uri, existingUri);
      }
    });

    try {
      Git git = Git.cloneRepository().setURI(uri).setDirectory(repositoryDirectory).call();
      return new Repository(name, git, repositoryDirectory);
    } catch (InvalidRemoteException e) {
      throw AddCommandException.missing(name, uri);
    } catch (JGitInternalException e) {
      if (e.getCause() instanceof NotSupportedException) {
        throw AddCommandException.missing(name, uri);
      } else {
        throw AddCommandException.unknown(name, uri, e);
      }
    } catch (TransportException e) {
      throw AddCommandException.authenticated(name, uri);
    } catch (GitAPIException e) {
      throw AddCommandException.unknown(name, uri, e);
    }
  }

  public Repository find(final String name) {
    return load(getRepositoryDirectory(name));
  }

  public List<Repository> all() {
    File[] directories = getRepositoriesDirectory().listFiles();
    if (directories == null) {
      return new ArrayList<>();
    }

    return stream(directories).filter(File::isDirectory).map(this::load).collect(toList());
  }
  
  private Repository load(final File repositoryDirectory) {
    String name = repositoryDirectory.getName();
    Git git = open(repositoryDirectory).orElseThrow(() -> new CommandException(
      "Repository " + name + " cannot be loaded because it doesn't exist"));
    return new Repository(name, git, repositoryDirectory);
  }

  private Optional<Git> open(final File repositoryDirectory) {
    try {
      return Optional.of(Git.open(repositoryDirectory));
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private File getRepositoryDirectory(final String name) {
    return getDirectory("repositories/" + name);
  }

  private File getRepositoriesDirectory() {
    return getDirectory("repositories");
  }

  private File getDirectory(final String name) {
    URL url = RepositoryMapper.class.getProtectionDomain().getCodeSource().getLocation();
    File childDirectory;
    try {
      childDirectory = new File(url.toURI());
    } catch (URISyntaxException e) {
      childDirectory = new File(url.getPath());
    }
    File jennyDirectory = childDirectory.getParentFile();
    return new File(jennyDirectory, name);
  }

  public boolean delete(final String name) {
    File repositoryDirectory = getRepositoryDirectory(name);

    return repositoryDirectory.exists() && delete(repositoryDirectory);
  }

  private boolean delete(final File file) {
    if (file.isDirectory()) {
      File[] children = file.listFiles();
      if (children != null && children.length > 0) {
        for (File child : children) {
          delete(child);
        }
      }
      return file.delete();
    } else {
      return file.delete();
    }
  }
}
