package com.intuit.ctg.fuego.hiptest;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 *
 */
public class Test {

    private static File file;
    private String testID;
    private String fileName ;
    private String testName;
    private String description;
    private String steps = "";
    private String tags;
    private String preconditions;
    private String results;
    private String testType;
    public static final String MANUAL_TEST = "MANUAL-TEST";
    public static final String FUNCTIONAL_TEST = "FUNCTIONAL-TEST";
    public static final String DESCRIPTION = "Description";

    /*
     * Header
     */
    public Test() {
        this.testID = "Test ID";
        this.testName = "Test name";
        this.description = "Test description";
        this.tags = "Test tags";
        this.preconditions = "Pre-conditions";
        this.steps = "Steps";
        this.results = "Result";
    }

    /*
     * Creates a test object
     */
    public Test(File file, String testType) {
        this.file = file;
        this.fileName = cleanUpName(file.getName());
        this.testType = testType;
        this.tags = tag(testType);
        this.testName = cleanUpName(file.getName()) + " " + this.tags;
        this.preconditions = "";
        this.results = "";
    }

    /*
     * Parses through the file to find the test description, if it exists
     */
    public void loopThroughFile() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            retrieveDescription(line);
            retrieveSteps(line);
        }
    }

    /*
     * Parses through the file to find the test description, if it exists
     * Sets flag to false once description has been found to prevent
     */
    public void retrieveDescription(String line) throws IOException {
        if (StringUtils.containsIgnoreCase(line, DESCRIPTION)) {
            this.description = removeDescriptionTag(line);
        }
    }

    /*
     * Parses through the file to find test steps
     */
    public void retrieveSteps(String line) throws IOException {
        if (StringUtils.containsIgnoreCase(line, this.testType)) {
            this.steps = removeManualTag(line);
        }
    }

    /*
     * Removes MANUAL-TEST or FUNCTIONAL-TEST
     * Concatenates step onto previous steps
     */
    public String removeManualTag(String step) {
        if (StringUtils.containsIgnoreCase(step, this.testType)) {
            step = step.replace(this.testType, "");
        }
        step = removeComments(step);
        return this.steps.concat("\n" + step);
    }

    /*
     * Removes DESCRIPTION
     */
    public String removeDescriptionTag(String description) {
        if (StringUtils.containsIgnoreCase(description, DESCRIPTION)) {
            description = description.replace(DESCRIPTION, "");
        }
        description = removeComments(description);
        return description;
    }

    /*
     * Removes comment characters
     */
    public String removeComments(String line) {
        if (line.contains("-")) {
            line = line.replace("-", "");
        }
        if (line.contains(":")) {
            line = line.replace(":", "");
        }
        if (line.contains(".")) {
            line = line.replace(".", "");
        }
        while (line.contains("/")) {
            line = line.replace("/", "");
        }
        while (line.contains("*")) {
            line = line.replace("*", "");
        }
        return line;
    }

    /*
     * Removes .java from end of test name
     */
    public String cleanUpName(String name) {
        if (file.getName().contains(".java")) {
            name = file.getName().replace(".java", "");
        }
        return name;
    }

    public String tag(String tag) {
        if (this.testType.equals(MANUAL_TEST)) {
            return "Manual";
        } else{
            return "Functional";
        }
    }

    /*
     * Getters
     */

    public String getTestID() {
        return this.testID;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSteps() {
        return this.steps;
    }

    public String getTags() {
        return this.tags;
    }

    public String getPreconditions() {
        return this.preconditions;
    }

    public String getResults() {
        return this.results;
    }
}
