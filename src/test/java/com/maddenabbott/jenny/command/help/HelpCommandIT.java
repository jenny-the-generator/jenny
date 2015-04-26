package com.maddenabbott.jenny.command.help;

import com.maddenabbott.jenny.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static org.junit.Assert.assertThat;

public class HelpCommandIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Test
  public void shouldShowHelp() throws Exception {
    Application.main(new String[]{ "help" });

    assertThat(log.getLog(), isIn("help/help"));
  }
}
