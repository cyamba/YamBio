package com.yambacode.bio.sequence.alignment.io;

import com.yambacode.bio.sequence.alignment.multiple.MultipleAlignmentResult;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class MultipleAlignmentPrinter {

    private static final String SEQUENCE_REFERENCE_LABEL = "Reference Sequence ";
    private static final String DIRECTIONALITY_5_PRIME = "5' - ";
    private static final String DIRECTIONALITY_3_PRIME = " - 3'";
    private static final char MATCH_CHAR = '|';
    private static final char GAP_CHAR = '-';
    private static final char MISMATCH_CHAR = ' ';
    private static final String NL = "\n";

    public static String format(MultipleAlignmentResult result) {
        List<String> alignedSequences = result.getAlignedSequences();
        StringBuilder output = new StringBuilder();
        int length = alignedSequences.getFirst().length();
        StringBuilder consensusLine = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char consensusChar = computeConsensus(alignedSequences, i);
            consensusLine.append(consensusChar);
        }
        for (int i = 0; i < alignedSequences.size(); i++) {
            output.append("""
                    %s%d: %s%s%s
                    """.formatted(SEQUENCE_REFERENCE_LABEL, i + 1, DIRECTIONALITY_5_PRIME, alignedSequences.get(i), DIRECTIONALITY_3_PRIME));
        }
        output.append("         ").append(consensusLine).append(NL);
        return output.toString();
    }

    static BiPredicate<List<String>, Integer> containsGapAt = (sequences, index) -> sequences.stream()
            .anyMatch(seq -> seq.charAt(index) == GAP_CHAR);

    static Predicate<Long> justOneMatch = (matches) -> matches == 1;

    private static char computeConsensus(List<String> sequences, int index) {
        long matches = countUniqueBasesAtIndex(sequences, index);
        if (justOneMatch.test(matches)) return MATCH_CHAR;
        if (containsGapAt.test(sequences, index)) return GAP_CHAR;
        return MISMATCH_CHAR;
    }

    private static long countUniqueBasesAtIndex(List<String> sequences, int index) {
        return sequences.stream().map(seq -> seq.charAt(index)).distinct().count();
    }
}
