package com.intuit.ctg.fuego.hiptest;

import java.io.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.testng.annotations.Test;

/**
 * Created by bryan on 8/22/16.
 */
public class HiptestTest {

    private static final File[] files = new File("./player_automation").listFiles();
    private static final String parentDirectory = "./player_automation/";
    private static final String workbookName = "RN Release Sept 1";

    @Test
    public void run() throws IOException {
        Workbook workbook = new Workbook(workbookName, files);
        workbook.create();

        Excel excel = new Excel(workbook);
        excel.write();
    }
}

