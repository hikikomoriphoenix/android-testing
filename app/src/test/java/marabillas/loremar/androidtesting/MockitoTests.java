package marabillas.loremar.androidtesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.AdditionalMatchers.leq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/*Notes:
 * 1. Used when testing a class method but one of the required arguments is an object you can't/dont
 * want to manually instantiate and that object's methods is to be called inside the class method
 * to be tested. Hence, you mock that object and predefine the results of its methods the way you
 * want them to respond.
 *
 * 2. When you want to pass a real object to the method but also want to make use of the ability
 * to predefine some of the methods results, use spy().
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

    @Test
    public void testMultipleValues() {
        when(someComplexClass.methodA()).thenReturn("hello").thenReturn("world");
        String message = someComplexClass.methodA() + " " + someComplexClass.methodA();
        assertThat(message, is("hello world"));
        when(someComplexClass.methodB()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5);
        String num = String.valueOf(someComplexClass.methodB()) + String.valueOf(someComplexClass
                .methodB()) + String.valueOf(someComplexClass.methodB()) + String.valueOf
                (someComplexClass.methodB()) + String.valueOf(someComplexClass.methodB());
        assertThat(num, is("12345"));
    }

    @Test
    public void testMockitoMatchers() {
        /*when(someComplexClass.methodC(anyInt())).thenReturn(true);
        assertThat(someComplexClass.methodC(-100), is(true));*/
        when(someComplexClass.methodC(and(gt(5), leq(20)))).thenReturn("yes");
        assertThat(someComplexClass.methodC(20), is("yes"));
    }

    /*@Test
    public void testTwoSameMethodWithDifferentArguments() {
        when(someComplexClass.methodC(gt(5))).thenReturn("yes");
        when(someComplexClass.methodC(leq(5))).thenReturn("no");
        assertThat(someComplexClass.methodC(6), is("yes"));
    } Shuld not "when.thenReturn" same method*/

    @Test
    public void testSpy() {
        SomeComplexClass someComplexClass = new SomeComplexClass();
        SomeComplexClass spySCC = spy(someComplexClass);
        assertThat(spySCC.methodA(), is("hi"));
        assertThat(spySCC.getX(), is(100));

        when(spySCC.methodB()).thenReturn(10);
        assertThat(spySCC.methodB(), is(10));
    }
}
