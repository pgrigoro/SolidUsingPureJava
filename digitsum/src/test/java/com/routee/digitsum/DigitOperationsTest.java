package com.route.digitsum;


import org.junit.Assert;
import org.junit.Test;

/**
 * DigitOperations Tester.
 *
 */
public class DigitOperationsTest {

    DigitOperations digitOps = new DigitOperations();

    @Test
    public void testDigitSum10() {
        int sum = digitOps.digitSum(10);
        Assert.assertEquals(1, sum);
    }

    @Test
    public void testDigitSum38() {
        int sum = digitOps.digitSum(38);
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDigitSum785() {
        int sum = digitOps.digitSum(785);
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDigitSum99892() {
        int sum = digitOps.digitSum(99892);
        Assert.assertEquals(1, sum);
    }

} 
