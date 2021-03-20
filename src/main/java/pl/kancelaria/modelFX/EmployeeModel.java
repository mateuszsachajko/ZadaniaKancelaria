package pl.kancelaria.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.dao.ClientDao;
import pl.kancelaria.database.dao.EmployeeDao;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.database.models.Employee;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.Converters;

import java.util.List;

public class EmployeeModel {

    private ObjectProperty<EmployeeFX> employeeFx = new SimpleObjectProperty<>(new EmployeeFX());
    private ObjectProperty<EmployeeFX> employeeFxEdit = new SimpleObjectProperty<>(new EmployeeFX());
    private ObservableList<EmployeeFX> employeeList = FXCollections.observableArrayList();

    public void init() throws AppExc {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> list = employeeDao.queryForAll(Employee.class);
        initEmpList(list);
        DbManager.closeConnectionSource();
        }

    public void save() throws AppExc {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.createOrUpdate(Converters.employeeFxToEmployee(this.employeeFx.get()));
        DbManager.closeConnectionSource();
    }
    public void edit() throws AppExc {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee temp = employeeDao.findById(Employee.class, getEmployeeFx().getId());
        temp.setPosition(getEmployeeFx().getPosition());
        DbManager.closeConnectionSource();
        init();
    }
    public void delete() throws AppExc {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.deleteById(Employee.class, this.employeeFx.get().getId());
        DbManager.closeConnectionSource();
        // adding something to edit the employer responsible for the task;
    }
    public void initEmpList(List<Employee> list){
        list.forEach(c->{
            this.employeeList.add(Converters.employeeToEmployeeFX(c));
        });
    }

    // getters and setters

    public EmployeeFX getEmployeeFx() {
        return employeeFx.get();
    }

    public ObjectProperty<EmployeeFX> employeeFxProperty() {
        return employeeFx;
    }

    public void setEmployeeFx(EmployeeFX employeeFx) {
        this.employeeFx.set(employeeFx);
    }

    public EmployeeFX getEmployeeFxEdit() {
        return employeeFxEdit.get();
    }

    public ObjectProperty<EmployeeFX> employeeFxEditProperty() {
        return employeeFxEdit;
    }

    public void setEmployeeFxEdit(EmployeeFX employeeFxEdit) {
        this.employeeFxEdit.set(employeeFxEdit);
    }

    public ObservableList<EmployeeFX> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ObservableList<EmployeeFX> employeeList) {
        this.employeeList = employeeList;
    }
}
