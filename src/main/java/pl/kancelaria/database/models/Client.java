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
}
