package com.maddenabbott.jenny.command;

import com.maddenabbott.jenny.Application;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static com.maddenabbott.jenny.test.RepositoryUtil.addRepo;
import static com.maddenabbott.jenny.test.RepositoryUtil.removeRepos;
import static org.junit.Assert.assertThat;

public class ListCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @After
  public void tearDown() throws Exception {
    removeRepos();
  }
  
  @Test
  public void shouldListRepositories() throws Exception {
    addRepo("test", "test-template-repo-1");
    addRepo("test2", "test-template-repo-2");

    Application.main(new String[]{ "list" });
    
    assertThat(log.getLog(), isIn("list/list"));
  }
  
  @Test
  public void shouldShowHelpWhenNoRepositories() throws Exception {
    Application.main(new String[]{ "list" });

    assertThat(log.getLog(), isIn("list/none"));
  }
}
