package com.yambacode.bio.sequence;

final class ProteinSequence extends BioSequence {

    public ProteinSequence(String sequence) {
        super(sequence);
    }

    @Override
    protected void validateSequence(String sequence) {
        if (sequence.isEmpty() || sequence.matches("[ACDEFGHIKLMNPQRSTVWYacdefghiklmnpqrstvwy]+")) {
            return;
        }
        throw new IllegalArgumentException("Invalid protein sequence: " + sequence);
    }

    @Override
    public BioSequence transcribe() {
        throw new UnsupportedOperationException("Proteins cannot be transcribed");
    }
}
