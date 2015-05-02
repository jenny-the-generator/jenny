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

public class RemoveCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @After
  public void tearDown() throws Exception {
    removeRepos();
  }

  @Test
  public void shouldRemoveRepository() throws Exception {
    addRepo("test", "test-template-repo-1");
    
    Application.main(new String[]{ "remove", "test" });
    
    assertThat(log.getLog(), isIn("remove/remove"));
    assertThat(new File(getReposDirectory(), "test").exists(), is(false));
  }

  @Test
  public void shouldFailToRemoveNonExistentRepository() throws Exception {
    Application.main(new String[]{ "remove", "test" });

    assertThat(log.getLog(), isIn("remove/missing"));
  }
}
