package pl.kancelaria.utils;

import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.database.models.Employee;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.modelFX.CaseFX;
import pl.kancelaria.modelFX.ClientFX;
import pl.kancelaria.modelFX.EmployeeFX;
import pl.kancelaria.modelFX.TaskFX;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Converters {

    public static Case caseFxToCase(CaseFX caseFx){
        Case cas = new Case();
        cas.setClient(clientFxToClient(caseFx.clientProperty().get()));
        cas.setCourt(caseFx.getCourt());
        cas.setDesc(caseFx.getDesc());
        cas.setFinished(caseFx.finishedProperty().get());
        cas.setId(caseFx.getId());
        cas.setSignature(caseFx.getSignature());
        cas.setStartDate(localDateToDate(caseFx.getStartDate()));
        return cas;
    }

    public static CaseFX caseToCaseFx(Case cas){
        CaseFX caseFX = new CaseFX();
        caseFX.setClient(clientToClientFX(cas.getClient()));
        caseFX.setCourt(cas.getCourt());
        caseFX.setDesc(cas.getDesc());
        caseFX.setFinished(cas.isFinished());
        caseFX.setId(cas.getId());
        caseFX.setSignature(cas.getSignature());
        caseFX.setStartDate(dateToLocalDate(cas.getStartDate()));
        return caseFX;

    }

    public static Client clientFxToClient(ClientFX clientFX){
        Client client = new Client();
        client.setDesc(clientFX.getDesc());
        client.setId(clientFX.getId());
        client.setName(clientFX.getName());

        return client;
    }

    public static ClientFX clientToClientFX(Client client){
        ClientFX clientFX = new ClientFX();
        clientFX.setDesc(client.getDesc());
        clientFX.setId(client.getId());
        clientFX.setName(client.getName());
        return clientFX;
    }

    public static Employee employeeFxToEmployee(EmployeeFX employeeFX){
        Employee emp = new Employee();

        emp.setId(employeeFX.getId());
        emp.setName(employeeFX.getName());
        emp.setPosition(employeeFX.getPosition());
        emp.setSurname(employeeFX.getSurname());
        return emp;

    }
    public static EmployeeFX employeeToEmployeeFX(Employee employee){
        EmployeeFX eFX = new EmployeeFX();
        eFX.setId(employee.getId());
        eFX.setName(employee.getName());
        eFX.setPosition(employee.getPosition());
        eFX.setSurname(employee.getSurname());
        return eFX;
    }

    public static Task TaskFxToTask(TaskFX taskFX){
        Task task = new Task();
        task.setId(taskFX.getId());
        task.setCas(caseFxToCase(taskFX.getCas()));
        task.setDeadLine(localDateToDate(taskFX.getDeadline()));
        task.setDesc(taskFX.getDesc());
        task.setEmployee(employeeFxToEmployee(taskFX.getEmployee()));
        task.setPriority(taskFX.getPriority());
        task.setStatus(taskFX.getStatus());
        return task;

    }
    public static TaskFX TaskToTaskFX(Task task){
        TaskFX taskFX = new TaskFX();
        taskFX.setCas(caseToCaseFx(task.getCas()));
        taskFX.setDeadline(dateToLocalDate(task.getDeadLine()));
        taskFX.setId(task.getId());
        taskFX.setDesc(task.getDesc());
        taskFX.setEmployee(employeeToEmployeeFX(task.getEmployee()));
        taskFX.setPriority(task.getPriority());
        taskFX.setStatus(task.getStatus());
        return taskFX;
    }


    public static Date localDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
