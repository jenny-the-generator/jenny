package com.maddenabbott.jenny.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.is;

/**
 * Test utility to verify multi-line Strings against content in files.
 */
public class OutputMatcher {
  /**
   * Verify that something matches exactly the content of a file. The file name given should be a
   * path relative to a directory called output at the root of the classpath,
   * with no file extension. A file extension of '.txt' will automatically be appended.
   *
   * For example "foo" would be the name of a file at classpath:output/foo.txt whilst "foo/bar"
   * would load the file classpath:output/foo/bar.txt.
   *
   * All of this is necessary because Java has no real way of having multi-line Strings so they
   * must be specified in files instead.
   *
   * @param path the path of the file to load content from
   * @return a Matcher that will verify equality with the contents of the loaded file
   */
  public static Matcher<String> isIn(final String path) {

    URL url = ClassLoader.getSystemResource("output/" + path + ".txt");
    if (url == null) {
      throw new RuntimeException("Unable to load output resource " + path);
    }

    URI uri;
    try {
      uri = url.toURI();
    } catch (URISyntaxException e) {
      throw new RuntimeException("Unable to load output resource " + path);
    }

    try {
      return is(new String(Files.readAllBytes(Paths.get(uri))));
    } catch (IOException e) {
      throw new RuntimeException("Unable to load output resource " + path);
    }
  }
}
