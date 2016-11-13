package com.intuit.ctg.fuego.hiptest;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by bryan on 9/1/16.
 */
public class Excel {
    // all the apache poi stuff
    // rows
    // columns
    // etc

    private Workbook workbook;
    private Worksheet worksheet;
    private ArrayList<Subfolder> subfolders;
    private int row;
    private CreationHelper createHelper;
    public static final int TEST_ID_COLUMN = 0;
    public static final int TEST_NAME_COLUMN = 1;
    public static final int TEST_DESCRIPTION_COLUMN = 2;
    public static final int TEST_TAGS_COLUMN = 3;
    public static final int PRECONDITIONS_COLUMN = 4;
    public static final int STEPS_COLUMN = 5;
    public static final int RESULTS_COLUMN = 6;

    public Excel(Workbook workbook) {
        this.workbook = workbook;
        this.worksheet = workbook.getWorksheet();
        this.subfolders = new ArrayList<Subfolder>();
    }

    public void write() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        File[] files;
        FileWriter fileWriter;
        Sheet sheet;
        CreationHelper createHelper;
        int fileCount;
        int rowInc;

        // makes the file
        FileOutputStream fileOut = new FileOutputStream("Player_Automation_Hiptest_Import.xlsx");

        // makes the sheet
        this.createHelper = wb.getCreationHelper();
        sheet = wb.createSheet(worksheet.getName());

        // Add column headers that Hiptest requires
        addCells(sheet.createRow(row++), new Test());

        this.subfolders = worksheet.getSubfolders();
        System.out.println(worksheet.getSubfolders());
        System.out.println("this.subfolders=" + this.subfolders);
        for (Subfolder subfolder: this.subfolders) {
            addSeparator(sheet, subfolder.getName());
            for (Test test: subfolder.getTests()) {
                System.out.println("test = " + test);
                addCells(sheet.createRow(row++), test);
            }
        }

        // add subfolder
        // then add its tests

        //
        wb.write(fileOut);
        fileOut.close();

    }

    /*
     * Add separator in excel file that allows subfolders to be made
     */
    private void addSeparator(Sheet sheet, String path) {
        Row separator = sheet.createRow(row);
        separator.createCell(TEST_ID_COLUMN).setCellValue(createHelper.createRichTextString(path));
        sheet.addMergedRegion(new CellRangeAddress(row, row, TEST_ID_COLUMN, RESULTS_COLUMN));
        ++row;
    }

    private void addCells(Row row, Test test) {
        row.createCell(TEST_ID_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getTestID()));
        row.createCell(TEST_NAME_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getTestName()));
        row.createCell(TEST_DESCRIPTION_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getDescription()));
        row.createCell(TEST_TAGS_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getTags()));
        row.createCell(PRECONDITIONS_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getPreconditions()));
        row.createCell(STEPS_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getSteps()));
        row.createCell(RESULTS_COLUMN).setCellValue(this.createHelper.createRichTextString(test.getResults()));
    }
}

