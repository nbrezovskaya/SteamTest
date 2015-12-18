package webdriver.elements;

import org.openqa.selenium.By;

public class DropDownList extends BaseElement {

    protected DropDownList(By loc) {
        super(loc);
    }

    public DropDownList(By loc, String nameOf) {
        super(loc, nameOf);
    }

    protected DropDownList(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.dropDownList");
    }
}
