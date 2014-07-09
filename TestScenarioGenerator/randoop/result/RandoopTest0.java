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
      Object obj0 = stack0.peek();
      fail("Expected exception of type java.util.EmptyStackException");
    } catch (java.util.EmptyStackException e) {
      // Expected exception.
    }
  }

  public void test1() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test2");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test2() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test3");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test3() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test4");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b0 = stack0.addAll(10, (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test4() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test5");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)1, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test5() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test6");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(10, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test6() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test7");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test7() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test8");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj0 = stack1.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b0 = stack0.addAll((-1), (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test8() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test9");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
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
  }

  public void test9() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test10");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack1.set(1, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test10() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test11");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Stack stack1 = new Stack();
    stack1.insertElementAt((Object)10, 0);
    boolean b0 = stack1.add((Object)0);
    Stack stack2 = new Stack();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    boolean b1 = stack2.addAll((Collection)stack3);
    boolean b2 = stack1.addAll(0, (Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b3 = stack0.addAll(10, (Collection)stack3);
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
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test12() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test13");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)100, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test13() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test14");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    Object obj1 = stack0.set(1, (Object)10);
    Object obj2 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add((-1), (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 10+ "'", obj2.equals(10));
  }

  public void test14() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test15");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)(-1), 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test15() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test16");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set((-1), (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test16() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test17");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.insertElementAt((Object)(-1), 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)0, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test17() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test18");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Stack stack3 = new Stack();
    stack3.insertElementAt((Object)10, 0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b3 = stack0.addAll((-1), (Collection)stack3);
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

  public void test18() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test19");

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

  public void test19() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test20");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)1, 10);
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

  public void test20() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test21");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b0 = stack0.addAll(1, (Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack1.setSize((-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test21() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test22");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack2.setElementAt((Object)10, 1);
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

  public void test22() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test23");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack2.add((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack2.setElementAt((Object)10, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test23() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test24");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack0.set(0, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test24() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test25");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)10, (-1));
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
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test26() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test27");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b0 = stack0.addAll(1, (Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test27() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test28");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj1 = stack0.set(1, (Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
  }

  public void test28() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test29");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b0 = stack0.addAll(1, (Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.set(10, (Object)1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test29() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test30");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    stack0.addElement((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)100, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test30() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test31");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    Object obj1 = stack3.peek();
    boolean b3 = stack0.addAll((Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack3.add(100, (Object)0);
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test31() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test32");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test32() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test33");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Stack stack3 = new Stack();
    stack3.insertElementAt((Object)10, 0);
    boolean b3 = stack0.addAll((Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack3.set(100, (Object)10);
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

  public void test33() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test34");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    Object obj1 = stack0.set(1, (Object)10);
    Object obj2 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)1, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 10+ "'", obj2.equals(10));
  }

  public void test34() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test35");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj0 = stack1.set(100, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test35() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test36");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.add(1, (Object)100);
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
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test36() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test37");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.insertElementAt((Object)1, 0);
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
    assertTrue(b2 == true);
  }

  public void test37() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test38");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    boolean b0 = stack0.add((Object)(-1));
    Object obj1 = stack0.push((Object)0);
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
  }

  public void test38() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test39");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.insertElementAt((Object)1, 0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)100, 10);
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

  public void test39() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test40");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b0 = stack0.addAll(1, (Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack1.setElementAt((Object)10, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test40() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test41");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj0 = stack0.peek();
    stack0.addElement((Object)0);
    Object obj1 = stack0.peek();
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
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
  }

  public void test41() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test42");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack2.push((Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack2.insertElementAt((Object)100, 10);
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
    assertTrue("'" + obj0 + "' != '" + 1+ "'", obj0.equals(1));
  }

  public void test42() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test43");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    Object obj1 = stack3.peek();
    boolean b2 = stack3.add((Object)1);
    boolean b3 = stack1.addAll(0, (Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b4 = stack0.addAll(100, (Collection)stack3);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test43() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test44");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    Object obj1 = stack0.set(1, (Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)0, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
  }

  public void test44() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test45");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test45() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test46");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack2.add((Object)0);
    stack2.setSize(1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack2.set(1, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test46() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test47");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    stack0.setSize(0);
    Stack stack1 = new Stack();
    stack1.insertElementAt((Object)10, 0);
    boolean b0 = stack1.add((Object)0);
    Stack stack2 = new Stack();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    boolean b1 = stack2.addAll((Collection)stack3);
    boolean b2 = stack1.addAll(0, (Collection)stack3);
    Object obj0 = stack1.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b3 = stack0.addAll(1, (Collection)stack1);
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

  public void test47() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test48");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
    Object obj0 = stack0.push((Object)10);
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
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test48() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test49");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    Object obj0 = stack3.push((Object)0);
    Object obj1 = stack3.set(0, (Object)100);
    boolean b3 = stack2.addAll((Collection)stack3);
    stack3.add(0, (Object)100);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test49() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test50");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)100, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test50() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test51");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Stack stack3 = new Stack();
    Stack stack4 = new Stack();
    stack4.addElement((Object)10);
    boolean b3 = stack3.addAll((Collection)stack4);
    Object obj0 = stack3.peek();
    Object obj1 = stack3.peek();
    boolean b4 = stack2.addAll((Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack2.add((-1), (Object)10);
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
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b4 == true);
  }

  public void test51() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test52");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    stack0.addElement((Object)(-1));
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj0 = stack1.push((Object)0);
    Object obj1 = stack1.set(0, (Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b0 = stack0.addAll((-1), (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test52() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test53");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
    Stack stack3 = new Stack();
    Stack stack4 = new Stack();
    stack4.addElement((Object)10);
    boolean b4 = stack3.addAll((Collection)stack4);
    stack3.addElement((Object)(-1));
    boolean b5 = stack0.addAll((Collection)stack3);
    Object obj0 = stack3.push((Object)100);
    
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b5 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 100+ "'", obj0.equals(100));
  }

  public void test53() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test54");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(10, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
  }

  public void test54() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test55");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    stack0.addElement((Object)0);
    Object obj2 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj3 = stack0.peek();
      fail("Expected exception of type java.util.EmptyStackException");
    } catch (java.util.EmptyStackException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
  }

  public void test55() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test56");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setSize((-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test56() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test57");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Object obj1 = stack0.peek();
    Object obj2 = stack0.push((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)(-1), 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + (-1)+ "'", obj2.equals((-1)));
  }

  public void test57() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test58");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
    stack0.insertElementAt((Object)0, 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)0);
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

  public void test58() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test59");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.peek();
    stack0.insertElementAt((Object)1, 1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj2 = stack2.push((Object)0);
    stack2.add(0, (Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b1 = stack0.addAll((-1), (Collection)stack2);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
  }

  public void test59() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test60");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b0 = stack1.addAll((Collection)stack2);
    boolean b1 = stack0.addAll(0, (Collection)stack1);
    stack0.setElementAt((Object)10, 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test60() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test61");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.peek();
    boolean b0 = stack1.add((Object)1);
    stack1.setElementAt((Object)0, 1);
    Object obj2 = stack1.push((Object)100);
    boolean b1 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.insertElementAt((Object)10, 0);
    boolean b2 = stack1.addAll((Collection)stack2);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 100+ "'", obj2.equals(100));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test61() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test62");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    Stack stack1 = new Stack();
    stack1.insertElementAt((Object)10, 0);
    boolean b0 = stack1.add((Object)0);
    Stack stack2 = new Stack();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    boolean b1 = stack2.addAll((Collection)stack3);
    boolean b2 = stack1.addAll(0, (Collection)stack3);
    Object obj2 = stack3.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b3 = stack0.addAll((-1), (Collection)stack3);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 10+ "'", obj2.equals(10));
  }

  public void test62() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test63");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)1);
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

  public void test63() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test64");

    Stack stack0 = new Stack();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test64() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test65");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
    Stack stack3 = new Stack();
    Stack stack4 = new Stack();
    stack4.addElement((Object)10);
    boolean b4 = stack3.addAll((Collection)stack4);
    stack3.addElement((Object)(-1));
    boolean b5 = stack0.addAll((Collection)stack3);
    Object obj0 = stack3.pop();
    
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b5 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + (-1)+ "'", obj0.equals((-1)));
  }

  public void test65() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test66");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.peek();
      fail("Expected exception of type java.util.EmptyStackException");
    } catch (java.util.EmptyStackException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test66() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test67");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)100);
    stack0.insertElementAt((Object)(-1), 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, (-1));
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test67() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test68");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.add(0, (Object)(-1));
    stack0.insertElementAt((Object)1, 0);
    stack0.setElementAt((Object)10, 0);
    stack0.setElementAt((Object)(-1), 1);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test68() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test69");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b1 = stack1.add((Object)(-1));
    Object obj2 = stack1.push((Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b2 = stack0.addAll(10, (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
  }

  public void test69() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test70");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.insertElementAt((Object)(-1), 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(10, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test70() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test71");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.peek();
    boolean b0 = stack1.add((Object)1);
    stack1.setElementAt((Object)0, 1);
    Object obj2 = stack1.push((Object)100);
    boolean b1 = stack0.addAll((Collection)stack1);
    Object obj3 = stack0.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 100+ "'", obj2.equals(100));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj3 + "' != '" + 100+ "'", obj3.equals(100));
  }

  public void test71() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test72");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Object obj1 = stack0.push((Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.set(10, (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test72() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test73");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.peek();
    boolean b0 = stack1.add((Object)1);
    stack1.setElementAt((Object)0, 1);
    Object obj2 = stack1.push((Object)100);
    boolean b1 = stack0.addAll((Collection)stack1);
    stack1.add(0, (Object)(-1));
    boolean b2 = stack1.add((Object)0);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 100+ "'", obj2.equals(100));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test73() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test74");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Object obj1 = stack0.push((Object)100);
    stack0.add(1, (Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test74() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test75");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b0 = stack1.addAll((Collection)stack2);
    boolean b1 = stack0.addAll(0, (Collection)stack1);
    Stack stack3 = new Stack();
    Stack stack4 = new Stack();
    stack4.addElement((Object)10);
    boolean b2 = stack3.addAll((Collection)stack4);
    Stack stack5 = new Stack();
    stack5.addElement((Object)10);
    Object obj0 = stack5.peek();
    boolean b3 = stack5.add((Object)1);
    boolean b4 = stack3.addAll(0, (Collection)stack5);
    Object obj1 = stack5.pop();
    boolean b5 = stack1.addAll((Collection)stack5);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b4 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b5 == true);
  }

  public void test75() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test76");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    boolean b3 = stack0.add((Object)(-1));
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test76() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test77");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    Object obj1 = stack3.peek();
    boolean b3 = stack0.addAll((Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.set(10, (Object)0);
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test77() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test78");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Object obj1 = stack0.push((Object)100);
    stack0.insertElementAt((Object)(-1), 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)0);
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test78() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test79");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    Object obj1 = stack0.set(0, (Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test79() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test80");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj0 = stack0.set(0, (Object)100);
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
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test80() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test81");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)(-1), 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test81() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test82");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    stack0.addElement((Object)0);
    Object obj2 = stack0.peek();
    Object obj3 = stack0.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setSize((-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj3 + "' != '" + 0+ "'", obj3.equals(0));
  }

  public void test82() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test83");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj1 = stack0.set(1, (Object)100);
    Object obj2 = stack0.pop();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)0, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 10+ "'", obj2.equals(10));
  }

  public void test83() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test84");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    stack0.addElement((Object)0);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj2 = stack1.push((Object)0);
    stack1.add(0, (Object)1);
    boolean b0 = stack0.addAll((Collection)stack1);
    Object obj3 = stack1.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj4 = stack1.set((-1), (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj3 + "' != '" + 0+ "'", obj3.equals(0));
  }

  public void test84() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test85");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    stack0.setSize(0);
    stack0.setSize(1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)0, (-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
  }

  public void test85() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test86");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Object obj1 = stack0.peek();
    Object obj2 = stack0.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)0, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
  }

  public void test86() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test87");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    stack0.add(1, (Object)(-1));
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
  }

  public void test87() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test88");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    boolean b0 = stack0.add((Object)0);
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b1 = stack1.addAll((Collection)stack2);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj0 = stack0.pop();
    Stack stack3 = new Stack();
    stack3.addElement((Object)10);
    Object obj1 = stack3.peek();
    boolean b3 = stack0.addAll((Collection)stack3);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)10, (-1));
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
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b3 == true);
  }

  public void test88() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test89");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    Object obj1 = stack0.pop();
    stack0.addElement((Object)0);
    Object obj2 = stack0.peek();
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    boolean b0 = stack1.addAll((Collection)stack2);
    Object obj3 = stack1.peek();
    Object obj4 = stack1.pop();
    boolean b1 = stack0.addAll(1, (Collection)stack1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack1.add(1, (Object)0);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 0+ "'", obj2.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj3 + "' != '" + 10+ "'", obj3.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj4 + "' != '" + 10+ "'", obj4.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == false);
  }

  public void test89() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test90");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    stack0.add(0, (Object)1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.peek();
    boolean b0 = stack1.add((Object)1);
    stack1.setElementAt((Object)0, 1);
    Object obj2 = stack1.push((Object)100);
    boolean b1 = stack0.addAll((Collection)stack1);
    Object obj3 = stack0.peek();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setElementAt((Object)1, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj2 + "' != '" + 100+ "'", obj2.equals(100));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj3 + "' != '" + 100+ "'", obj3.equals(100));
  }

  public void test90() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test91");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Object obj1 = stack0.push((Object)100);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.insertElementAt((Object)10, 10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 100+ "'", obj1.equals(100));
  }

  public void test91() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test92");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.setSize((-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test92() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test93");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.add(0, (Object)(-1));
    stack0.setSize(10);
    stack0.addElement((Object)10);
    stack0.setSize(10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set(100, (Object)100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test93() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test94");

    Stack stack0 = new Stack();
    stack0.insertElementAt((Object)10, 0);
    stack0.addElement((Object)(-1));
    Object obj0 = stack0.push((Object)10);
    stack0.add(1, (Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj1 = stack0.set(100, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
  }

  public void test94() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test95");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.peek();
    boolean b0 = stack0.add((Object)1);
    stack0.setElementAt((Object)0, 1);
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    Object obj1 = stack1.peek();
    boolean b1 = stack1.add((Object)1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      boolean b2 = stack0.addAll((-1), (Collection)stack1);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
  }

  public void test95() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test96");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    Object obj1 = stack0.set(1, (Object)100);
    stack0.setElementAt((Object)10, 0);
    stack0.add(1, (Object)10);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack0.add(100, (Object)10);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 1+ "'", obj1.equals(1));
  }

  public void test96() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test97");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.add(0, (Object)(-1));
    stack0.setSize(10);
    stack0.addElement((Object)10);
    stack0.setSize(10);
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
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
  }

  public void test97() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test98");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    stack1.insertElementAt((Object)1, 1);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      stack1.insertElementAt((Object)1, 100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }

  public void test98() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test99");

    Stack stack0 = new Stack();
    Stack stack1 = new Stack();
    stack1.addElement((Object)10);
    boolean b0 = stack0.addAll((Collection)stack1);
    Stack stack2 = new Stack();
    stack2.addElement((Object)10);
    Object obj0 = stack2.peek();
    boolean b1 = stack2.add((Object)1);
    boolean b2 = stack0.addAll(0, (Collection)stack2);
    stack0.add(0, (Object)(-1));
    stack0.setSize(10);
    Object obj1 = stack0.set(1, (Object)0);
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.set((-1), (Object)(-1));
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 10+ "'", obj0.equals(10));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b1 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b2 == true);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 10+ "'", obj1.equals(10));
  }

  public void test99() throws Throwable {

    if(debug) System.out.println("%nRandoopTest0.test100");

    Stack stack0 = new Stack();
    stack0.addElement((Object)10);
    Object obj0 = stack0.push((Object)0);
    Object obj1 = stack0.set(1, (Object)10);
    stack0.insertElementAt((Object)0, 0);
    boolean b0 = stack0.add((Object)(-1));
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      Object obj2 = stack0.set((-1), (Object)100);
      fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj0 + "' != '" + 0+ "'", obj0.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + obj1 + "' != '" + 0+ "'", obj1.equals(0));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(b0 == true);
  }


}