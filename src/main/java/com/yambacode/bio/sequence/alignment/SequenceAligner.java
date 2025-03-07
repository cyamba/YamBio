package com.yambacode.bio.sequence.alignment;

public interface SequenceAligner<T> {
    AlignmentResult align(T input);
}

