package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.dao.CaseDao;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.modelFX.CaseModel;
import pl.kancelaria.modelFX.TaskFX;
import pl.kancelaria.modelFX.TaskModel;
import pl.kancelaria.utils.*;

public class TasksController {

    private CaseModel caseModel;
    private TaskModel taskModel;

    private Task foundTask;

    @FXML
    public TreeView<String> taskCaseTreeView;

    @FXML
    public void initialize(){

        this.caseModel = new CaseModel();
        this.taskModel = new TaskModel();
        try {
            caseModel.init();
            taskModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.taskCaseTreeView.setRoot(this.caseModel.getRoot());
        this.taskCaseTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            public TreeCell<String> call(TreeView<String> p) {
                TreeCell<String> cell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String str, boolean empty) {
                        super.updateItem(str, empty);
                        if (!empty&&getTreeItem().isLeaf()) {
                            setText(str);
                            setContextMenu(createContextMenu());
                        } else if(!empty){
                            setText(str);
                        } else if(empty){
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    public void showAllTasks() {
        try {
            this.caseModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
      //  this.initialize();
    }
    @FXML
    public void showToDoTasks(ActionEvent actionEvent) {
        try {
            this.caseModel.sortByStatus(StatusENUM.Status.DO_ZROBIENIA);
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.taskCaseTreeView.setRoot(this.caseModel.getRoot());
    }
    @FXML
    public void showToVerify(ActionEvent actionEvent) {
        try {
            this.caseModel.sortByStatus(StatusENUM.Status.DO_WERYFIKACJI);
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.taskCaseTreeView.setRoot(this.caseModel.getRoot());
    }
    @FXML
    public void showToSign(ActionEvent actionEvent) {
        try {
            this.caseModel.sortByStatus(StatusENUM.Status.DO_PODPISU);
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
       this.taskCaseTreeView.setRoot(this.caseModel.getRoot());
    }
    @FXML
    public void showToRemind(ActionEvent actionEvent) {
        try {
            this.caseModel.sortByStatus(StatusENUM.Status.DO_PRZYPOMNIENIA);
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.taskCaseTreeView.setRoot(this.caseModel.getRoot());
    }

    private void findTaskByName(String caseName, String taskName){
        CaseDao caseDao = new CaseDao();
        try {
            caseDao.queryForAll(Case.class).forEach(c->{
                if(c.getDesc().equals(caseName)){
                    c.getTasks().forEach(task -> {
                        if(task.getDesc().equals(taskName)){
                            setFoundTask(task);
                        }
                    });
                }
            });
        } catch (AppExc appExc) {
            appExc.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }

    private ContextMenu createContextMenu(){
        ContextMenu cm = new ContextMenu();
        MenuItem finishTask = new MenuItem("Zakończ zadanie");
        MenuItem changeToVerify = new MenuItem("Do weryfikacji");
        MenuItem changeToSign = new MenuItem("Do podpisu");
        finishTask.setOnAction(event -> {
            String caseName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getParent().getValue();
            String taskName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getValue();
            findTaskByName(caseName,taskName);
            try {
                this.taskModel.editStatus(foundTask.getId(), StatusENUM.Status.ZAKONCZONE);
            } catch (AppExc appExc) {
                appExc.printStackTrace();
            }
        });
        changeToVerify.setOnAction(event -> {
            String caseName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getParent().getValue();
            String taskName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getValue();
            findTaskByName(caseName,taskName);
            try {
                this.taskModel.editStatus(foundTask.getId(), StatusENUM.Status.DO_WERYFIKACJI);
            } catch (AppExc appExc) {
                appExc.printStackTrace();
            }
        });
        changeToSign.setOnAction(event -> {
            String caseName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getParent().getValue();
            String taskName = this.taskCaseTreeView.getSelectionModel().getSelectedItem().getValue();
            findTaskByName(caseName,taskName);
            try {
                this.taskModel.editStatus(foundTask.getId(), StatusENUM.Status.DO_PODPISU);
            } catch (AppExc appExc) {
                appExc.printStackTrace();
            }
        });
        cm.getItems().addAll(finishTask,changeToVerify,changeToSign);
        return cm;
    }


    public Task getFoundTask() {
        return foundTask;
    }

    public void setFoundTask(Task foundTask) {
        this.foundTask = foundTask;
    }
    
}
