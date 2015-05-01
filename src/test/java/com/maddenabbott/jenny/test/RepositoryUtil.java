package com.maddenabbott.jenny.test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.eclipse.jgit.util.FileUtils.RECURSIVE;
import static org.eclipse.jgit.util.FileUtils.RETRY;
import static org.eclipse.jgit.util.FileUtils.delete;

public class RepositoryUtil {
  public static File getReposDirectory() {
    URL url = RepositoryUtil.class.getProtectionDomain().getCodeSource().getLocation();
    File childDirectory;
    try {
      childDirectory = new File(url.toURI());
    } catch (URISyntaxException e) {
      childDirectory = new File(url.getPath());
    }
    File jennyDirectory = childDirectory.getParentFile();
    return new File(jennyDirectory, "repositories");
  }

  public static void removeRepos() throws IOException {
    delete(getReposDirectory(), RECURSIVE | RETRY);
  }
  
  public static String repoName(final String name) {
    return "https://github.com/jenny-the-generator/" + name;
  }
}
