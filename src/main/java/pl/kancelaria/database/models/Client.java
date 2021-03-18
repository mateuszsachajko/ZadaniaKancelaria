package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;

public class Client implements BaseModel{

    public Client(){}

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "NAZWA", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "OPIS")
    private String desc;

    //getters and setters

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
}
