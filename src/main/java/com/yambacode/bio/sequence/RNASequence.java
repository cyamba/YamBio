package com.yambacode.bio.sequence;

import static com.yambacode.bio.sequence.utils.Transcriptions.translateUsingForLoop;

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
        return translateUsingForLoop(this.sequence);
    }
}
