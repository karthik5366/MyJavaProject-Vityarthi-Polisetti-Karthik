package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileUtils {
    // This is the recursive method
    public static long calculateDirectorySize(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            // Base Case: If it's a file, return its size.
            return Files.size(path);
        }

        long size = 0;
        // Recursive Step: If it's a directory, sum the sizes of its contents.
        try (Stream<Path> stream = Files.list(path)) {
            for (Path entry : stream.collect(java.util.stream.Collectors.toList())) {
                size += calculateDirectorySize(entry);
            }
        }
        return size;
    }
}