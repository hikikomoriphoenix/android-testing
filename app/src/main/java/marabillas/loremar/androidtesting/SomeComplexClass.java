package marabillas.loremar.androidtesting;

import android.app.Activity;

public class SomeComplexClass {
    private int x;

    SomeComplexClass() {
        x = 100;
    }
    String methodA() {
        return "hi";
    }

    int methodB() {
        return 0;
    }

    String methodC(int x) {
        return "no";
    }

    int getX() {
        return x;
    }

    void haveAString(String s) {
    }

    String takeSomeStrings(String a, String b, String c) {
        return null;
    }

    static class InnerClass {
        Activity a;
        Activity v;

        InnerClass(Activity a, Activity v) {
            this.a = a;
            this.v = v;
        }

        String getAString() {
            return a.toString();
        }

        String getVString() {
            return v.getPackageName();
        }
    }
}
