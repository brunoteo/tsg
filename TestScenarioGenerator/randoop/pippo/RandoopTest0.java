import java.lang.Object;
import java.util.Collection;
import stack.util.Stack;
import stack.util.Vector;

import junit.framework.TestCase;

public class RandoopTest0 extends TestCase { 

  public static boolean debug = false;

  public void test0() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test1");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set((-1), (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test1() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test2");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b0 = stack0.addAll(1, (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test2() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test3");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b0 = stack0.addAll((-1), (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test3() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test4");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test4() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test5");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.pop();
      fail("Expected exception of type java.util.EmptyStackException");
    } catch (java.util.EmptyStackException e) {
      // Expected exception.
    }
  }

  public void test5() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test6");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test6() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test7");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(10, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test7() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test8");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    Stack stack1 = new Stack();
    boolean b3 = stack1.add((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b4 = stack0.addAll(100, (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test8() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test9");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(1, (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test9() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test10");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set(10, (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test10() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test11");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)(-1), 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test11() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test12");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test12() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test13");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set(10, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test13() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test14");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.pop();
      fail("Expected exception of type java.util.EmptyStackException");
    } catch (java.util.EmptyStackException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test14() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test15");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set((-1), (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test15() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test16");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)(-1), 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test16() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test17");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    boolean b3 = stack0.add((Object)0);
    Object obj0 = stack0.pop();
    stack0.setSize(1);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test17() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test18");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)0);
    Object obj2 = stack0.push((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)100, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + (-1)+ "'", obj2.equals((-1)));
  }

  public void test18() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test19");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    Stack stack1 = new Stack();
    boolean b0 = stack1.add((Object)10);
    boolean b1 = stack1.add((Object)(-1));
    boolean b2 = stack1.add((Object)100);
    stack1.addElement((Object)0);
    boolean b3 = stack0.addAll((Collection)stack1);
    boolean b4 = stack0.add((Object)(-1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b4 == true);
  }

  public void test19() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test20");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)0);
    stack0.setSize(10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add((-1), (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test20() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test21");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    boolean b3 = stack0.add((Object)0);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)0, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test21() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test22");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)1, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test22() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test23");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    Stack stack1 = new Stack();
    boolean b0 = stack1.add((Object)10);
    boolean b1 = stack1.add((Object)(-1));
    boolean b2 = stack1.add((Object)100);
    stack1.addElement((Object)0);
    boolean b3 = stack0.addAll((Collection)stack1);
    Object obj1 = stack0.pop();
    Object obj2 = stack0.pop();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 100+ "'", obj2.equals(100));
  }

  public void test23() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test24");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    stack0.add(1, (Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test24() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test25");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    Object obj0 = stack0.pop();
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setSize((-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test25() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test26");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)1, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + (-1)+ "'", obj0.equals((-1)));
  }

  public void test26() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test27");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test27() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test28");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)0, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
  }

  public void test28() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test29");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set(1, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test29() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test30");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    stack0.add(1, (Object)100);
    stack0.setSize(100);
    boolean b2 = stack0.add((Object)1);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)1);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 1+ "'", obj0.equals(1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
  }

  public void test30() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test31");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    stack0.addElement((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set(100, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test31() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test32");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set(0, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test32() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test33");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)0);
    stack0.addElement((Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set((-1), (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test33() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test34");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    stack0.add(1, (Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)100, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test34() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test35");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    Stack stack1 = new Stack();
    boolean b0 = stack1.add((Object)10);
    boolean b1 = stack1.add((Object)(-1));
    boolean b2 = stack1.add((Object)100);
    stack1.addElement((Object)0);
    boolean b3 = stack0.addAll((Collection)stack1);
    boolean b4 = stack1.add((Object)(-1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b4 == true);
  }

  public void test35() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test36");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)(-1), 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test36() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test37");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)0);
    stack0.setSize(10);
    Stack stack1 = new Stack();
    boolean b2 = stack1.add((Object)10);
    Object obj0 = stack1.pop();
    boolean b3 = stack0.addAll(1, (Collection)stack1);
    boolean b4 = stack0.add((Object)100);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b4 == true);
  }

  public void test37() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test38");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    Stack stack1 = new Stack();
    boolean b0 = stack1.add((Object)10);
    boolean b1 = stack1.add((Object)(-1));
    boolean b2 = stack1.add((Object)100);
    stack1.addElement((Object)0);
    boolean b3 = stack0.addAll((Collection)stack1);
    Object obj1 = stack0.push((Object)10);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test38() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test39");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add((-1), (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
  }

  public void test39() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test40");

    Stack stack0 = new Stack();
    Object obj0 = stack0.push((Object)0);
    boolean b0 = stack0.add((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test40() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test41");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    stack0.add(1, (Object)100);
    stack0.setSize(100);
    boolean b2 = stack0.add((Object)1);
    Object obj0 = stack0.pop();
    stack0.setSize(1);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 1+ "'", obj0.equals(1));
  }

  public void test41() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test42");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)0);
    stack0.setSize(10);
    Stack stack1 = new Stack();
    boolean b2 = stack1.add((Object)10);
    Object obj0 = stack1.pop();
    boolean b3 = stack0.addAll(1, (Collection)stack1);
    stack0.setSize(0);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == false);
  }

  public void test42() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test43");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)0);
    stack0.setSize(10);
    Stack stack1 = new Stack();
    boolean b2 = stack1.add((Object)10);
    Object obj0 = stack1.pop();
    boolean b3 = stack0.addAll(1, (Collection)stack1);
    Object obj1 = stack0.push((Object)(-1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + (-1)+ "'", obj1.equals((-1)));
  }

  public void test43() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test44");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(10, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test44() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test45");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    Object obj0 = stack0.pop();
    boolean b2 = stack0.add((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)100, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + (-1)+ "'", obj0.equals((-1)));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test45() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test46");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    boolean b3 = stack0.add((Object)0);
    stack0.addElement((Object)0);
    Object obj0 = stack0.pop();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test46() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test47");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    stack0.add(1, (Object)100);
    Stack stack1 = new Stack();
    Object obj0 = stack1.push((Object)0);
    stack1.addElement((Object)0);
    boolean b2 = stack0.addAll((Collection)stack1);
    Object obj1 = stack0.pop();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
  }

  public void test47() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test48");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    Object obj0 = stack0.pop();
    stack0.setElementAt((Object)(-1), 0);
    Object obj1 = stack0.push((Object)100);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test48() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test49");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)10);
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test49() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test50");

    Stack stack0 = new Stack();
    boolean b0 = stack0.add((Object)10);
    boolean b1 = stack0.add((Object)(-1));
    boolean b2 = stack0.add((Object)100);
    stack0.addElement((Object)0);
    boolean b3 = stack0.add((Object)0);
    Object obj0 = stack0.set(0, (Object)(-1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }


}