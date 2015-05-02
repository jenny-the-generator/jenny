package com.maddenabbott.jenny;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import static com.maddenabbott.jenny.test.OutputMatcher.isIn;
import static org.junit.Assert.assertThat;

public class ApplicationIT {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Test
  public void shouldFailWhenIncorrectParametersForCommand() throws Exception {
    Application.main(new String[]{ "add", "test" });

    assertThat(log.getLog(), isIn("incorrectParameters"));
  }
}
