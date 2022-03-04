package org.nealsr.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.nealsr.demo.util.SecAccumulator;

public class ZipInate {
    public static void main(String[] args) {

        final SecAccumulator accumulator = new SecAccumulator();
        accumulator.printCurrentState();

        // InputStream inputStream = new URL("http://www.sec.gov/dera/data/Public-EDGAR-log-file-data/2017/Qtr2/log20170630.zip").openStream();
        try {
            FileInputStream fis = new FileInputStream("/Users/nerichardson/Downloads/log20170630.zip");
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (zipEntry.getName().endsWith("csv")) {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(zis));
                    String nextLine = reader.readLine();
                    int lines = 0;
                    while (nextLine != null) {
                        accumulator.processRow(nextLine);
                        lines++;
                        if (lines % 1000 == 0) {
                         //   accumulator.printCurrentState();
                        }
                        nextLine = reader.readLine();
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.close();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}