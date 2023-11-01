package com.kaungmaw;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class OpenCSVFileReader implements ICSVFileReader {

    @Override
    public LinkedList<Population> read(String filePath) throws FileNotFoundException {
        LinkedList<Population> latestPopulation = new LinkedList<>();

        try {
            FileReader fr = new FileReader(filePath);
            CSVReader csvFileReader = new CSVReader(fr);
            String[] record;
            csvFileReader.readNext();
            while ((record = csvFileReader.readNext()) != null) {
                latestPopulation.add(new Population(record[0], Integer.parseInt(record[1])));
            }
            csvFileReader.close();
            fr.close();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("Invalid file path");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return latestPopulation;
    }

}
