package com.yambacode.bio.sequence.alignment.pairwise;

import com.yambacode.bio.sequence.alignment.AlignmentResult;
import lombok.Getter;

@Getter
public class PairwiseAlignmentResult extends AlignmentResult {

    public PairwiseAlignmentResult(String alignedFirstSequence, String alignedSecondSequence, int score) {
        super(alignedFirstSequence, alignedSecondSequence, score);
    }
}

