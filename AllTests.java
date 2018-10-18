import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ParseGEDCOMTest.class, CheckDateTest.class, PrintListTest.class, CheckPersonInfoTest.class })
public class AllTests {

}
