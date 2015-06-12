/**
 * Created by Владелец on 12.06.2015.
 */
public interface FileSizeCalculator {
    long getSize(final String pathToDir, final String fileTemplate);
}