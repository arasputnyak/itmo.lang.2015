import java.io.*;

/**
 * Created by Владелец on 24.02.2015.
 */
public class OddEvenFileSplitter implements FileSplitter {
     public void splitFile(SplitConfig config1) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(config1.getSourceFilePath()));
            BufferedWriter bufWriter1 = new BufferedWriter(new FileWriter(config1.getOddLinesFilePath()));
            BufferedWriter bufWriter2 = new BufferedWriter(new FileWriter(config1.getEvenLinesFilePath()));
            String line;
            int i;
            i = 0;
            line = bufReader.readLine();
            while (line != null) {
                i = i + 1;
                if (i % 2 != 0) {
                    bufWriter1.write(line);
                    bufWriter1.newLine();
                }
                if (i % 2 == 0) {
                    bufWriter2.write(line);
                    bufWriter2.newLine();
                }
                line = bufReader.readLine();
            }
            bufReader.close();
            bufWriter1.close();
            bufWriter2.close();
        } catch (IOException e) {
        e.printStackTrace();
    }

    };
    public static void main(final String[] args) {
        SplitConfig config = new SplitConfig() {
            public String getSourceFilePath() {
                return args[0];
            };
            public String getOddLinesFilePath() {
                return args[1];
            };
            public String getEvenLinesFilePath() {
                return args[2];
            };
        };
        OddEvenFileSplitter oddEvenFileSplitter = new OddEvenFileSplitter();
        oddEvenFileSplitter.splitFile(config);
    }
}
