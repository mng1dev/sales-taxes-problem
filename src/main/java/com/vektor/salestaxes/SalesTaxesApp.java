package com.vektor.salestaxes;

import com.vektor.salestaxes.receipt.writer.ReceiptWriter;
import com.vektor.salestaxes.receipt.writer.StringReceiptWriter;
import org.apache.commons.cli.*;

public class SalesTaxesApp {

    private static final Options options = new Options();

    private static HelpFormatter formatter;

    private static String[] excludedCategories;
    private static String purchasesPath;
    private static String categoriesPath;

    public static void main(String[] args) {
        formatter = new HelpFormatter();
        initCommandLineOptions();
        try {
            CommandLine cmd = parseOptions(args);
            loadArguments(cmd);

            if (areArgumentsSet()) {
                SalesTaxes salesTaxes = new SalesTaxes();

                salesTaxes.loadExcludedCategories(excludedCategories);
                salesTaxes.loadCategories(categoriesPath);
                salesTaxes.loadPurchaseList(purchasesPath);

                ReceiptWriter writer = new StringReceiptWriter();
                String printedReceipt = writer.write(salesTaxes.generateReceipt());

                System.out.println(printedReceipt);
            } else {
                formatter.printHelp("SalesTaxes", options);
            }
        } catch (ParseException e) {
            System.err.println("There was a problem parsing the input arguments: " + e.getMessage());
            formatter.printHelp("SalesTaxes", options);
        }
    }

    private static void initCommandLineOptions() {
        options.addOption("e", true, "Comma-separated list of categories that are exent from basic sales taxes.");
        options.addOption("i", true, "Path to the input file containing the list of purchases.");
        options.addOption("c", true, "Path to the file containing the categories of each product in the catalog.");
    }

    private static CommandLine parseOptions(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static void loadArguments(CommandLine cmd) {
        if (cmd.hasOption("e")) {
            excludedCategories = cmd.getOptionValue("e").split(",");
        } else {
            printParsingError("Missing excluded categories list");
        }
        if (cmd.hasOption("i")) {
            purchasesPath = cmd.getOptionValue("i");
        } else {
            printParsingError("Missing purchases file path");
        }
        if (cmd.hasOption("c")) {
            categoriesPath = cmd.getOptionValue("c");
        } else {
            printParsingError("Missing categories file path");
        }
    }

    private static boolean areArgumentsSet() {
        return excludedCategories != null && categoriesPath != null && purchasesPath != null;
    }

    private static void printParsingError(String errorMessage) {
        System.err.println(String.format("Parsing error: %s.", errorMessage));
    }

}
