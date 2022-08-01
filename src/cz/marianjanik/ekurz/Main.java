package cz.marianjanik.ekurz;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.lineSeparator;

public class Main {

    public static void main(String[] args) throws SQLException {
        final String SEPARATOR = lineSeparator() + "---------------------";
        final int ELEMENT_ORDER_FOR_PRINTING = 3;
        final int NEW_PRICE_FOR_CHOOSE_ELEMENT = 2254;
        EshopMethods methods = new EshopMethods();

        System.out.println(SEPARATOR + "Printing the " + ELEMENT_ORDER_FOR_PRINTING + "rd record:");
        Item element = methods.loadItemById(ELEMENT_ORDER_FOR_PRINTING);
        System.out.println(element.getAllInfo());
        System.out.println();

        System.out.println(SEPARATOR + "Printing all records:");
        printAllInfo();

        System.out.println(SEPARATOR + "Insert new items: ");
        Item newElement = creationNewItem();
        System.out.println(newElement.getAllInfo());
        methods.saveItem(newElement);
        methods.saveItem(newElement);
        methods.saveItem(newElement);
        System.out.println();
        printAllInfo();

        System.out.println(SEPARATOR + "Update price in " + ELEMENT_ORDER_FOR_PRINTING + "rd record:");
        methods.updatePrice(ELEMENT_ORDER_FOR_PRINTING, BigDecimal.valueOf(NEW_PRICE_FOR_CHOOSE_ELEMENT));
        printAllInfo();

        System.out.println(SEPARATOR + "Delete items which is not in stock: ");
        methods.deleteAllOutOfStockItems();
        printAllInfo();
    }
    private static void printAllInfo() throws SQLException {
        EshopMethods methods = new EshopMethods();
        List<Item> items = methods.loadAllAvailableItems();
        for (Item thing:items) {
            System.out.println(thing.getAllInfo());
        }
    }

    private static Item creationNewItem() {
        Item newItem = new Item();
        newItem.setId(30);
        newItem.setPartNo("86");
        newItem.setSerialNo("dvd456");
        newItem.setName("DVD ROM");
        newItem.setDescription("Medium to software.");
        newItem.setNumberInStock(0);
        newItem.setPrice(BigDecimal.valueOf(36));
        return newItem;
    }
}
