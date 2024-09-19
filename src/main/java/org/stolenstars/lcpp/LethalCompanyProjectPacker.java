package org.stolenstars.lcpp;

import org.stolenstars.lcpp.packer.ZipProjectPacker;
import java.io.*;
import java.util.*;

public class LethalCompanyProjectPacker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Specify folder from which you want to retrieve required files");
        String folderPath = scanner.nextLine();
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            System.out.println("This folder does not exists");
            return;
        }

        System.out.println("Specify bundles extension (.lethalbundle ?)");
        String extension = scanner.nextLine();

        System.out.println("Specify the name of the file in which the mod content is placed");
        String modContentFileName = scanner.nextLine();
        File modContentFile = new File(folder, modContentFileName + "." + extension);

        if (!modContentFile.exists()) {
            System.out.println("Mod content file not found");
            return;
        }

        System.out.println("Specify additional files to be added (blank line to complete the entry). Specify name only, exclude extensions");
        List<File> additionalFiles = new ArrayList<>();
        while (true) {
            String additionalFileName = scanner.nextLine();
            if (additionalFileName.isEmpty()) {
                break;
            }
            File additionalFile = new File(folder, additionalFileName + "." + extension);
            if (additionalFile.exists()) {
                additionalFiles.add(additionalFile);
            } else {
                System.out.println("File " + additionalFile + " not found and will not be added");
            }
        }

        System.out.println("Enter the name of the archive in which you want to package the specified content");
        String zipFileName = scanner.nextLine();

        System.out.println("Enter the path where in which you want to get the finished archive");
        String outputPath = scanner.nextLine();
        File outputFolder = new File(outputPath);
        if (!outputFolder.exists() || !outputFolder.isDirectory()) {
            System.out.println("The specified folder for the archive does not exist");
            return;
        }

        System.out.println("Should the old README.md be added? If so, please specify the path to the file");
        String readmePath = scanner.nextLine();
        File readmeFile = new File(readmePath);
        if (!readmePath.isEmpty() && !readmeFile.exists()) {
            System.out.println("File not found");
        }

        File zipFile = new File(outputFolder, zipFileName + ".zip");
        try {
            ZipProjectPacker zipPacker = new ZipProjectPacker();
            zipPacker.packFiles(zipFile, modContentFile, additionalFiles, readmeFile);
            System.out.println("Archive created: " + zipFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Exception while creating an archive: " + e.getMessage());
        }
    }
}

