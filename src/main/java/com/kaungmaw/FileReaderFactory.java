package com.kaungmaw;

public class FileReaderFactory {

    public ICSVFileReader getFileReader(FileReaderType type) {
        if (type == null) {
            return null;
        }

        if (type == FileReaderType.OpenCSV)
            return new OpenCSVFileReader();
        else if (type == FileReaderType.Buffer)
            return new CSVFileBufferReader();

        return null;
    }

}
