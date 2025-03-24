package com.yambacode.bio.sequence.alignment.pairwise;

import com.yambacode.bio.sequence.alignment.PairwiseAlignmentInput;
import com.yambacode.bio.utils.TriPredicate;

import java.util.function.BiConsumer;

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
        int[][] scoreMatrix = initializeScoreMatrix(firstSequence, secondSequence);

        for (int i = 1; i <= firstSequence.length(); i++) {
            for (int j = 1; j <= secondSequence.length(); j++) {
                int matchScore = scoreMatrix[i - 1][j - 1] + (firstSequence.charAt(i - 1) == secondSequence.charAt(j - 1) ? match : mismatch);
                int delete = scoreMatrix[i - 1][j] + gap;
                int insert = scoreMatrix[i][j - 1] + gap;
                scoreMatrix[i][j] = Math.max(matchScore, Math.max(delete, insert));
            }
        }

        return (PairwiseAlignmentResult) traceback(firstSequence, secondSequence, scoreMatrix);
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

    private final TriPredicate<Integer, Integer, Integer> isDiagonalMove = (score, scoreDiag, match) ->
            score == scoreDiag + (match);

    private final TriPredicate<Integer, Integer, Integer> isVerticalMove = (score, scoreUp, gap) ->
            score == scoreUp + gap;

    private final BiConsumer<StringBuilder, Character> insertIntoFirstSequence = (sb, c) -> sb.insert(0, c);
    private final BiConsumer<StringBuilder, Character> insertIntoSecondSequence = (sb, c) -> sb.insert(0, c);

    PairwiseAlignmentResult traceback(String firstSequence, String secondSequence, int[][] scoreMatrix) {
        StringBuilder alignedFirst = new StringBuilder();
        StringBuilder alignedSecond = new StringBuilder();

        int i = firstSequence.length();
        int j = secondSequence.length();

        while (i > 0 && j > 0) {
            int score = scoreMatrix[i][j];
            int scoreDiag = scoreMatrix[i - 1][j - 1];
            int scoreUp = scoreMatrix[i - 1][j];

            if (isDiagonalMove.test(score, scoreDiag, match)) {
                insertIntoFirstSequence.accept(alignedFirst, firstSequence.charAt(i - 1));
                insertIntoSecondSequence.accept(alignedSecond, secondSequence.charAt(j - 1));
                i--;
                j--;
            } else if (isVerticalMove.test(score, scoreUp, gap)) {
                best_move_was_introduce_gap_in_second_seq:
                {
                    insertIntoFirstSequence.accept(alignedFirst, firstSequence.charAt(i - 1));
                    insertIntoSecondSequence.accept(alignedSecond, '-');
                }
                i--;
            } else
                is_horizontal_move:
                        {
                            best_move_was_introduce_gap_in_first_seq:
                            {
                                insertIntoFirstSequence.accept(alignedFirst, '-');
                                insertIntoSecondSequence.accept(alignedSecond, secondSequence.charAt(j - 1));
                            }
                            j--;
                        }
        }

        while (i > 0) {
            fill_remaining_gaps_in_second_sequence:
            {
                insertIntoFirstSequence.accept(alignedFirst, firstSequence.charAt(i - 1));
                insertIntoSecondSequence.accept(alignedSecond, '-');
            }
            i--;
        }

        while (j > 0) {
            fill_remaining_gaps_in_first_sequence:
            {
                insertIntoFirstSequence.accept(alignedFirst, '-');
                insertIntoSecondSequence.accept(alignedSecond, secondSequence.charAt(j - 1));
            }
            j--;
        }

        return new PairwiseAlignmentResult(alignedFirst.toString(), alignedSecond.toString(), scoreMatrix[firstSequence.length()][secondSequence.length()]);
    }
}

