package pl.kancelaria.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;
import java.util.Date;
@DatabaseTable(tableName = "SPRAWY")
public class Case implements BaseModel{

    public static final String KLIENT = "KLIENT";


    public Case(){}
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "SYGNATURA")
    private String signature;
    @DatabaseField(columnName = "SAD")
    private String court;
    @DatabaseField(columnName = KLIENT,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Client client;
    @DatabaseField(columnName = "CZY_SKONCZONE", defaultValue = "false")
    private boolean finished;
    @DatabaseField(columnName = "OPIS")
    private String desc;
    @DatabaseField(columnName = "DATA_ROZPOCZECIA")
    private Date startDate;
    @ForeignCollectionField(columnName = "TASK_ID")
    private ForeignCollection<Task> tasks;

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

    public static String getKLIENT() {
        return KLIENT;
    }

    public ForeignCollection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ForeignCollection<Task> tasks) {
        this.tasks = tasks;
    }

}
