package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DivElement extends BaseElement {
    public DivElement(By loc) {
        super(loc);
    }

    public DivElement(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public DivElement(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    public String findInnerLabelText(By loc) {
        return element.findElement(loc).getText();
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.div");
    }
}
