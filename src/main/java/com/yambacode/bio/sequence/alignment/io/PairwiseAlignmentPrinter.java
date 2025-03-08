package com.yambacode.bio.sequence.alignment.io;

import com.yambacode.bio.sequence.alignment.pairwise.PairwiseAlignmentResult;

public class PairwiseAlignmentPrinter {

    private static final String REFERENCE_SEQUENCE_LABEL = "Reference Sequence ";
    private static final String VARIANT_SEQUENCE_LABEL = "Variant Sequence ";
    private static final String DIRECTIONALITY_5_PRIME = "5' - ";
    private static final String DIRECTIONALITY_3_PRIME = " - 3'";
    private static final String MATCH_CHAR = "|";
    private static final String GAP_CHAR = "-";
    private static final String MISMATCH_CHAR = " ";
    private static final String NL = "\n";

    public static String format(PairwiseAlignmentResult result) {
        String firstSeq = result.alignedFirstSequence();
        String secondSeq = result.alignedSecondSequence();

        StringBuilder matchLine = new StringBuilder();
        for (int i = 0; i < firstSeq.length(); i++) {
            char c1 = firstSeq.charAt(i);
            char c2 = secondSeq.charAt(i);
            matchLine.append(switch (c1) {
                case char c when c == c2 -> MATCH_CHAR;
                case '-' -> GAP_CHAR;
                default -> (c2 == '-' ? GAP_CHAR : MISMATCH_CHAR);
            });
        }
        return """
                %s%s%s%s
                         %s
                %s%s%s%s
                """.formatted(
                REFERENCE_SEQUENCE_LABEL, DIRECTIONALITY_5_PRIME, firstSeq, DIRECTIONALITY_3_PRIME,
                matchLine,
                VARIANT_SEQUENCE_LABEL, DIRECTIONALITY_5_PRIME, secondSeq, DIRECTIONALITY_3_PRIME
        );
    }
}
