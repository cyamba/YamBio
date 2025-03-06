package com.yambacode.bio.sequence;

import static org.junit.jupiter.api.Assertions.*;

import com.yambacode.bio.aminoacid.AminoAcid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

public class CodonTableTest {

    private static Stream<Arguments> provideValidCodons() {
        return Stream.of(
                Arguments.of("UUU", AminoAcid.F),
                Arguments.of("UUC", AminoAcid.F),
                Arguments.of("AUG", AminoAcid.M), // Start codon
                Arguments.of("UGA", AminoAcid.STOP),
                Arguments.of("GCU", AminoAcid.A),
                Arguments.of("UGG", AminoAcid.W)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidCodons")
    @DisplayName("Test valid codon translation")
    void testTranslateValidCodons(String codon, AminoAcid expected) {
        assertEquals(expected, CodonTable.translate(codon));
    }

    @Test
    @DisplayName("Test invalid codon throws exception")
    void testTranslateInvalidCodon() {
        assertThrows(IllegalArgumentException.class, () -> CodonTable.translate("XXX"));
        assertThrows(IllegalArgumentException.class, () -> CodonTable.translate("U"));
        assertThrows(IllegalArgumentException.class, () -> CodonTable.translate(null));
    }

    @Test
    @DisplayName("Test codon table is unmodifiable")
    void testCodonMapUnmodifiable() {
        Map<String, AminoAcid> codonMap = CodonTable.getCodonMap();
        assertThrows(UnsupportedOperationException.class, () -> codonMap.put("AAA", AminoAcid.K));
    }
}
