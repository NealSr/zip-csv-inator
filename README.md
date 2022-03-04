# zip-csv-inator
A simple command-line utility to download a zip file, extract to a csv, and parse the data.

## Requirements
* JDK 8
* Maven 3

## Overview
This project is a simple command-line jar that when executed will go out to www.sec.gov and pull down a multi-gig zip file, extract the CSV log record, and calculate metrics on three fields in the logs.  The actual file being parsed is: `https://www.sec.gov/dera/data/Public-EDGAR-log-file-data/2017/Qtr2/log20170630.zip`

The project can be build with Maven and Executed from the command line with `java -jar`  It will log the metrics for every 1M rows, then log the final results at the end.

NOTE: As this project is a proof-of-concept, most error handling is either defaulted to null rows, zeros, or just letting severe errors print out the stack trace.  As long as the URL above is accessible the jar should run fine without exception.

## Build and Run
This project is built with maven from a terminal.
```
mvn clean install
```
Once built, the war file will be located in `./target/zip-csv-inator-1.0.jar` and can be executed with:
```
java -jar target/zip-csv-inator-1.0.jar
```

The output should look similar to:
```
Unique IPs: 77,107
Total Bytes: 3,714,476,980,561
Status Codes:
	UKN: 174,605
	200: 15,741,005
	301: 4,770,998
	429: 618,033
	206: 409,061
	304: 426,727
	400: 12,906
	403: 620,965
	404: 79,654
	500: 439
	502: 287
	503: 145,038
	504: 281

Unique IPs: 78,181
Total Bytes: 3,821,351,877,238
Status Codes:
	UKN: 178,399
	200: 16,203,776
	301: 4,899,498
	429: 640,877
	206: 419,055
	304: 449,732
	400: 12,909
	403: 643,613
	404: 80,767
	500: 446
	502: 294
	503: 147,948
	504: 286


Total Rows: 23,677,601
```