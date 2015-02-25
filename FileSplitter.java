/**
 * Created by Владелец on 24.02.2015.
 */
public interface FileSplitter {
    void splitFile(SplitConfig config);
    interface SplitConfig {
        String getSourceFilePath();
        String getOddLinesFilePath();
        String getEvenLinesFilePath();

    }
}
