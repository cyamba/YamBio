package com.yambacode.bio.sequence.alignment.pairwise;

import com.yambacode.bio.sequence.alignment.PairwiseAlignmentInput;
import org.junit.jupiter.api.Test;

import static com.yambacode.bio.sequence.alignment.io.PairwiseAlignmentPrinter.format;
import static org.junit.jupiter.api.Assertions.*;

class NeedlemanWunschAlignerTest {

    @Test
    public void testNeedlemanWunschAlignment() {
        NeedlemanWunschAligner aligner = new NeedlemanWunschAligner(2, -1, -2);
        PairwiseAlignmentInput input = new PairwiseAlignmentInput("GATTACA", "GCATGCU");
        PairwiseAlignmentResult result = aligner.align(input);

        String expectedFirstAligned = "G-ATTACA";
        String expectedSecondAligned = "GCAT-GCU";

        //assertEquals(expectedFirstAligned, result.getAlignedFirstSequence());
        //assertEquals(expectedSecondAligned, result.getAlignedSecondSequence());

        System.out.println(format(result));
    }

    @Test
    void testPairwiseAlignment() {
        NeedlemanWunschAligner aligner = new NeedlemanWunschAligner(2, -1, -2);
        PairwiseAlignmentInput input = new PairwiseAlignmentInput("GATTACA", "GCATGCU");
        PairwiseAlignmentResult result = aligner.align(input);

        int expectedScore = 2;
        assertEquals(expectedScore, result.getScore());
    }

    @Test
    public void testAlignmentScoreMatrix(){

    }
}