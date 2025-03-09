package com.yambacode.bio.sequence.alignment.multiple;

import com.yambacode.bio.sequence.alignment.SequenceAligner;

public interface MultipleSequenceAligner extends SequenceAligner<MultipleAlignmentInput, MultipleAlignmentResult> {
    MultipleAlignmentResult align(MultipleAlignmentInput input);
}
