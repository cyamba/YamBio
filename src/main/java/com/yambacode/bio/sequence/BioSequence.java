package com.yambacode.bio.sequence;

import com.yambacode.bio.nucleicacid.NucleicAcidUtils;
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

    public BioSequence reverseComplement() {
        return switch (this) {
            case DNASequence dna -> new DNASequence(NucleicAcidUtils.reverseComplement(dna));
            case RNASequence rna -> new RNASequence(NucleicAcidUtils.reverseComplement(rna));
            default ->
                    throw new UnsupportedOperationException("Reverse complement not supported for this sequence type");
        };
    }

}
