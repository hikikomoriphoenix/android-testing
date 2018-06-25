package marabillas.loremar.androidtesting.custom_view_matchers;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static marabillas.loremar.androidtesting.Utils.getBitmap;

public class ImageViewMatchers {
    public static Matcher<View> withSrc(int drawableId) {
        return new SourceMatcher(drawableId);
    }

    public static class SourceMatcher extends TypeSafeMatcher<View> {
        private int expectedId;

        public SourceMatcher(int expectedId) {
            super(View.class);
            this.expectedId = expectedId;
        }

        @Override
        protected boolean matchesSafely(View item) {
            if (!(item instanceof ImageView)) {
                return false;
            }
            Drawable actualDrawable = ((ImageView) item).getDrawable();
            Drawable expectedDrawable = item.getContext().getResources().getDrawable(expectedId,
                    null);
            Bitmap actualBitmap = getBitmap(actualDrawable);
            Bitmap expectedBitmap = getBitmap(expectedDrawable);
            return actualBitmap.sameAs(expectedBitmap);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with src: ");
            description.appendValue(expectedId);
        }
    }
}
