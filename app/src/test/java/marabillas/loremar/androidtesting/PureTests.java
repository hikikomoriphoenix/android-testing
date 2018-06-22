package marabillas.loremar.androidtesting;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class PureTests {
    @Test
    public void trueTest() {
        assertThat(true, is(true));
    }

    @Test
    public void withinRange() {
        assertThat(0, both(greaterThanOrEqualTo(0)).and(lessThan(5)));
    }

    @Test
    public void or() {
        assertThat(3, either(is(2)).or(is(3)));
    }

    @Test
    public void isEqualString() {
        assertThat("red", is("red"));//ommitted equalTo
        assertThat("blue", is("blue"));
    }

    @Test
    public void containsGivenText() {
        assertThat("bahaghari", containsString("hari"));
    }

    @Test
    public void oldAssert() {
        int x = 12;
        assert x > 10 : "x is not greater than 10";
    }

    @Test
    public void objectIsNull() {
        Object obj = null;
        assertThat(obj, is(nullValue()));
    }

    @Test
    public void objectIsNotNull() {
        Object obj = new Object();
        assertThat(obj, is(notNullValue()));
    }
}
