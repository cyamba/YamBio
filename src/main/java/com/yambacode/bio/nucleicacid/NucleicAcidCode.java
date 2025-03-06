package com.yambacode.bio.nucleicacid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Following IUPAC Conventions.
 * https://www.bioinformatics.org/sms/iupac.html
 *
 */
@Getter
@ToString
@AllArgsConstructor
public enum NucleicAcidCode {
    A("A", "Adenine", "Purine"),
    C("C", "Cytosine", "Pyrimidine"),
    G("G", "Guanine", "Purine"),
    T("T", "Thymine", "Pyrimidine"),
    U("U", "Uracil", "RNA Pyrimidine"),
    R("R", "Purine (A or G)", "PuRine"),
    Y("Y", "Pyrimidine (C or T/U)", "pYrimidine"),
    S("S", "Strong (G or C)", "Strong Hydrogen Bonds"),
    W("W", "Weak (A or T/U)", "Weak Hydrogen Bonds"),
    K("K", "Keto (G or T/U)", "Keto Group"),
    M("M", "Amino (A or C)", "aMino Group"),
    B("B", "C, G, or T/U (not A)", "B follows A"),
    D("D", "A, G, or T/U (not C)", "D follows C"),
    H("H", "A, C, or T/U (not G)", "H follows G"),
    V("V", "A, C, or G (not T/U)", "V follows U/T"),
    N("N", "Any base (A, C, G, or T/U)", "Unknown/Nucleotide");

    private final String code;
    private final String meaning;
    private final String mnemonic;

}
