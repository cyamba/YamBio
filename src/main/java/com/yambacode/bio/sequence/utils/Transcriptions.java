package com.yambacode.bio.sequence.utils;

import com.yambacode.bio.sequence.AminoAcid;
import com.yambacode.bio.sequence.CodonTable;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Transcriptions {

    public static String translateUsingForLoop(String sequence) {
        StringBuilder protein = new StringBuilder(sequence.length() / 3);
        for (int i = 0; i < sequence.length() - 2; i += 3) {
            String codon = sequence.substring(i, i + 3);
            AminoAcid aminoAcid = CodonTable.translate(codon);
            if (aminoAcid == AminoAcid.STOP) break;
            protein.append(aminoAcid.name());
        }
        return protein.toString();
    }

    public static String translateUsingStream(String sequence) {
        return IntStream.range(0, sequence.length() / 3)
                .mapToObj(i -> sequence.substring(i * 3, Math.min((i + 1) * 3, sequence.length())))
                .map(CodonTable::translate).takeWhile(a -> a != AminoAcid.STOP)
                .map(Enum::name)
                .collect(Collectors.joining());
    }
}
