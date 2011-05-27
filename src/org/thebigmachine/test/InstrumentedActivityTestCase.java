/**
 * 
 */
package org.thebigmachine.test;

import android.app.Activity;


/**
 * @author simon
 *
 */
public class InstrumentedActivityTestCase<T extends Activity> extends android.test.ActivityInstrumentationTestCase2<T> {
    
    /**
     * @param activityClass
     */
    public InstrumentedActivityTestCase(String packageName, Class<T> activityClass) {
        super(packageName, activityClass);
    }
    
    protected static interface assertRaises {
        public void run() throws Throwable;
    }
    
    protected static void assertRaises(Class<? extends Throwable> exceptionClass,
                                       assertRaises assertion) {
        try {
            assertion.run();
        } catch (Throwable t) {
            assertEquals(exceptionClass, t.getClass());
        }
    }
    
    protected static <T> void assertAllAreEqual(T... things) {
        for (int i = 1; i < things.length; ++i) {
            assertEquals(things[0], things[i]);
        }
    }
    
    protected static <T> void assertAllAreEqual(T value, T[] things) {
        for (int i = 0; i < things.length; ++i) {
            assertEquals(value, things[i]);
        }
    }
    
    @Deprecated
    protected static <T> void assertContained(T value, T[] list) {
        for (int i = 0; i < list.length; ++i) {
            if (list[i].equals(value)) {
                return;
            }
        }
        fail("Expected object not contained in list");
    }

    @Deprecated
    protected static <T> void assertNotContained(T value, T[] list) {
        for (int i = 0; i < list.length; ++i) {
            if (list[i].equals(value)) {
                fail("Object unexpectedly contained in list");
            }
        }
    }

    protected static <T> void assertIsOneOf(T value, T... things) {
        for (Object thing: things) {
            if (thing.equals(value)) {
                return;
            }
        }
        fail();
    }
    
    protected static void assertSameContents(Object[] expected, Object[] got) {
        failUnless("Object arrays not same length", expected.length == got.length);
        for (int i = 0; i < expected.length; ++i) {
        }
    }
      
    /**
     * Asserts that the provided value v satisfies min <= v <= max
     * @param value
     * @param min
     * @param max
     */
    protected static void assertWithin(int min, int max, int value) {
        failUnless(String.format("Expected value %d not within %d - %d", value, min, max),
                   min <= value && value <= max);
    }

    protected static void failUnless(Boolean value) {
        failUnless("Expected value is false", value);
    }

    protected static void failUnless(String message, Boolean value) {
        if (!value) {
            fail(message);
        }
    }
}
