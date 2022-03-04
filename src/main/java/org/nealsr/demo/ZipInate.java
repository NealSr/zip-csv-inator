package org.nealsr.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;

import org.nealsr.demo.domain.CsvRecord;
import org.nealsr.demo.util.CsvUtil;
import org.nealsr.demo.util.SecAccumulator;
import org.nealsr.demo.util.ZipUtil;

/**
 * Main java class for parsing sec.gov csv data.
 */
public class ZipInate {
    private static final String SEC_URL = "https://www.sec.gov/dera/data/Public-EDGAR-log-file-data/2017/Qtr2/log20170630.zip";
    private static final int ONE_MILLION = 1_000_000;

    public static void main(String[] args) {
        final SecAccumulator accumulator = new SecAccumulator();
        accumulator.printCurrentState();

        BufferedReader csvReader = ZipUtil.getBufferedReaderForCSVFromURL(SEC_URL);

        try {
            int rowNumber = 0;
            String nextLine = csvReader.readLine();
            while (nextLine != null) {
                rowNumber++;
                if (nextLine.startsWith("ip")) {
                    // Header Row - Ignore
                    nextLine = csvReader.readLine();
                    continue;
                }
                CsvRecord lineRecord = CsvUtil.parseCsvRecord(nextLine);
                if (lineRecord != null) {
                    accumulator.processRow(lineRecord);
                }
                // track progress every 1M records
                if (rowNumber % ONE_MILLION == 0) {
                    accumulator.printCurrentState();
                }
                nextLine = csvReader.readLine();
            }

            // print final results
            accumulator.printCurrentState();
            System.out.println("\nTotal Rows: " + NumberFormat.getNumberInstance().format(rowNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}