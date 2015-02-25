import java.io.*;

/**
 * Created by Владелец on 24.02.2015.
 */
public class OddEvenFileSplitter implements FileSplitter {
     public void splitFile(SplitConfig config1) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(config1.getSourceFilePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bufWriter1 = new BufferedWriter(new FileWriter(config1.getOddLinesFilePath()));
            BufferedWriter bufWriter2 = new BufferedWriter(new FileWriter(config1.getEvenLinesFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        int i;
        i = 0;
        do {
            i = i + 1;
            line = bufReader.readLine();
            if (i % 2 != 0) {
                bufWriter1.write(line);
            }
            if (i % 2 == 0) {
                bufWriter2.write(line);
            }
        } while (line != null);
         bufReader.close();
         bufWriter1.close();
         bufWriter2.close();
    };
    public static void main(final String[] args) {
        OddEvenFileSplitter oddEvenFileSplitter = new OddEvenFileSplitter();
        oddEvenFileSplitter.splitFile(config);
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
    }
}
