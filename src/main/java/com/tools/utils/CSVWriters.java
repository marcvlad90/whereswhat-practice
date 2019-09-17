package com.tools.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tools.constants.Constants;
import com.tools.entities.Item;

public class CSVWriters {
    public static <T> void writeInCsv(List<T> t, String file) throws Exception {
        waitForFileToBeReadyForUpdates(file, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        CsvSchema schema = CsvSchema.builder()
                .addColumn("Name")
                .addColumn("Item tag")
                .addColumn("Description")
                .addColumn("Category")
                .build().withHeader();
        ObjectWriter myObjectWriter = mapper.writer(schema);
        File tempFile = new File(file);
        FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
        OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
        myObjectWriter.writeValue(writerOutputStream, t);
    }

    public static void waitForFileToBeReadyForUpdates(String filePath, int noOfSecondsToWait) throws InterruptedException {
        Path path = FileSystems.getDefault().getPath(filePath);
        for (int i = 0; i < noOfSecondsToWait; i++) {
            if (Files.isWritable(path)) {
                break;
            }
            Thread.sleep(1000);
        }
    }

    public static void appendInCsv(List<Item> items, String file, boolean areMultipleCategories) throws Exception {
        waitForFileToBeReadyForUpdates(file, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        FileWriter fstream = new FileWriter(file, true);
        BufferedWriter fbw = new BufferedWriter(fstream);
        for (Item item : items) {
            fbw.write(String.valueOf(item.getTitle()));
            fbw.write(",");
            fbw.write(String.valueOf(item.getItemTag()));
            fbw.write(",");
            fbw.write("Descriptions of " + item.getTitle());
            if (areMultipleCategories) {
                fbw.write(",");
                fbw.write(String.valueOf(item.getCategoryTitle()));
            }
            fbw.newLine();
        }
        fbw.close();
    }
}
