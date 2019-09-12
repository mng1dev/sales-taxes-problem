import com.vektor.salestaxes.SalesTaxes;
import com.vektor.salestaxes.receipt.model.Receipt;
import com.vektor.salestaxes.receipt.writer.StringReceiptWriter;
import org.junit.Test;


import static org.junit.Assert.*;

public class TestSalesTaxes {
    @Test
    public void TestSalesTaxesLoadCategories() {
        String path = "src/test/resources/categories.csv";

        SalesTaxes salesTaxes = new SalesTaxes();
        salesTaxes.loadCategories(path);

        assertEquals(6, salesTaxes.getCategories().size());
    }

    @Test
    public void TestSalesTaxesLoadPurchases() {
        String path = "src/test/resources/purchase_1.txt";

        SalesTaxes salesTaxes = new SalesTaxes();
        salesTaxes.loadPurchaseList(path);

        assertEquals(3, salesTaxes.getPurchaseList().size());
    }

    @Test
    public void TestSalesTaxesGenerateReceipt() {
        String purchasePath = "src/test/resources/purchase_1.txt";
        String categoriesPath = "src/test/resources/categories.csv";

        SalesTaxes salesTaxes = new SalesTaxes();
        salesTaxes.loadExcludedCategories(new String[]{"books", "food", "medical products"});
        salesTaxes.loadPurchaseList(purchasePath);
        salesTaxes.loadCategories(categoriesPath);

        Receipt receipt = salesTaxes.generateReceipt();

        assertEquals(salesTaxes.getPurchaseList().size(), receipt.size());
        assertEquals(
                24.98,
                receipt.getPurchases().get(0).getPriceAfterTaxes().doubleValue(),
                0.000001
        );
        assertEquals(
                16.49,
                receipt.getPurchases().get(1).getPriceAfterTaxes().doubleValue(),
                0.000001
        );
    }

    @Test
    public void TestSalesTaxesGenerateReceiptWrite() {
        String purchasePath = "src/test/resources/purchase_3.txt";
        String categoriesPath = "src/test/resources/categories.csv";

        SalesTaxes salesTaxes = new SalesTaxes();

        salesTaxes.loadExcludedCategories(new String[]{"books", "food", "medical products"});
        salesTaxes.loadPurchaseList(purchasePath);
        salesTaxes.loadCategories(categoriesPath);

        Receipt receipt = salesTaxes.generateReceipt();

        System.out.println(
            new StringReceiptWriter().write(receipt)
        );
    }
}
