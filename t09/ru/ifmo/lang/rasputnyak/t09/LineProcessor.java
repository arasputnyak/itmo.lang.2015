package ru.ifmo.lang.rasputnyak.t09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Владелец on 12.06.2015.
 */
public class LineProcessor {
    String pathToDir;
    String pathToDir2;
    List<String> list;
    public LineProcessor(String pathToDir, String pathToDir2) throws IOException {
        this.pathToDir = pathToDir;
        this.pathToDir2 = pathToDir2;
        this.list = Files.readAllLines(Paths.get(pathToDir));
    }
    public static void main(String[] args) {
        try {
            LineProcessor lineProcessor = new LineProcessor(args[0], args[1]);
            for (int i = 2; i < args.length; i++) {
                if (args[i].equals("sort")) {
                    lineProcessor.sort();
                }
                if (args[i].equals("skip")) {
                    lineProcessor.skip(Integer.parseInt(args[i + 1]));
                }
                if (args[i].equals("limit")) {
                    lineProcessor.limit(Integer.parseInt(args[i + 1]));
                }
                if (args[i].equals("shuffle")) {
                    lineProcessor.shuffle();
                }
                if (args[i].equals("distinct")) {
                    lineProcessor.distinct();
                }
                if (args[i].equals("filter")) {
                    lineProcessor.filter(args[i++]);
                }
            }
            lineProcessor.writeLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        list.stream().sorted().collect(Collectors.toList());
    }
    public void skip(int n) {
        list.stream().skip(n).collect(Collectors.toList());
    }
    public void limit(int n) {
        list.stream().limit(n).collect(Collectors.toList());
    }
    public void shuffle() {
        Collections.shuffle(list);
    }
    public void distinct() {
        list.stream().distinct().collect(Collectors.toList());
    }
    public void filter(String regex) {
        list.stream().filter(list -> list.contains(regex)).collect(Collectors.toList());
    }
    public void writeLines() throws IOException {
        Files.write(Paths.get(pathToDir2), list);
    }
}
