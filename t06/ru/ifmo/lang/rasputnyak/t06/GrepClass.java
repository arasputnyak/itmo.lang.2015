package ru.ifmo.lang.rasputnyak.t06;

import ru.ifmo.lang.rasputnyak.t06.Grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владелец on 22.03.2015.
 */
public class GrepClass implements Grep {
    BufferedReader bufferedReader;
    List<String> list;
    public GrepClass(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public static void main(String[] args) {
        try {
            GrepClass grepClass = new GrepClass(new BufferedInputStream(new FileInputStream(args[2])));
            if (args[0].equals("-o")) {
                System.out.println(grepClass.findParts(args[1]));
            }
            if (args[0].equals("-v")) {
                System.out.println(grepClass.findInvertMatch(args[0]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public List<String> findLines(String regex) {
        try {
            list = new ArrayList<String>();
            String line;
            line = " ";
            while (line != null) {
                line = bufferedReader.readLine();
                if (line.contains(regex)) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<String> findParts(String regex){
        try {
            list = new ArrayList<String>();
            String line;
            line = " ";
            while (line != null) {
                line = bufferedReader.readLine();
                if (line.contains(regex)) {
                    list.add(regex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<String> findInvertMatch(String regex) {
        try {
            list = new ArrayList<String>();
            String line;
            List<String> list = new ArrayList<String>();
            line = " ";
            while (line != null) {
                line = bufferedReader.readLine();
                if (!(line.contains(regex))) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
