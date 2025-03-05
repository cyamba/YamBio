package com.yambacode.bio.sequence;

import lombok.Getter;

@Getter
enum AminoAcid {

    F("Phenylalanine", "Phe"),
    L("Leucine", "Leu"),
    S("Serine", "Ser"),
    Y("Tyrosine", "Tyr"),
    C("Cysteine", "Cys"),
    W("Tryptophan", "Trp"),
    P("Proline", "Pro"),
    H("Histidine", "His"),
    Q("Glutamine", "Gln"),
    R("Arginine", "Arg"),
    I("Isoleucine", "Ile"),
    M("Methionine", "Met"),
    T("Threonine", "Thr"),
    N("Asparagine", "Asn"),
    K("Lysine", "Lys"),  // ← **Lysine is explicitly named**
    V("Valine", "Val"),
    A("Alanine", "Ala"),
    D("Aspartic Acid", "Asp"),
    E("Glutamic Acid", "Glu"),
    G("Glycine", "Gly"),
    STOP("Stop Codon", "Stop");

    private final String fullName;
    private final String threeLetterCode;

    AminoAcid(String fullName, String threeLetterCode) {
        this.fullName = fullName;
        this.threeLetterCode = threeLetterCode;
    }

}
