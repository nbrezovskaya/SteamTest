package demo.test.model;

import webdriver.elements.DivElement;

/**
 * Created by Nina on 17.12.2015.
 */
public class Game {
    private String name;
    private String priceText;
    private int discount;
    private DivElement element;

    public Game(String name, String priceText, int discount, DivElement element) {
        this.name = name;
        this.priceText = priceText;
        this.discount = discount;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public DivElement getElement() {
        return element;
    }

    public void setElement(DivElement element) {
        this.element = element;
    }
}
