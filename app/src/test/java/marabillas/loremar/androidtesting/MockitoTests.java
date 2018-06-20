package marabillas.loremar.androidtesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/*Notes:
 * 1. Used when testing a class method but one of the required arguments is an object you can't/dont
 * want to manually instantiate and that object's methods is to be called inside the class method
 * to be tested. Hence, you mock that object and pre-define the results of its methods the way you
 * want them to respond.
 * */

@RunWith(MockitoJUnitRunner.class)
public class MockitoTests {
    @Mock
    private
    SomeComplexClass someComplexClass;

 /*   @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();*/

    @Test
    public void testWhenThenReturn() {
        MainActivity activity = new MainActivity();
        when(someComplexClass.methodA()).thenReturn("hello");
        when(someComplexClass.methodB()).thenReturn(5);
        activity.processInvolvingSomeComplexClass(someComplexClass);
        assertThat(activity.aValue, is("hello"));
        assertThat(activity.bValue, is(5));
    }
}
