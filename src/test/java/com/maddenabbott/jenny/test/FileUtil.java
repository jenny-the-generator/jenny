package com.maddenabbott.jenny.test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.eclipse.jgit.util.FileUtils.RECURSIVE;
import static org.eclipse.jgit.util.FileUtils.RETRY;
import static org.eclipse.jgit.util.FileUtils.delete;

public class FileUtil {
  public static File getRepositoriesDirectory() {
    URL url = FileUtil.class.getProtectionDomain().getCodeSource().getLocation();
    File childDirectory;
    try {
      childDirectory = new File(url.toURI());
    } catch (URISyntaxException e) {
      childDirectory = new File(url.getPath());
    }
    File jennyDirectory = childDirectory.getParentFile();
    return new File(jennyDirectory, "repositories");
  }

  public static void removeRepositories() throws IOException {
    delete(getRepositoriesDirectory(), RECURSIVE | RETRY);
  }
}
