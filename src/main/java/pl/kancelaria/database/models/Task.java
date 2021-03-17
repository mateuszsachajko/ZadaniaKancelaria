package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.kancelaria.utils.StatusENUM;

import java.time.LocalDateTime;
import java.util.Date;

@DatabaseTable(tableName = "ZADANIA")
public class Task implements BaseModel{

    public Task(){
        this.deadLine = todayPlus14Days();
        this.status = StatusENUM.Status.DO_ZROBIENIA;
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "OPIS")
    private String desc;
    @DatabaseField(columnName = "TERMIN")
    private Date deadLine;
    @DatabaseField(columnName = "SPRAWA", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Case cas;
    @DatabaseField(columnName = "PRIORYTET", defaultValue = "4")
    private int priority;
    @DatabaseField(columnName = "STATUS")
    private StatusENUM.Status status;
    @DatabaseField(columnName = "Odpowiedzialny pracownik", canBeNull = false)
    private Employee employee;



    public Date todayPlus14Days(){
        Date date = new Date();
        LocalDateTime.from(date.toInstant()).plusDays(14);
        return date;
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Case getCas() {
        return cas;
    }

    public void setCas(Case cas) {
        this.cas = cas;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public StatusENUM.Status getStatus() {
        return status;
    }

    public void setStatus(StatusENUM.Status status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
