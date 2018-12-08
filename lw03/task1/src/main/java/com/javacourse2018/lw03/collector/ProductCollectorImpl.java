package com.javacourse2018.lw03.collector;

import com.javacourse2018.lw03.model.product.Measure;
import com.javacourse2018.lw03.model.product.Product;
import com.javacourse2018.lw03.model.product.ProductImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductCollectorImpl implements ProductCollector {
    private static final Log LOG = LogFactory.getLog(ProductCollectorImpl.class);

    private Map<Integer, Product> products = new TreeMap<>();
    private List<Product> productList = new ArrayList<>();

    public ProductCollectorImpl() {
        this.collect();
    }

    public Integer getProductsSize() {
        return this.products.size();
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Product getProduct(Integer id) throws IndexOutOfBoundsException {
        if (this.products.containsKey(id)) {
            return this.products.get(id);
        }
        throw new IndexOutOfBoundsException();
    }

    public void removeProduct(Integer id) throws IndexOutOfBoundsException {
        if (this.products.containsKey(id)) {
            this.products.remove(id);
        }
        throw new IndexOutOfBoundsException();
    }

    private void collect() {
        this.fillProductList();
        for (Product product: this.productList) {
            this.products.put(product.getId(), product);
            this.printProductInformation(product);
        }

    }

    private void printProductInformation(Product product) {
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

    private void fillProductList() {
        Product product1 = new ProductImpl();
        product1.setId(1);
        product1.setName("Whiskey");
        product1.setAdult(true);
        product1.setDiscount(0);
        product1.setMeasure(Measure.LITER);
        product1.setPrice(new BigDecimal(1000));
        product1.setAmount(10);
        this.productList.add(product1);


        Product product2 = new ProductImpl();
        product2.setId(2);
        product2.setName("Winston");
        product2.setAdult(true);
        product2.setDiscount(3);
        product2.setMeasure(Measure.PIECE);
        product2.setPrice(new BigDecimal(100));
        product2.setAmount(10);
        this.productList.add(product2);

        Product bread = new ProductImpl();
        bread.setId(3);
        bread.setName("Bread");
        bread.setAdult(false);
        bread.setDiscount(0);
        bread.setMeasure(Measure.PIECE);
        bread.setPrice(new BigDecimal(25));
        bread.setAmount(100);
        this.productList.add(bread);

        Product cola = new ProductImpl();
        cola.setId(4);
        cola.setName("Coca-cola");
        cola.setAdult(false);
        cola.setDiscount(30);
        cola.setMeasure(Measure.PIECE);
        cola.setPrice(new BigDecimal(69));
        cola.setAmount(11);
        this.productList.add(cola);

        Product meat = new ProductImpl();
        meat.setId(5);
        meat.setName("Meat");
        meat.setAdult(false);
        meat.setDiscount(10);
        meat.setMeasure(Measure.KG);
        meat.setPrice(new BigDecimal(227));
        meat.setAmount(50);
        this.productList.add(meat);
    }

}
