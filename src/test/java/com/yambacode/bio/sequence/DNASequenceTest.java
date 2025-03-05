package com.yambacode.bio.sequence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DNASequenceTest {

    @ParameterizedTest
    @CsvSource({
            "ATGC, AUGC",
            "TTTT, UUUU",
            "CGTA, CGUA",
            "GATTACA, GAUUACA"
    })
    void testTranscription(String dna, String expectedRna) {
        DNASequence dnaSeq = new DNASequence(dna);
        RNASequence rnaSeq = dnaSeq.transcribe();
        assertEquals(expectedRna, rnaSeq.getSequence());
    }

    @Test
    void testInvalidDNAThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new DNASequence("XYZ"));
    }


}
