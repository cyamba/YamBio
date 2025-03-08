# YamBio: Bioinformatics Alignment Library

## Overview
**YamBio** is a bioinformatics library focused on **sequence alignment** for DNA, RNA, and protein sequences. It provides efficient implementations of **pairwise** and **multiple sequence alignments**, enabling researchers to compare sequences and analyze genetic relationships.

## Features
- **Pairwise Sequence Alignment**
    - Global alignment (Needleman-Wunsch)
    - Local alignment (Smith-Waterman) *(upcoming)*
- **Multiple Sequence Alignment (MSA)** *(planned)*
- **Modular and Extendable** design with clear package structure
- **Optimized Initialization** for alignment matrices
- **Alignment Output Formatting**
    - `AlignmentPrinter` for visualizing pairwise and multiple sequence alignments

## Package Structure
```
bioinformatics.alignment
├── SequenceAligner.java             # Generic alignment interface
├── AlignmentResult.java             # Result representation
├── AlignmentPrinter.java            # Utility class for formatted output
│
├── pairwise                         # Pairwise sequence alignment
│   ├── PairwiseSequenceAligner.java # Interface for pairwise aligners
│   ├── PairwiseAlignmentInput.java  # Input data structure
│   ├── NeedlemanWunschAligner.java  # Global alignment implementation
│   ├── SmithWatermanAligner.java    # Local alignment implementation *(upcoming)*
│
├── multiple                         # Multiple sequence alignment (MSA)
    ├── MultipleSequenceAligner.java # Interface for MSA
    ├── MultipleAlignmentInput.java  # Input structure for MSA *(planned)*
```

## Usage
### Pairwise Alignment Example (Needleman-Wunsch)
```java
PairwiseSequenceAligner aligner = new NeedlemanWunschAligner(2, -1, -2);
PairwiseAlignmentInput input = new PairwiseAlignmentInput("ACGT", "AGT");
AlignmentResult result = aligner.align(input);
System.out.println(AlignmentPrinter.format(result));
```

## Roadmap
- ✅ Implement **Needleman-Wunsch (global alignment)**
- 🔜 Add **Smith-Waterman (local alignment)**
- 🔜 Implement **traceback** for retrieving aligned sequences
- 🔜 Develop **Multiple Sequence Alignment (MSA)**
- 🔜 Optimize for **parallelization & large-scale genomic data**
- 🔜 Enhance **AlignmentPrinter** to support MSA visualization

## Contributing
Contributions are welcome! Feel free to **submit issues, suggest features, or contribute code** to improve YamBio.

## License
Apache License 2.0 © 2025 YamBio

