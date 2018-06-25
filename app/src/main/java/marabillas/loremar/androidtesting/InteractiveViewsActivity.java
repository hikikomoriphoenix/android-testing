package marabillas.loremar.androidtesting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import static marabillas.loremar.androidtesting.Utils.getBitmap;

public class InteractiveViewsActivity extends Activity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interactive_views);

        Button button1 = findViewById(R.id.i_button1);
        Button button2 = findViewById(R.id.i_button2);
        Button button3 = findViewById(R.id.i_button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = InteractiveViewsActivity.this.findViewById(R.id.i_image);
                Drawable drawable = image.getDrawable();
                Drawable smile = getResources().getDrawable(R.drawable.ic_sentiment_satisfied_black_24dp);
                Drawable neutral = getResources().getDrawable(R.drawable
                        .ic_sentiment_neutral_black_24dp);
                if (!getBitmap(drawable).sameAs(getBitmap(smile))) {
                    image.setImageDrawable(smile);
                } else {
                    image.setImageDrawable(neutral);
                }
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(new GestureDetector
                    .SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    new EditBoxDialog(InteractiveViewsActivity.this) {
                        @Override
                        void ok(String text) {
                            TextView textBox = InteractiveViewsActivity.this.findViewById(R.id
                                    .i_textBox);
                            textBox.setText(text);
                        }
                    };
                    return true;
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        button3.setLongClickable(true);
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(InteractiveViewsActivity.this, v);
                popupMenu.getMenu().add("alpha");
                popupMenu.getMenu().add("bravo");
                popupMenu.getMenu().add("charlie");
                popupMenu.getMenu().add("delta");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "alpha":
                                break;
                            case "bravo":
                                break;
                            case "charlie":
                                break;
                            case "delta":
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }
}
