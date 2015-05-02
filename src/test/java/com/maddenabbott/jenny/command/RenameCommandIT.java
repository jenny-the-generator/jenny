package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.Application;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import java.io.File;

import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static com.maddenabbott.jenny.test.RepositoryUtil.addRepo;
import static com.maddenabbott.jenny.test.RepositoryUtil.getReposDirectory;
import static com.maddenabbott.jenny.test.RepositoryUtil.removeRepos;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RenameCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @After
  public void tearDown() throws Exception {
    removeRepos();
  }

  @Test
  public void shouldRenameRepository() throws Exception {
    addRepo("test", "test-template-repo-1");

    Application.main(new String[]{ "rename", "test", "test1" });

    assertThat(log.getLog(), isIn("rename/rename"));
    assertThat(new File(getReposDirectory(), "test1").exists(), is(true));
  }

  @Test
  public void shouldFailToRenameRepositoryToIdenticalName() throws Exception {
    addRepo("test", "test-template-repo-1");

    Application.main(new String[]{ "rename", "test", "test" });

    assertThat(log.getLog(), isIn("rename/identical"));
  }

  @Test
  public void shouldFailToRenameMissingRepository() throws Exception {
    Application.main(new String[]{ "rename", "test", "test1" });

    assertThat(log.getLog(), isIn("rename/missing"));
  }

  @Test
  public void shouldFailToRenameRepositoryToExistingName() throws Exception {
    addRepo("test1", "test-template-repo-1");
    addRepo("test2", "test-template-repo-2");

    Application.main(new String[]{ "rename", "test1", "test2" });

    assertThat(log.getLog(), isIn("rename/uniqueName"));
  }
}
