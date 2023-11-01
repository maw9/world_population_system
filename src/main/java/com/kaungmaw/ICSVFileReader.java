package com.kaungmaw;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public interface ICSVFileReader {

    LinkedList<Population> read(String filePath) throws FileNotFoundException;

}
