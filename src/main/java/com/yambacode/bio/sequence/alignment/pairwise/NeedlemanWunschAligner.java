package com.yambacode.bio.sequence.alignment.pairwise;

import com.yambacode.bio.sequence.alignment.AlignmentResult;
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
    public AlignmentResult align(PairwiseAlignmentInput input) {
        String firstSequence = input.firstSequence();
        String secondSequence = input.secondSequence();
        int[][] scoreMatrix = new int[firstSequence.length() + 1][secondSequence.length() + 1];

        initializeScoreMatrix(scoreMatrix,firstSequence.length(),secondSequence.length());

        // Fill score matrix
        for (int i = 1; i <= firstSequence.length(); i++) {
            for (int j = 1; j <= secondSequence.length(); j++) {
                int matchScore = scoreMatrix[i - 1][j - 1] + (firstSequence.charAt(i - 1) == secondSequence.charAt(j - 1) ? match : mismatch);
                int delete = scoreMatrix[i - 1][j] + gap;
                int insert = scoreMatrix[i][j - 1] + gap;
                scoreMatrix[i][j] = Math.max(matchScore, Math.max(delete, insert));
            }
        }

        return new AlignmentResult(firstSequence, secondSequence, scoreMatrix[firstSequence.length()][secondSequence.length()]);
    }

    private void initializeScoreMatrix(int[][] scoreMatrix, int firstLength, int secondLength) {
        int minLength = Math.min(firstLength, secondLength);
        for (int i = 0; i <= minLength; i++) {
            scoreMatrix[i][0] = i * gap;
            scoreMatrix[0][i] = i * gap;
        }
        boolean firstIsLonger = firstLength > secondLength;
        if (firstIsLonger) {
            for (int i = minLength + 1; i <= firstLength; i++) {
                scoreMatrix[i][0] = i * gap;
            }
        } else {
            for (int i = minLength + 1; i <= secondLength; i++) {
                scoreMatrix[0][i] = i * gap;
            }
        }
    }
}
