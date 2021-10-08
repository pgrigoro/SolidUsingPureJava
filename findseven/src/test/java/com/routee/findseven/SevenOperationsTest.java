package com.routee.findseven;

import org.junit.Assert;
import org.junit.Test;

/**
 * SevenOperations Tester.
 *
 */
public class SevenOperationsTest {

    SevenOperations sevenOps = new SevenOperations();

    @Test
    public void testFindSevenFound() {
        String result = sevenOps.findSeven(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        Assert.assertEquals("Found", result);
    }

    @Test
    public void testFindSevenNoSeven() {
        String result = sevenOps.findSeven(new Integer[] {8, 6, 33, 100});
        Assert.assertEquals("there is no 7 in the array", result);
    }

    @Test
    public void testFindSevenFound2() {
        String result = sevenOps.findSeven(new Integer[] {2, 55, 60, 97, 86});
        Assert.assertEquals("Found", result);
    }

} 
