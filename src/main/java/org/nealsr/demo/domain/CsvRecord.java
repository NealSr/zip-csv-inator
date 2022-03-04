package org.nealsr.demo.domain;

/**
 * A CSV Record POJO for parsing rows into.
 */
public class CsvRecord {
    private String ip;
    private int code;
    private Long size;

    /**
     * Default Constructor
     * @param ip the IP Address.
     * @param code the HTTP Response Code.
     * @param size the response size in bytes.
     */
    public CsvRecord(String ip, int code, Long size) {
        this.ip = ip;
        this.code = code;
        this.size = size;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
