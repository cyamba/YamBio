package com.yambacode.bio.sequence;
import lombok.Getter;

@Getter
public sealed abstract class BioSequence permits DNASequence, RNASequence, ProteinSequence {
    protected final String sequence;

    public BioSequence(String sequence) {
        validateSequence(sequence);
        this.sequence = sequence;
    }

    protected abstract void validateSequence(String sequence);

    public abstract BioSequence transcribe();
}
