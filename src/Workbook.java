package com.intuit.ctg.fuego.hiptest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bryan on 8/31/16.
 */
public class Workbook {

    private File[] files;
    private String name;
    private Worksheet worksheet;

    /*
     * Creates an excel workbook
     */
    public Workbook(String name, File[] files) throws FileNotFoundException {
        this.name = name;
        this.files = files;
    }

    /*
     * Handles logic to extract information from files to create an excel workbook
     */
    public void create() throws IOException {
        this.worksheet = new Worksheet();
        this.worksheet.setName(this.files[0].getParentFile().getName());
        this.worksheet.showFiles(this.files);
    }

    public Worksheet getWorksheet() {
        return this.worksheet;
    }
}
