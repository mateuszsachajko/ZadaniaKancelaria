package pl.kancelaria.database.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Case implements BaseModel{

    public static final String KLIENT = "KLIENT";

    public Case(){}
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "SYGNATURA")
    private String signature;
    @DatabaseField(columnName = "SAD")
    private String court;
    @DatabaseField(columnName = KLIENT)
    private Client client;
    @DatabaseField(columnName = "CZY_SKONCZONE", defaultValue = "false")
    private boolean finished;
    @DatabaseField(columnName = "OPIS")
    private String desc;
    @DatabaseField(columnName = "DATA_ROZPOCZECIA")
    private Date startDate;

    public int getId() {
        return id;
    }


    // getters and setters
    public void setId(int id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCourt() {
        return court;
    }


    //getters and setters
    public void setCourt(String court) {
        this.court = court;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
