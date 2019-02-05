package com.tools.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tools.entities.Item;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVWriters {
    public static <T> void writeInCsv(List<T> t, String file) throws Exception {
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

    public static void appendInCsv(List<Item> items, String file, boolean areMultipleCategories) throws Exception {
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
