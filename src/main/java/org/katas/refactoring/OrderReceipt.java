package org.katas.refactoring;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * @author Gordon Yang
 */
public class OrderReceipt {
    private Order order;
    private double salesTaxs = 0d;
    private double totalAmount = 0d;
    private double taxRate=0.1;
    public OrderReceipt(Order oredr) {
        this.order = oredr;
    }

    public String printReceipt() {
        StringBuilder receiptOutput = new StringBuilder();
        String headers = "======Printing Orders======\n";
        receiptOutput.append(headers);
        receiptOutput.append(order.getCustomerName());
        receiptOutput.append(order.getCustomerAddress());
        for (LineItem lineItem : order.getLineItems()) {
            receiptOutput.append(printLineItem(lineItem));
        }
        String salesTax = calculateSalesTaxs(order.getLineItems())+"";
        receiptOutput.append("Sales Tax").append('\t').append(salesTax);
        String totalAmout = calculateTotalAmount(order.getLineItems())+"";
        receiptOutput.append("Total Amount").append('\t').append(totalAmout);
        return receiptOutput.toString();
    }

    public String printLineItem(LineItem lineItem){
        String lineItemString =lineItem.getDescription()+'\t'+
                lineItem.getPrice()+'\t'+
                lineItem.getQuantity()+'\t'+
                lineItem.totalAmount()+'\n';
        return lineItemString;
    }
    public double calculateSalesTaxs(List<LineItem> lineItems){
        for (LineItem lineItem : lineItems) {
            double salesTax = lineItem.totalAmount() * taxRate;
            salesTaxs += salesTax;
        }
        return salesTaxs;
    }
    public double calculateTotalAmount(List<LineItem> lineItems){
        for (LineItem lineItem : lineItems) {
            double salesTax = lineItem.totalAmount() * taxRate;
            totalAmount += lineItem.totalAmount() + salesTax;
        }
        return totalAmount;
    }
}