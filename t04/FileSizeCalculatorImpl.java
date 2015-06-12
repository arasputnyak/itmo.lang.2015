import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Владелец on 12.06.2015.
 */
public class FileSizeCalculatorImpl implements FileSizeCalculator {
    private long size;
    public static void main(String[] args) {
        String path = args[0];
        String template = args[1];
        FileSizeCalculatorImpl fileSizeCalculator = new FileSizeCalculatorImpl();
        System.out.println(fileSizeCalculator.getSize(path, template));
    }
    public long getSize(final String pathToDir, final String fileTemplate) {
        try {
            size = 0;
            Path path = Paths.get(pathToDir);
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return null;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.getFileName().toString().equals(fileTemplate)) {
                        size = size + Files.size(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return null;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
