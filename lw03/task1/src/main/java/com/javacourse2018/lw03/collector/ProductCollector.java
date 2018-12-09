package com.javacourse2018.lw03.collector;

import com.javacourse2018.lw03.model.product.Measure;
import com.javacourse2018.lw03.model.product.Product;
import com.javacourse2018.lw03.model.product.ProductImpl;

import java.math.BigDecimal;

public class ProductCollector extends BaseCollector<Product> {

    public ProductCollector() {
        this.collect();
    }

    protected Integer getId(Product product) {
        return product.getId();
    }

    protected void printEntityInformation(Product product) {
        LOG.info("---Added new product---");
        LOG.info("  Id: " + product.getId());
        LOG.info("  Name: " + product.getName());
        LOG.info("  Amount: " + product.getAmount());
        LOG.info("  Measure: " + Measure.measureToString(product.getMeasure()));
        LOG.info("  Price: " + product.getPrice());
        LOG.info("  Discount: " + product.getDiscount() + "%");
        if (product.isAdult()) {
            LOG.info("  Product for adults.");
        }
        LOG.info("");
    }

    protected void fillEntityList() {
        Product whiskey = new ProductImpl();
        whiskey.setId(0);
        whiskey.setName("Whiskey");
        whiskey.setAdult(true);
        whiskey.setDiscount(0);
        whiskey.setMeasure(Measure.LITER);
        whiskey.setPrice(new BigDecimal(1000));
        whiskey.setAmount(100);
        this.entityList.add(whiskey);


        Product winston = new ProductImpl();
        winston.setId(1);
        winston.setName("Winston");
        winston.setAdult(true);
        winston.setDiscount(3);
        winston.setMeasure(Measure.PIECE);
        winston.setPrice(new BigDecimal(100));
        winston.setAmount(70);
        this.entityList.add(winston);

        Product bread = new ProductImpl();
        bread.setId(2);
        bread.setName("Bread");
        bread.setAdult(false);
        bread.setDiscount(0);
        bread.setMeasure(Measure.PIECE);
        bread.setPrice(new BigDecimal(25));
        bread.setAmount(1000);
        this.entityList.add(bread);

        Product cola = new ProductImpl();
        cola.setId(3);
        cola.setName("Coca-cola");
        cola.setAdult(false);
        cola.setDiscount(0);
        cola.setMeasure(Measure.PIECE);
        cola.setPrice(new BigDecimal(69));
        cola.setAmount(1241);
        this.entityList.add(cola);

        Product meat = new ProductImpl();
        meat.setId(4);
        meat.setName("Meat");
        meat.setAdult(false);
        meat.setDiscount(5);
        meat.setMeasure(Measure.KG);
        meat.setPrice(new BigDecimal(227));
        meat.setAmount(5110);
        this.entityList.add(meat);
    }

}
