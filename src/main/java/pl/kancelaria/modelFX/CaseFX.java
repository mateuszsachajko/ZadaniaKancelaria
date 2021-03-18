package pl.kancelaria.modelFX;

import com.j256.ormlite.field.DatabaseField;
import javafx.beans.property.*;
import pl.kancelaria.database.models.Client;

import java.time.LocalDate;
import java.util.Date;

public class CaseFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty signature = new SimpleStringProperty();
    private StringProperty court = new SimpleStringProperty();
    private ObjectProperty<ClientFX> client = new SimpleObjectProperty<>();
    private BooleanProperty finished = new SimpleBooleanProperty();
    private StringProperty desc = new SimpleStringProperty();
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();

    // getters and setters


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSignature() {
        return signature.get();
    }

    public StringProperty signatureProperty() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature.set(signature);
    }

    public String getCourt() {
        return court.get();
    }

    public StringProperty courtProperty() {
        return court;
    }

    public void setCourt(String court) {
        this.court.set(court);
    }

    public ClientFX getClient() {
        return client.get();
    }

    public ObjectProperty<ClientFX> clientProperty() {
        return client;
    }

    public void setClient(ClientFX client) {
        this.client.set(client);
    }

    public boolean isFinished() {
        return finished.get();
    }

    public BooleanProperty finishedProperty() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished.set(finished);
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

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }
}
