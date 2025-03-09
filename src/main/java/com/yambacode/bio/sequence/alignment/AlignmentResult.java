package com.yambacode.bio.sequence.alignment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlignmentResult {
    String alignedFirstSequence;
    String alignedSecondSequence;
    int score;
}


