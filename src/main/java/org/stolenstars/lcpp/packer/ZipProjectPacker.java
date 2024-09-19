package org.stolenstars.lcpp.packer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipProjectPacker {

    public void packFiles(File zipFile, File modContentFile, List<File> additionalFiles, File readmeFile) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            addFileToZip(zipOut, modContentFile);
            for (File file : additionalFiles) {
                addFileToZip(zipOut, file);
            }
            if (readmeFile != null && readmeFile.exists()) {
                addFileToZip(zipOut, readmeFile);
            }
        }
    }

    public void addFileToZip(ZipOutputStream zipOut, File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            zipOut.closeEntry();
        }
    }
}
