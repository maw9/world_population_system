package com.kaungmaw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class CSVFileBufferReader implements ICSVFileReader {

    @Override
    public LinkedList<Population> read(String filePath) throws FileNotFoundException {
        LinkedList<Population> latestPopulation = new LinkedList<>();

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader bfReader = new java.io.BufferedReader(fr);
            bfReader.readLine();
            String line;
            while ((line = bfReader.readLine()) != null) {
                String[] record = line.split(",");
                latestPopulation.add(new Population(record[0], Integer.parseInt(record[1])));
            }
            bfReader.close();
            fr.close();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("Invalid file path");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return latestPopulation;
    }

}
