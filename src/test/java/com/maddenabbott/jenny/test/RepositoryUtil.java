package com.maddenabbott.jenny.test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.maddenabbott.jenny.Application;
import static org.eclipse.jgit.util.FileUtils.RECURSIVE;
import static org.eclipse.jgit.util.FileUtils.RETRY;
import static org.eclipse.jgit.util.FileUtils.SKIP_MISSING;
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
    delete(getReposDirectory(), RECURSIVE | SKIP_MISSING | RETRY);
  }
  
  public static void addRepo(final String name, final String url) {
    Application.main(new String[]{ "add", name, repoUrl(url) });
  }
  
  public static String repoUrl(final String name) {
    return "https://github.com/jenny-the-generator/" + name;
  }
}
