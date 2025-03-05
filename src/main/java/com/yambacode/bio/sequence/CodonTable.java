package com.yambacode.bio.sequence;

import java.util.Map;

public class CodonTable {

    private static final Map<String, AminoAcid> CODON_MAP = Map.<String, AminoAcid>ofEntries(
            Map.entry("UUU", AminoAcid.F), Map.entry("UUC", AminoAcid.F),
            Map.entry("UUA", AminoAcid.L), Map.entry("UUG", AminoAcid.L),
            Map.entry("UCU", AminoAcid.S), Map.entry("UCC", AminoAcid.S), Map.entry("UCA", AminoAcid.S), Map.entry("UCG", AminoAcid.S),
            Map.entry("UAU", AminoAcid.Y), Map.entry("UAC", AminoAcid.Y),
            Map.entry("UGU", AminoAcid.C), Map.entry("UGC", AminoAcid.C),
            Map.entry("UGG", AminoAcid.W),
            Map.entry("CUU", AminoAcid.L), Map.entry("CUC", AminoAcid.L), Map.entry("CUA", AminoAcid.L), Map.entry("CUG", AminoAcid.L),
            Map.entry("CCU", AminoAcid.P), Map.entry("CCC", AminoAcid.P), Map.entry("CCA", AminoAcid.P), Map.entry("CCG", AminoAcid.P),
            Map.entry("CAU", AminoAcid.H), Map.entry("CAC", AminoAcid.H),
            Map.entry("CAA", AminoAcid.Q), Map.entry("CAG", AminoAcid.Q),
            Map.entry("CGU", AminoAcid.R), Map.entry("CGC", AminoAcid.R), Map.entry("CGA", AminoAcid.R), Map.entry("CGG", AminoAcid.R),
            Map.entry("AUU", AminoAcid.I), Map.entry("AUC", AminoAcid.I), Map.entry("AUA", AminoAcid.I),
            Map.entry("AUG", AminoAcid.M), // Start codon
            Map.entry("ACU", AminoAcid.T), Map.entry("ACC", AminoAcid.T), Map.entry("ACA", AminoAcid.T), Map.entry("ACG", AminoAcid.T),
            Map.entry("AAU", AminoAcid.N), Map.entry("AAC", AminoAcid.N),
            Map.entry("AAA", AminoAcid.K), Map.entry("AAG", AminoAcid.K),
            Map.entry("AGU", AminoAcid.S), Map.entry("AGC", AminoAcid.S),
            Map.entry("AGA", AminoAcid.R), Map.entry("AGG", AminoAcid.R),
            Map.entry("GUU", AminoAcid.V), Map.entry("GUC", AminoAcid.V), Map.entry("GUA", AminoAcid.V), Map.entry("GUG", AminoAcid.V),
            Map.entry("GCU", AminoAcid.A), Map.entry("GCC", AminoAcid.A), Map.entry("GCA", AminoAcid.A), Map.entry("GCG", AminoAcid.A),
            Map.entry("GAU", AminoAcid.D), Map.entry("GAC", AminoAcid.D),
            Map.entry("GAA", AminoAcid.E), Map.entry("GAG", AminoAcid.E),
            Map.entry("GGU", AminoAcid.G), Map.entry("GGC", AminoAcid.G), Map.entry("GGA", AminoAcid.G), Map.entry("GGG", AminoAcid.G),
            Map.entry("UGA", AminoAcid.STOP), Map.entry("UAA", AminoAcid.STOP), Map.entry("UAG", AminoAcid.STOP) // Stop codons
    );

    public static AminoAcid translate(String codon) {
        if (codon.length() != 3) {
            throw new IllegalArgumentException("Malformed codon (must be 3 bases): " + codon);
        }
        if (!CODON_MAP.containsKey(codon)) {
            throw new IllegalArgumentException("Unknown codon (not in genetic code): " + codon);
        }
        return CODON_MAP.get(codon);
    }
}
