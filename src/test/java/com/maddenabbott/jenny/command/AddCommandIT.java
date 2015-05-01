package com.maddenabbott.jenny.command;

import java.io.File;

import com.maddenabbott.jenny.Application;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static com.maddenabbott.jenny.test.RepositoryUtil.getReposDirectory;
import static com.maddenabbott.jenny.test.RepositoryUtil.removeRepos;
import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static com.maddenabbott.jenny.test.RepositoryUtil.repoName;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

public class AddCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @After
  public void tearDown() throws Exception {
    removeRepos();
  }

  @Test
  public void shouldAddRepository() throws Exception {
    Application.main(new String[]{ "add", "test", repoName("test-template-repo-1") });

    assertThat(new File(getReposDirectory(), "test").exists(), is(true));
    assertThat(log.getLog(), isEmptyString());
  }

  @Test
  public void shouldFailToAddExistingRepositoryWithExistingName() throws Exception {
    Application.main(new String[]{ "add", "test", repoName("test-template-repo-1") });
    Application.main(new String[]{ "add", "test", repoName("test-template-repo-1") });

    assertThat(log.getLog(), isIn("add/duplicate"));
  }

  @Test
  public void shouldFailToAddRepositoryWithExistingName() throws Exception {
    Application.main(new String[]{ "add", "test", repoName("test-template-repo-1") });
    Application.main(new String[]{ "add", "test", repoName("test-template-repo-2") });

    assertThat(log.getLog(), isIn("add/uniqueName"));
  }

  @Test
  public void shouldFailToAddNonExistentRepository() throws Exception {
    Application.main(new String[]{ "add", "test", "https://example.org" });

    assertThat(log.getLog(), isIn("add/missing"));
  }

  @Test
  public void shouldFailToAddInvalidRepository() throws Exception {
    Application.main(new String[]{ "add", "test", "https://github.com/jenny-the-generator" });

    assertThat(log.getLog(), isIn("add/invalid"));
  }

  @Test
  public void shouldFailToAddAuthenticatedRepository() throws Exception {
    Application.main(new String[]{ "add", "test", repoName("nonexistent-repo") });

    assertThat(log.getLog(), isIn("add/authenticated"));
  }
}
