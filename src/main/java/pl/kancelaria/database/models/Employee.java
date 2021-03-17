package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;

public class Employee  implements BaseModel{

    public Employee(){}
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "IMIE",canBeNull = false)
    private String name;
    @DatabaseField(columnName = "NAZWISKO",canBeNull = false)
    private String surname;
    @DatabaseField(columnName = "STANOWISKO", canBeNull = false)
    private String position;


}
