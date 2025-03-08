package com.yambacode.bio.sequence.alignment.io;

import com.yambacode.bio.sequence.alignment.pairwise.PairwiseAlignmentResult;

public class PairwiseAlignmentPrinter {

    private static final String REFERENCE_SEQUENCE_LABEL = "Reference Sequence ";
    private static final String VARIANT_SEQUENCE_LABEL = "Variant Sequence ";
    private static final String DIRECTIONALITY_5_PRIME = "5' - ";
    private static final String DIRECTIONALITY_3_PRIME = " - 3'";
    private static final char MATCH_CHAR = '|';
    private static final char GAP_CHAR = '-';
    private static final char MISMATCH_CHAR = ' ';

    public static String format(PairwiseAlignmentResult result) {
        String firstSeq = result.alignedFirstSequence();
        String secondSeq = result.alignedSecondSequence();

        int firstLength = firstSeq.length();
        int secondLength = secondSeq.length();
        int maxLength = Math.max(firstLength, secondLength);

        StringBuilder firstPadded = new StringBuilder(firstSeq);
        StringBuilder secondPadded = new StringBuilder(secondSeq);

        padShorterSequenceWithGaps(firstPadded, secondPadded, maxLength);

        StringBuilder matchLine = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            char firstBase = firstPadded.charAt(i);
            char secondBase = secondPadded.charAt(i);
            matchLine.append(determineMatchType(firstBase, secondBase));
        }

        return """
                %s%s%s%s
                         %s
                %s%s%s%s
                """.formatted(
                REFERENCE_SEQUENCE_LABEL, DIRECTIONALITY_5_PRIME, firstPadded, DIRECTIONALITY_3_PRIME,
                matchLine,
                VARIANT_SEQUENCE_LABEL, DIRECTIONALITY_5_PRIME, secondPadded, DIRECTIONALITY_3_PRIME
        );
    }

    private static void padShorterSequenceWithGaps(StringBuilder firstPadded, StringBuilder secondPadded, int maxLength) {
        while (firstPadded.length() < maxLength) {
            firstPadded.append(GAP_CHAR);
        }
        while (secondPadded.length() < maxLength) {
            secondPadded.append(GAP_CHAR);
        }
    }

    private static char determineMatchType(char firstBase, char secondBase) {
        return switch (firstBase) {
            case GAP_CHAR -> GAP_CHAR;
            default -> (firstBase == secondBase) ? MATCH_CHAR : MISMATCH_CHAR;
        };
    }
}
