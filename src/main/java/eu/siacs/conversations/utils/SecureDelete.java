package eu.siacs.conversations.utils;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class SecureDelete {
    private static final String TAG = "SecureDelete";

    public static String DeleteFile(File file) throws IOException {
        if (file.exists() && file.canRead() && file.canWrite()) {
            long length = file.length();
            SecureRandom random = new SecureRandom();
            byte[] buffer = new byte[4096];

            long start = System.currentTimeMillis();

            // LANGKAH 1: Write random bytes (1st pass)
            try (FileOutputStream fos = new FileOutputStream(file)) {
                long remainingBytes = length;
                while (remainingBytes > 0) {
                    random.nextBytes(buffer);
                    int toWrite = (int) Math.min(buffer.length, remainingBytes);
                    fos.write(buffer, 0, toWrite);
                    remainingBytes -= toWrite;
                }
                fos.flush();
            }
            Log.d(TAG, "Pass 1 (random write) selesai dalam " + (System.currentTimeMillis() - start) + " ms");

            // LANGKAH 2: Write random bytes (2nd pass)
            start = System.currentTimeMillis();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                long remainingBytes = length;
                while (remainingBytes > 0) {
                    random.nextBytes(buffer);
                    int toWrite = (int) Math.min(buffer.length, remainingBytes);
                    fos.write(buffer, 0, toWrite);
                    remainingBytes -= toWrite;
                }
                fos.flush();
            }
            Log.d(TAG, "Pass 2 (random write) selesai dalam " + (System.currentTimeMillis() - start) + " ms");

            // LANGKAH 3: Write zeros
            start = System.currentTimeMillis();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] zerosBuffer = new byte[4096];
                long remainingBytes = length;
                while (remainingBytes > 0) {
                    int toWrite = (int) Math.min(zerosBuffer.length, remainingBytes);
                    fos.write(zerosBuffer, 0, toWrite);
                    remainingBytes -= toWrite;
                }
                fos.flush();
            }
            Log.d(TAG, "Pass 3 (zero write) selesai dalam " + (System.currentTimeMillis() - start) + " ms");

            // Penghapusan file
            start = System.currentTimeMillis();
            boolean deleted = file.delete();
            Log.d(TAG, "File.delete() di eksekusi dalam " + (System.currentTimeMillis() - start) + " ms");

            return deleted ? "Deleted Success" : "Deleted Failed";
        } else {
            Log.d(TAG, "File tidak ditemukan atau tidak bisa diakses");
            return "No Exists";
        }
    }
}

