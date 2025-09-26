package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    public void backupData(String sourceFilePath, String backupRootDirectory) {
        // Create a timestamped folder name e.g., "backup_2025-10-26_14-30-00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        Path backupDir = Paths.get(backupRootDirectory, "backup_" + timestamp);

        try {
            Files.createDirectories(backupDir);
            Path sourcePath = Paths.get(sourceFilePath);
            Path destinationPath = backupDir.resolve(sourcePath.getFileName());

            // Copy the file
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup successful! Data saved to: " + destinationPath);
        } catch (IOException e) {
            System.err.println("Backup failed: " + e.getMessage());
        }
    }
}