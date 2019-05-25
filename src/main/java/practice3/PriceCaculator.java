package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private Order order;

    public PriceCaculator(Order order) {
        this.order = order;
    }

    public BigDecimal calculate() {
        BigDecimal subTotal = new BigDecimal(0);

        subTotal = calcualteSubTotal(subTotal);
        subTotal = subtractDiscounts(subTotal);

        BigDecimal tax = calculateTax(subTotal);
        return calculateGrandTotal(subTotal, tax);
    }

    private BigDecimal calculateGrandTotal(BigDecimal subTotal, BigDecimal tax) {
        return subTotal.add(tax);
    }

    private BigDecimal calculateTax(BigDecimal subTotal) {
        return subTotal.multiply(order.getTax());
    }

    private BigDecimal subtractDiscounts(BigDecimal subTotal) {
        for (BigDecimal discount : order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }

    private BigDecimal calcualteSubTotal(BigDecimal subTotal) {
        for (OrderLineItem lineItem : order.getOrderLineItemList()) {
            subTotal = calculateGrandTotal(subTotal, lineItem.getPrice());
        }
        return subTotal;
    }
}
