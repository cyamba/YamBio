package com.yambacode.bio.sequence;

public final class DNASequence extends BioSequence {

    public DNASequence(String sequence) {
        super(sequence);
    }

    @Override
    protected void validateSequence(String sequence) {
        if (!sequence.matches("[ACGTacgt]+"))
            throw new IllegalArgumentException("Invalid DNA sequence");
    }

    @Override
    public RNASequence transcribe() {
        return new RNASequence(sequence.replace('T', 'U'));
    }

    public DNASequence reverseComplement() {
        StringBuilder complement = new StringBuilder();
        for (char base : sequence.toCharArray()) {
            switch (base) {
                case 'A' -> complement.append('T');
                case 'T' -> complement.append('A');
                case 'C' -> complement.append('G');
                case 'G' -> complement.append('C');
                default -> throw new IllegalArgumentException("Invalid nucleotide: " + base);
            }
        }
        return new DNASequence(complement.reverse().toString());
    }
}
