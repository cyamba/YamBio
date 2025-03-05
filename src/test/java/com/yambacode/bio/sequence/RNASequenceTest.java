package com.yambacode.bio.sequence;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RNASequenceTest {

    @ParameterizedTest
    @CsvSource({
            "AUGCGUACG, MRT",  // Valid RNA sequence
            "AUGUUUUGA, MF",   // Translation stops at UGA (stop codon)
            "UUUUUCUUA, FFL",  // Multiple amino acids
            "UGAUGA, ''"       // Only stop codons, should return an empty string
    })
    void translateValidRNA(String rna, String expectedProtein) {
        System.out.println(rna);
        ProteinSequence proteinSeq = RNASequence.of(rna).transcribe();
        assertEquals(expectedProtein, proteinSeq.getSequence());
    }
}