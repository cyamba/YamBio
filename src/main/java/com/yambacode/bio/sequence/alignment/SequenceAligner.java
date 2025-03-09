package com.yambacode.bio.sequence.alignment;

public interface SequenceAligner<S, T extends AlignmentResult> {
    T align(S input);
}

