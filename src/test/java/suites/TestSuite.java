package suites;

import org.example.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
        SearchTests.class,
        MyListsTests.class,
        GetStartedTest.class
})
public class TestSuite {
}
