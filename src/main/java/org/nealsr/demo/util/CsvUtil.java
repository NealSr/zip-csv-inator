package org.nealsr.demo.util;

import com.opencsv.CSVParser;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.nealsr.demo.domain.CsvRecord;

/**
 * Helper utility to parse sec log file records.
 */
public class CsvUtil {
    //header row: ip,date,time,zone,cik,accession,extention,code,size,idx,norefer,noagent,find,crawler,browser
    //col nums:   0  1    2    3    4   5         6         7    8    9   10      11      12   13      14
    private static final int num_cols = 15;
    private static final int ip_col = 0;
    private static final int code_col = 7;
    private static final int size_col = 8;

    /**
     * Parses a log file line into a CsvRecord, if possible.  By default if numbers are present but can't be parsed
     * it will default to zero.  If the total columns are off or an IO Exception occurs, will return null.
     * @param line the log file line to parse.
     * @return a populated CsvRecord if possible, or null if a parsing error occurs.
     */
    public static CsvRecord parseCsvRecord(String line) {
        CSVParser parser = new CSVParser();
        CsvRecord record = null;
        try {
            String[] fields = parser.parseLine(line);
            if (fields.length != num_cols) {
                return null;
            } else {
                String ip = fields[ip_col];
                String codeString = fields[code_col];
                String sizeString = fields[size_col];
                int code = 0;
                try {
                    code = NumberFormat.getNumberInstance().parse(codeString).intValue();
                } catch (ParseException e) {
                    // ignore and default to zero;
                }
                Long size = 0L;
                try {
                    size = NumberFormat.getNumberInstance().parse(sizeString).longValue();
                } catch (ParseException e) {
                    // ignore and default to zero;
                }
                record = new CsvRecord(ip, code, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return record;
    }
}
