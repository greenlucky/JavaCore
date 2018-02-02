package com.lamdevops.file;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CompareFileWithMemoryMap {

    @Test
    public void compareFileWithBuffer() throws IOException {
        long startTime = System.currentTimeMillis();

        BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream("C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\file\\alice.txt"));
        BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream("C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\file\\alice-copy.txt"));
        int b1 = 0, b2 = 0, pos = 1;
        while (b1 != -1 && b2 != -1) {
            if(b1 != b2) {
                System.out.println("Files differ at position " + pos);
            }
            pos++;
            b1 = fis1.read();
            b2 = fis2.read();
        }

        if(b1 != b2) {
            System.out.println("Files have different length");
        } else {
            System.out.println("Files are identical, you can delete one of them");
        }
        fis1.close();
        fis2.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Executive time: " + (endTime - startTime) + " ms");
    }

    /**
     * This code just executive file less than 2Gb
     * @throws IOException
     */
    @Test
    public void compareFileWithMemoryMapped() throws IOException {
        long startTime = System.currentTimeMillis();
        String fis1 = "C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\file\\alice.txt";
        String fis2 = "C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\file\\alice-copy.txt";
        FileChannel ch1 = new RandomAccessFile(fis1, "r").getChannel();
        FileChannel ch2 = new RandomAccessFile(fis2, "r").getChannel();

        if(ch1.size() != ch2.size()) {
            System.out.println("Files have different length");
            return;
        }
        long size = ch1.size();
        ByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
        ByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);
        for(int pos = 0; pos < size; pos++) {
            if(m1.get(pos) != m2.get(pos)) {
                System.out.println("Files differ at position " + pos);
                return;
            }
        }
        System.out.println("Files are identical, you can delete one of them");
        long endTime = System.currentTimeMillis();
        System.out.println("Executive time: " + (endTime - startTime) + " ms");

    }

}
