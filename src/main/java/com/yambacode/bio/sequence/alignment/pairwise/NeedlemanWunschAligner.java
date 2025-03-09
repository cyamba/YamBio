package com.yambacode.bio.sequence.alignment.pairwise;

import com.yambacode.bio.sequence.alignment.PairwiseAlignmentInput;

public class NeedlemanWunschAligner implements PairwiseSequenceAligner {
    private final int match;
    private final int mismatch;
    private final int gap;

    public NeedlemanWunschAligner(int match, int mismatch, int gap) {
        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
    }

    @Override
        public PairwiseAlignmentResult align(PairwiseAlignmentInput input) {
            String firstSequence = input.getFirstSequence();
            String secondSequence = input.getSecondSequence();
            int[][] scoreMatrix = initializeScoreMatrix(firstSequence,secondSequence);

            for (int i = 1; i <= firstSequence.length(); i++) {
                for (int j = 1; j <= secondSequence.length(); j++) {
                    int matchScore = scoreMatrix[i - 1][j - 1] + (firstSequence.charAt(i - 1) == secondSequence.charAt(j - 1) ? match : mismatch);
                    int delete = scoreMatrix[i - 1][j] + gap;
                    int insert = scoreMatrix[i][j - 1] + gap;
                    scoreMatrix[i][j] = Math.max(matchScore, Math.max(delete, insert));
                }
            }

            return new PairwiseAlignmentResult(firstSequence, secondSequence, scoreMatrix[firstSequence.length()][secondSequence.length()]);
        }

    int[][] initializeScoreMatrix(String firstSequence, String secondSequence) {
        int firstSequenceLength = firstSequence.length();
        int secondSequenceLength = secondSequence.length();
        int[][] scoreMatrix = new int[firstSequenceLength + 1][secondSequenceLength + 1];
        int minLength = Math.min(firstSequenceLength, secondSequenceLength);
        for (int i = 0; i <= minLength; i++) {
            scoreMatrix[i][0] = i * gap;
            scoreMatrix[0][i] = i * gap;
        }
        boolean firstIsLonger = firstSequenceLength > secondSequenceLength;
        if (firstIsLonger) {
            for (int i = minLength + 1; i <= firstSequenceLength; i++) {
                scoreMatrix[i][0] = i * gap;
            }
        } else {
            for (int i = minLength + 1; i <= secondSequenceLength; i++) {
                scoreMatrix[0][i] = i * gap;
            }
        }
        return scoreMatrix;
    }
}
