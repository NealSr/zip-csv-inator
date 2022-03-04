package org.nealsr.demo.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecAccumulator {

    //ip,date,time,zone,cik,accession,extention,code,size,idx,norefer,noagent,find,crawler,browser
    //0  1    2    3    4   5         6         7    8    9   10      11      12   13      14
    private final int ip_col = 0;
    private final int code_col = 7;
    private final int size_col = 8;

    private Set<String> ipAddressSet = new HashSet<String>();
    private Map<Integer, Integer> statusCodeMap = new HashMap<Integer, Integer>();
    private Long totalBytes = Long.valueOf(0);

    private static String STATE_FORMAT = "Unique IPs: %d, Total Bytes: %d";

    public void processRow(String csv) {
        String[] columns = csv.split(",");
        if (columns.length != 15) {
            // do nothing - invalid
        } else {
            String ip = columns[ip_col];
            String code = columns[code_col];
            String bytes = columns[size_col];
            System.out.println(String.format("%s,%s,%s", ip, code, bytes));
            //int code = Integer.parseInt(columns[code_col]);
            // Long bytes = Long.parseLong(columns[size_col]);
            // totalBytes += bytes;

            ipAddressSet.add(ip);
//            if (statusCodeMap.containsKey(code)) {
//                statusCodeMap.put(code, statusCodeMap.get(code) + 1);
//            } else {
//                statusCodeMap.put(code, 1);
//            }
        }
    }

    public void printCurrentState() {
        System.out.println("Unique IPs: " + ipAddressSet.size());
        System.out.println("Total Bytes: " + totalBytes);
        System.out.println("Status Codes:");
        System.out.println(statusCodeMap);
    }

}
