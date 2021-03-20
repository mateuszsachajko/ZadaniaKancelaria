package pl.kancelaria.modelFX;

import com.j256.ormlite.stmt.Where;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.dao.CaseDao;
import pl.kancelaria.database.dao.EmployeeDao;
import pl.kancelaria.database.dao.TaskDao;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Employee;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.Converters;
import pl.kancelaria.utils.StatusENUM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskModel {
    private ObjectProperty<TaskFX> taskFX = new SimpleObjectProperty<>(new TaskFX());
    private ObjectProperty<TaskFX> taskFXEdit = new SimpleObjectProperty<>(new TaskFX());
    private ObservableList<CaseFX> caseFXES = FXCollections.observableArrayList();
    private ObservableList<EmployeeFX> employeeFXES = FXCollections.observableArrayList();
    private List<TaskFX> taskFxList = new ArrayList<>();
    private ObservableList<TaskFX> taskFXObservableList = FXCollections.observableArrayList();

    public void init() throws AppExc {
        TaskDao taskDao = new TaskDao();
        List<Task> tasks = taskDao.queryForAll(Task.class);
        taskFxList.clear();
        tasks.forEach(t->{
            this.taskFxList.add(Converters.taskToTaskFX(t));
        });
        this.taskFXObservableList.setAll(taskFxList);
        initCaseList();
        initEmplList();
    }

    public void initCaseList() throws AppExc {
        CaseDao caseDao = new CaseDao();
        List<Case> list = caseDao.queryForAll(Case.class);
        list.forEach(c -> {
            this.caseFXES.add(Converters.caseToCaseFx(c));
        });
        DbManager.closeConnectionSource();
    }
    public void initEmplList() throws AppExc {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> list = employeeDao.queryForAll(Employee.class);
        list.forEach(e ->{
            this.employeeFXES.add(Converters.employeeToEmployeeFX(e));
        });
    }

    public void save() throws AppExc {
        Task task = Converters.TaskFxToTask(this.taskFX.get());
        task.setStatus(StatusENUM.Status.DO_ZROBIENIA);
        CaseDao caseDao = new CaseDao();
        Case cas = caseDao.findById(Case.class, this.taskFX.get().getCas().getId());
        task.setCas(cas);
        DbManager.closeConnectionSource();
        EmployeeDao employeeDao = new EmployeeDao();
        Employee emp = employeeDao.findById(Employee.class, taskFX.get().getEmployee().getId());
        task.setEmployee(emp);
        DbManager.closeConnectionSource();
        TaskDao taskDao = new TaskDao();
        taskDao.createOrUpdate(task);
    }

    public void editStatus(int id, StatusENUM.Status status) throws AppExc {
        TaskDao taskDao = new TaskDao();
        Task task = taskDao.findById(Task.class, id);
        task.setStatus(status);
        taskDao.createOrUpdate(task);
        DbManager.closeConnectionSource();
        this.init();
    }

    public void edit() throws AppExc {
        TaskDao taskDao = new TaskDao();
        Task tempTask = taskDao.findById(Task.class, getTaskFX().getId());
        tempTask.setStatus(getTaskFX().getStatus());
        taskDao.createOrUpdate(tempTask);
        DbManager.closeConnectionSource();
        init();
    }


    // getters and setters

    public TaskFX getTaskFX() {
        return taskFX.get();
    }

    public ObjectProperty<TaskFX> taskFXProperty() {
        return taskFX;
    }

    public void setTaskFX(TaskFX taskFX) {
        this.taskFX.set(taskFX);
    }

    public TaskFX getTaskFXEdit() {
        return taskFXEdit.get();
    }

    public ObjectProperty<TaskFX> taskFXEditProperty() {
        return taskFXEdit;
    }

    public void setTaskFXEdit(TaskFX taskFXEdit) {
        this.taskFXEdit.set(taskFXEdit);
    }

    public ObservableList<CaseFX> getCaseFXES() {
        return caseFXES;
    }

    public void setCaseFXES(ObservableList<CaseFX> caseFXES) {
        this.caseFXES = caseFXES;
    }

    public ObservableList<EmployeeFX> getEmployeeFXES() {
        return employeeFXES;
    }

    public void setEmployeeFXES(ObservableList<EmployeeFX> employeeFXES) {
        this.employeeFXES = employeeFXES;
    }

    public List<TaskFX> getTaskFxList() {
        return taskFxList;
    }

    public void setTaskFxList(List<TaskFX> taskFxList) {
        this.taskFxList = taskFxList;
    }

    public ObservableList<TaskFX> getTaskFXObservableList() {
        return taskFXObservableList;
    }

    public void setTaskFXObservableList(ObservableList<TaskFX> taskFXObservableList) {
        this.taskFXObservableList = taskFXObservableList;
    }
}
