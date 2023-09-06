import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Junit Suite Test
//sono necessarie entrambe le annotazioni RunWitth Suite.SuiteClasses({C1.class,c2.class})
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestListObjectAdapter.class, TestCollectionObjectAdapter.class,TestMapObjectAdapter.class,TestSublist.class,TestSetObjectAdapter.class, TestKeySet.class,TestValues.class,TestEntrySet.class})

public class TestSuite
{	}
