package pl.kancelaria.modelFX;

import javafx.beans.property.*;
import pl.kancelaria.utils.StatusENUM;

import java.time.LocalDate;

public class TaskFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty desc = new SimpleStringProperty();
    private ObjectProperty<LocalDate> deadline = new SimpleObjectProperty<>();
    private ObjectProperty<CaseFX> cas = new SimpleObjectProperty<>();
    private IntegerProperty priority = new SimpleIntegerProperty();
    private ObjectProperty<StatusENUM.Status> status = new SimpleObjectProperty<>();
    private ObjectProperty<EmployeeFX> employee = new SimpleObjectProperty<>();

    //getters and setters


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public LocalDate getDeadline() {
        return deadline.get();
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline.set(deadline);
    }

    public CaseFX getCas() {
        return cas.get();
    }

    public ObjectProperty<CaseFX> casProperty() {
        return cas;
    }

    public void setCas(CaseFX cas) {
        this.cas.set(cas);
    }

    public int getPriority() {
        return priority.get();
    }

    public IntegerProperty priorityProperty() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
    }

    public StatusENUM.Status getStatus() {
        return status.get();
    }

    public ObjectProperty<StatusENUM.Status> statusProperty() {
        return status;
    }

    public void setStatus(StatusENUM.Status status) {
        this.status.set(status);
    }

    public EmployeeFX getEmployee() {
        return employee.get();
    }

    public ObjectProperty<EmployeeFX> employeeProperty() {
        return employee;
    }

    public void setEmployee(EmployeeFX employee) {
        this.employee.set(employee);
    }
}
