package com.intuit.ctg.fuego.hiptest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bryan on 8/31/16.
 */
public class Subfolder {
    private String name;
    private ArrayList<File> files;
    private ArrayList<Test> tests;

    /*
     * "Subfolder" are separators in excel which contain folder information to mimic the structure found in
     *  Fuego Player Automation.
     */
    public Subfolder(String name) {
        this.name = name;
        this.files = new ArrayList<File>();
        this.tests = new ArrayList<Test>();
    }

    /*
     * Add manual and functional tests to the subfolder.
     */
    public void addTest(File file) throws IOException {
        Test manualTest = new Test(file, HiptestConstants.MANUAL_TEST);
        Test functionalTest = new Test(file, HiptestConstants.FUNCTIONAL_TEST);

        manualTest.loopThroughFile();
        functionalTest.loopThroughFile();

        this.tests.add(manualTest);
        this.tests.add(functionalTest);
    }

    /*
     * Getters and setters.
     */
    public ArrayList<Test> getTests() {
        return this.tests;
    }

    public String getName() {
        return this.name;
    }

//    public File[] getFiles() {
//        return this.files;
//    }

    public void addFile(File file) {
        this.files.add(file);
    }

}


