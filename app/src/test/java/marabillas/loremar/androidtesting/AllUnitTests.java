package marabillas.loremar.androidtesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PureTests.class, MockitoTests.class, RobolectricTests.class})
public class AllUnitTests {
}
