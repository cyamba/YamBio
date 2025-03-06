package com.yambacode.bio.nucleicacid;

import com.yambacode.bio.sequence.DNASequence;
import com.yambacode.bio.sequence.RNASequence;

import java.util.Map;

public class NucleicAcidUtils {

    private static final Map<Character, Character> DNA_COMPLEMENT = Map.of(
            'A', 'T', 'T', 'A', 'C', 'G', 'G', 'C'
    );
    private static final Map<Character, Character> RNA_COMPLEMENT = Map.of(
            'A', 'U', 'U', 'A', 'C', 'G', 'G', 'C'
    );

    public static String reverseComplement(DNASequence sequence) {
        return reverseComplement(sequence.getSequence(), DNA_COMPLEMENT);
    }

    public static String reverseComplement(RNASequence sequence) {
        return reverseComplement(sequence.getSequence(), RNA_COMPLEMENT);
    }

    private static String reverseComplement(String sequence, Map<Character, Character> complementMap) {
        char[] result = new char[sequence.length()];
        for (int i = 0; i < sequence.length(); i++) {
            result[sequence.length() - 1 - i] = complementMap.get(sequence.charAt(i));
        }
        return new String(result);
    }
}
