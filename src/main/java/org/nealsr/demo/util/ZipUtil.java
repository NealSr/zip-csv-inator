package org.nealsr.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Helper utility to open a buffered reader to a csv file in a remote zip url.
 */
public class ZipUtil {

    /**
     * Opens a URL to a zip file and returns a BufferedReader pointing to the first .csv file in the Zip archive.
     * @param url the full URL to the zip file online.
     * @return a BufferedReader pointing to the first .csv file, or null if an exception occurs.
     */
    public static BufferedReader getBufferedReaderForCSVFromURL(String url) {
        BufferedReader csvReader = null;
        try {
            final URL zipUrl = new URL(url);
            InputStream uis = new BufferedInputStream(zipUrl.openStream(), 1024);
            ZipInputStream zis = new ZipInputStream(uis);
            ZipEntry zipEntry = zis.getNextEntry();
            if (zipEntry != null && zipEntry.getName().endsWith("csv")) {
                csvReader = new BufferedReader(new InputStreamReader(zis, "UTF-8"));
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return csvReader;
    }
}
