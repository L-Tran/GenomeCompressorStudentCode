/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 *  @author Logan Tran
 */
public class GenomeCompressor {

    public static final int LEN = 1;
    public static final int BITS_PER_CHAR = 2;
    public static final char ESC_A = 0x00;
    public static final char ESC_G = 0x01;
    public static final char ESC_C = 0x02;
    public static final char ESC_T = 0x03;

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {

        // Read in the string and find the first instance of TARGET
        String s = BinaryStdIn.readString();
        int n = s.length();
        BinaryStdOut.write(n);
        // Write out each character
        for (int i = 0; i < n; i++) {
            if (i + LEN <= n && s.charAt(i) == 'A') {
                BinaryStdOut.write(ESC_A, BITS_PER_CHAR);
                i += LEN - 1;
            }
            else if (i + LEN <= n && s.charAt(i) == 'G') {
                BinaryStdOut.write(ESC_G, BITS_PER_CHAR);
                i += LEN - 1;
            }
            else if (i + LEN <= n && s.charAt(i) == 'C') {
                BinaryStdOut.write(ESC_C, BITS_PER_CHAR);
                i += LEN - 1;
            }
            else if (i + LEN <= n && s.charAt(i) == 'T') {
                BinaryStdOut.write(ESC_T, BITS_PER_CHAR);
                i += LEN - 1;
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int n = BinaryStdIn.readInt();
        for (int i = 0; i < n; i++) {
            char c = BinaryStdIn.readChar(BITS_PER_CHAR);
            if (c == ESC_A) {
                BinaryStdOut.write('A');
            }
            else if (c == ESC_G) {
                BinaryStdOut.write('G');
            }
            else if (c == ESC_C) {
                BinaryStdOut.write('C');
            }
            else if (c == ESC_T) {
                BinaryStdOut.write('T');
            }
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}