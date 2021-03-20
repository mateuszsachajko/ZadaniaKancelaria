package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRACOWNICY")
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


    //setters getters


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
