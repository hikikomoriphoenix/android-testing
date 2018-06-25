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
    public static Matcher<View> withDrawable(int drawableId) {
        return new DrawableMatcher(drawableId);
    }

    public static class DrawableMatcher extends TypeSafeMatcher<View> {
        private int expectedId;

        public DrawableMatcher(int expectedId) {
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
            description.appendText("with drawable from resource id: ");
            description.appendValue(expectedId);
        }
    }
}
