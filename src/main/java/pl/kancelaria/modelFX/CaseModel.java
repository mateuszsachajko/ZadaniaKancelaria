package pl.kancelaria.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.dao.CaseDao;
import pl.kancelaria.database.dao.TaskDao;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.Converters;

import java.sql.SQLException;
import java.util.List;

public class CaseModel {

    private ObjectProperty<CaseFX> caseFXObjectProperty = new SimpleObjectProperty<>(new CaseFX());
    private ObjectProperty<CaseFX> caseFXObjectPropertyEdit = new SimpleObjectProperty<>(new CaseFX());
    private ObservableList<CaseFX> caseFXObservableList = FXCollections.observableArrayList();

    public void init() throws AppExc {
        CaseDao caseDao = new CaseDao();
        List<Case> caseList = caseDao.queryForAll(Case.class);
        initCaseList(caseList);
        DbManager.closeConnectionSource();
    }

    private void initCaseList(List<Case> caseList) {
        this.caseFXObservableList.clear();
        caseList.forEach(c->{
            CaseFX caseFX = Converters.caseToCaseFx(c);
            this.caseFXObservableList.add(caseFX);
        });
    }

    public void saveInDb() throws AppExc {
        CaseDao caseDao = new CaseDao();
        Case cas = Converters.caseFxToCase(this.getCaseFXObjectProperty());
        caseDao.createOrUpdate(cas);
        DbManager.closeConnectionSource();
        this.init();
    }
    public void edit() throws AppExc {
        CaseDao caseDao = new CaseDao();
        Case cas = Converters.caseFxToCase(this.getCaseFXObjectPropertyEdit());
        caseDao.createOrUpdate(cas);
        DbManager.closeConnectionSource();
        this.init();

    }
    public void delete() throws AppExc, SQLException {
        CaseDao caseDao = new CaseDao();
        caseDao.deleteById(Case.class, this.caseFXObjectProperty.get().getId());
        DbManager.closeConnectionSource();
        TaskDao taskDao = new TaskDao();
        taskDao.deleteByColumnName(Task.SPRAWA,this.getCaseFXObjectProperty().getId());
        this.init();

    }


    //getters and setters

    public CaseFX getCaseFXObjectProperty() {
        return caseFXObjectProperty.get();
    }

    public ObjectProperty<CaseFX> caseFXObjectPropertyProperty() {
        return caseFXObjectProperty;
    }

    public void setCaseFXObjectProperty(CaseFX caseFXObjectProperty) {
        this.caseFXObjectProperty.set(caseFXObjectProperty);
    }

    public CaseFX getCaseFXObjectPropertyEdit() {
        return caseFXObjectPropertyEdit.get();
    }

    public ObjectProperty<CaseFX> caseFXObjectPropertyEditProperty() {
        return caseFXObjectPropertyEdit;
    }

    public void setCaseFXObjectPropertyEdit(CaseFX caseFXObjectPropertyEdit) {
        this.caseFXObjectPropertyEdit.set(caseFXObjectPropertyEdit);
    }

    public ObservableList<CaseFX> getCaseFXObservableList() {
        return caseFXObservableList;
    }

    public void setCaseFXObservableList(ObservableList<CaseFX> caseFXObservableList) {
        this.caseFXObservableList = caseFXObservableList;
    }
}
