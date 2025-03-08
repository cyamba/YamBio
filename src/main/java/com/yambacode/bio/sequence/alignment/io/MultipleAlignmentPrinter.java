package com.yambacode.bio.sequence.alignment.io;

import com.yambacode.bio.sequence.alignment.multiple.MultipleAlignmentResult;

import java.util.List;

public class MultipleAlignmentPrinter {

    private static final String SEQUENCE_REFERENCE_LABEL = "Reference Sequence ";
    private static final String DIRECTIONALITY_5_PRIME = "5' - ";
    private static final String DIRECTIONALITY_3_PRIME = " - 3'";
    private static final String MATCH_CHAR = "|";
    private static final String GAP_CHAR = "-";
    private static final String MISMATCH_CHAR = " ";
    private static final String NL = "\n";

    public static String format(MultipleAlignmentResult result) {
        List<String> alignedSequences = result.alignedSequences();
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

    private static char computeConsensus(List<String> sequences, int index) {
        long matches = sequences.stream().map(seq -> seq.charAt(index)).distinct().count();
        if (matches == 1) return MATCH_CHAR.charAt(0);
        if (sequences.stream().anyMatch(seq -> seq.charAt(index) == GAP_CHAR.charAt(0))) return GAP_CHAR.charAt(0);
        return MISMATCH_CHAR.charAt(0);
    }
}
