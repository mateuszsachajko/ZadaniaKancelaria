package pl.kancelaria.modelFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty desc = new SimpleStringProperty();
    private StringProperty formOfSettlement = new SimpleStringProperty();

    //getters and setters


    public String getFormOfSettlement() {
        return formOfSettlement.get();
    }

    public StringProperty formOfSettlementProperty() {
        return formOfSettlement;
    }

    public void setFormOfSettlement(String formOfSettlement) {
        this.formOfSettlement.set(formOfSettlement);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    @Override
    public String toString() {
        return getName();
    }
}
