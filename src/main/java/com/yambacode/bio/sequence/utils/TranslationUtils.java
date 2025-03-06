package com.yambacode.bio.sequence.utils;

import com.yambacode.bio.aminoacid.AminoAcid;
import com.yambacode.bio.sequence.CodonTable;

public class TranslationUtils {

    public static String translate(String sequence) {
        StringBuilder protein = new StringBuilder(sequence.length() / 3);
        for (int i = 0; i < sequence.length() - 2; i += 3) {
            String codon = sequence.substring(i, i + 3);
            AminoAcid aminoAcid = CodonTable.translate(codon);
            if (aminoAcid == AminoAcid.STOP) break;
            protein.append(aminoAcid.name());
        }
        return protein.toString();
    }

}
