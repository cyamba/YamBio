package com.yambacode.bio.sequence.alignment.multiple;

import com.yambacode.bio.sequence.alignment.AlignmentResult;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleAlignmentResult extends AlignmentResult {
    List<String> alignedSequences;

    public MultipleAlignmentResult(String alignedFirstSequence, String alignedSecondSequence, int score) {
        super(alignedFirstSequence, alignedSecondSequence, score);
    }
}

