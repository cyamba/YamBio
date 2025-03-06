package com.yambacode.bio.sequence;

import com.yambacode.bio.nucleicacid.NucleicAcidUtils;

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
        return new DNASequence(NucleicAcidUtils.reverseComplement(this));
    }
}
