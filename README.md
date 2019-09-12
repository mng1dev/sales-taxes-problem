# Sales Taxes Problem

## Overview

This library provides a simple command-line interface for printing purchase receipts.

## Test
Classic unit test output:
```shell script
mvn clean test
```

Generate a report using openclover:

Add this to your `.m2/settings.xml` file so you can reference Clover by its short name clover.
```xml
<pluginGroups>
    <pluginGroup>org.openclover</pluginGroup>
</pluginGroups>
```

Then run the following command, that will generate a test report in the `target/site/clover` directory.
```shell script
mvn clean clover:setup test clover:aggregate clover:clover
```

## Build
To build an executable jar containing all the dependencies, use the following command
```shell script
mvn clean package assembly:single
```

## Usage 
Once the jar has been created, it can be executed using the following parameters

```text
usage: SalesTaxes
 -c <arg>   Path to the file containing the categories of each product in
            the catalog.
 -e <arg>   Comma-separated list of categories that are exent from basic
            sales taxes.
 -i <arg>   Path to the input file containing the list of purchases.

```

For instance:
```shell script
java -jar target/sales-taxes-1.0-SNAPSHOT-jar-with-dependencies.jar -e "books,medical products,food" -c categories.csv -i input_1.txt
```
Will print
```text
2 book: 24.98
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 42.32
```