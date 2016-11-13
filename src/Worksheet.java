package com.intuit.ctg.fuego.hiptest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bryan on 8/31/16.
 */
public class Worksheet {

    // Tab name of the worksheet
    private String name;

    // Separators in the excel sheet for each subfolder breakdown
    private ArrayList<Subfolder> subfolders;
    private File[] files;

    /*
     * Creates a new worksheet which holds subfolders
     */
    public Worksheet() {
        this.subfolders = new ArrayList<Subfolder>();
    }

    /*
     * Loops through array of files and breaks them up according to folder hierarchy in player automation
     */
    public void showFiles(File[] files) throws IOException {
        for (File file : files) {
            if (file.isDirectory()) {
                showFiles(file.listFiles());
            } else {
                addSubfolder(file);
            }
        }
    }

    /*
     * Prevents adding of duplicate subfolders
     */
    public boolean addSubfolder(File file) throws IOException {
        Boolean add = true;
        String folder = removeCharacters(file);
        ArrayList<Subfolder> subfolders = getSubfolders();

        for (Subfolder subfolder: subfolders) {
            if (folder.equals(subfolder)) {
                return false;
            }
        }

        Subfolder subfolder = new Subfolder(folder);
        subfolder.addTest(file);
        this.subfolders.add(subfolder);
        return true;
    }

    /*
     * Remove the parent directory, file name, and unnecessary . and /'s from subfolder name
     */
    public String removeCharacters(File file){
        String path = file.getPath() + "/";
        path = path.replace(file.getName() + "/", "");
        if (path.contains("./" + this.name + "/")) {
            path = path.replace("./" + this.name + "/", "");
        }
        return path;
    }

    public ArrayList<Subfolder> getSubfolders(){
        return this.subfolders;
    }

    public void setName(String name) {
        if (name.contains(".")) {
            name = name.replace(".", "");
        }
        while (name.contains("/")) {
            name = name.replace("/", "");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

