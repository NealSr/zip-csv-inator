package org.nealsr.demo.util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nealsr.demo.domain.CsvRecord;

/**
 * Utility for adding up the metrics from the zipped CSV file.  Tracks unique IPs, Status Code frequency, and total bytes.
 */
public class SecAccumulator {
    private Set<String> ipAddressSet = new HashSet<String>();
    private Map<Integer, Integer> statusCodeMap = new HashMap<Integer, Integer>();
    private Long totalBytes = Long.valueOf(0);
    private NumberFormat numberFormat = NumberFormat.getNumberInstance();

    /**
     * Processes a CsvRecord and increments the metrics accordingly.
     * @param record the CsvRecord to process.
     */
    public void processRow(CsvRecord record) {
        ipAddressSet.add(record.getIp());

        int code = record.getCode();
        if (statusCodeMap.containsKey(code)) {
            statusCodeMap.put(code, statusCodeMap.get(code) + 1);
        } else {
            statusCodeMap.put(code, 1);
        }

        totalBytes += record.getSize();
    }

    /**
     * Prints the current state to the console, with some formatting.
     */
    public void printCurrentState() {
        System.out.println("Unique IPs: " + numberFormat.format(ipAddressSet.size()));
        System.out.println("Total Bytes: " + numberFormat.format(totalBytes));
        System.out.println("Status Codes:");
        for (Map.Entry<Integer, Integer> entry : statusCodeMap.entrySet()) {
            if (entry.getKey() == 0) {
                System.out.println("\tUKN: " + numberFormat.format(entry.getValue()));
            } else {
                System.out.println("\t" + entry.getKey() + ": " + numberFormat.format(entry.getValue()));
            }
        }
        // additional line for breaking apart logs
        System.out.println();
    }
}