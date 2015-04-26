package com.maddenabbott.jenny.command.help;

import com.maddenabbott.jenny.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static org.junit.Assert.assertThat;

public class CommandHelpCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Test
  public void shouldShowHelpForHelp() throws Exception {
    Application.main(new String[]{ "help", "help" });

    assertThat(log.getLog(), isIn("help/command/help"));
  }

  @Test
  public void shouldShowHelpForAdd() throws Exception {
    Application.main(new String[]{ "help", "add" });

    assertThat(log.getLog(), isIn("help/command/add"));
  }

  @Test
  public void shouldShowHelpForList() throws Exception {
    Application.main(new String[]{ "help", "list" });

    assertThat(log.getLog(), isIn("help/command/list"));
  }

  @Test
  public void shouldShowHelpForRemove() throws Exception {
    Application.main(new String[]{ "help", "remove" });

    assertThat(log.getLog(), isIn("help/command/remove"));
  }

  @Test
  public void shouldShowHelpForRename() throws Exception {
    Application.main(new String[]{ "help", "rename" });

    assertThat(log.getLog(), isIn("help/command/rename"));
  }

  @Test
  public void shouldShowHelpForNew() throws Exception {
    Application.main(new String[]{ "help", "new" });

    assertThat(log.getLog(), isIn("help/command/new"));
  }
}
