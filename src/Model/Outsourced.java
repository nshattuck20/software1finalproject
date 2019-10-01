package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private  SimpleStringProperty companyName;


    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public Outsourced() {
        super();
        this.companyName = new SimpleStringProperty();
    }
}
