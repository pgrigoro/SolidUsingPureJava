package com.route.doremake;

import org.junit.Assert;
import org.junit.Test;

/**
 * RemakeOperations Tester.
 *
 */
public class RemakeOperationsTest {

    RemakeOperations remakeOps = new RemakeOperations();

    @Test
    public void testDoRemakeCats() {
        String result = remakeOps.doRemake("Cats are great pets.");
        Assert.assertEquals("Atscay areway reatgay etspay.", result);
    }

    @Test
    public void testDoRemakeTom() {
        String result = remakeOps.doRemake("Tom got a small piece of pie.");
        Assert.assertEquals("Omtay otgay away mallsay iecepay ofway iepay.", result);
    }

    @Test
    public void testDoRemakeExcitingTale() {
        String result = remakeOps.doRemake("He told us a very exciting tale.");
        Assert.assertEquals("Ehay oldtay usway away eryvay excitingway aletay.", result);
    }

} 
