package com.yambacode.bio.sequence;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class RNASequence extends BioSequence {

    public RNASequence(String sequence) {
        super(sequence);
    }

    public static RNASequence of(String sequence){
        return new RNASequence(sequence);
    }

    @Override
    protected void validateSequence(String sequence) {
        if (!sequence.matches("[ACGUacgu]+"))
            throw new IllegalArgumentException("Invalid RNA sequence");
    }

    @Override
    public ProteinSequence transcribe() {
        return new ProteinSequence(translateToProtein());
    }

    private String translateToProtein() {
        System.out.println();
        return IntStream.range(0, sequence.length() / 3)
                .mapToObj(i -> sequence.substring(i * 3, Math.min((i + 1) * 3, sequence.length())))
                .map(CodonTable::translate)
                .takeWhile(aminoAcid -> aminoAcid != AminoAcid.STOP)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
