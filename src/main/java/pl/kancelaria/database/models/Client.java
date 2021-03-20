package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "KLIENCI")
public class Client implements BaseModel{

    public Client(){}

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "NAZWA", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "OPIS")
    private String desc;
    @DatabaseField(columnName = "FORMA_ROZLICZENIA")
    private String formOfSettlement;

    //getters and setters
    public String getFormOfSettlement() {
        return formOfSettlement;
    }

    public void setFormOfSettlement(String formOfSettlement) {
        this.formOfSettlement = formOfSettlement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", formOfSettlement='" + formOfSettlement + '\'' +
                '}';
    }
}
