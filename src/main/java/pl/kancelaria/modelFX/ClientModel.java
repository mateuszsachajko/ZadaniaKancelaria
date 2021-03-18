package pl.kancelaria.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.dao.CaseDao;
import pl.kancelaria.database.dao.ClientDao;
import pl.kancelaria.database.dao.TaskDao;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.Converters;

import java.sql.SQLException;
import java.util.List;

public class ClientModel {

    ObjectProperty<ClientFX> clientFXObject = new SimpleObjectProperty<>(new ClientFX());
    ObjectProperty<ClientFX> clientFXObjectEdit = new SimpleObjectProperty<>(new ClientFX());
    ObservableList<ClientFX> clientsList = FXCollections.observableArrayList();

    public void init() throws AppExc {
        ClientDao clientDao = new ClientDao();
        List<Client> list = clientDao.queryForAll(Client.class);
        initClientsList(list);
        DbManager.closeConnectionSource();

    }
    public void saveInDb() throws AppExc {
        ClientDao clientDao = new ClientDao();
        Client client = Converters.clientFxToClient(this.getClientFXObject());
        clientDao.createOrUpdate(client);
        DbManager.closeConnectionSource();
        this.init();
    }
    public void edit() throws AppExc {
        ClientDao clientDao = new ClientDao();
        Client client = Converters.clientFxToClient(this.getClientFXObjectEdit());
        clientDao.createOrUpdate(client);
        DbManager.closeConnectionSource();
        this.init();

    }
    public void delete() throws AppExc, SQLException {
        ClientDao clientDao = new ClientDao();
        clientDao.deleteById(Client.class,this.clientFXObject.get().getId());
        DbManager.closeConnectionSource();
        CaseDao caseDao = new CaseDao();
        caseDao.deleteByColumn(Case.KLIENT,this.getClientFXObject().getId());
    }

    public void initClientsList(List<Client> clients){
        this.clientsList.clear();
        clients.forEach(client -> {
            this.clientsList.add(Converters.clientToClientFX(client));
        });
    }

    // getters and setters

    public ClientFX getClientFXObject() {
        return clientFXObject.get();
    }

    public ObjectProperty<ClientFX> clientFXObjectProperty() {
        return clientFXObject;
    }

    public void setClientFXObject(ClientFX clientFXObject) {
        this.clientFXObject.set(clientFXObject);
    }

    public ClientFX getClientFXObjectEdit() {
        return clientFXObjectEdit.get();
    }

    public ObjectProperty<ClientFX> clientFXObjectEditProperty() {
        return clientFXObjectEdit;
    }

    public void setClientFXObjectEdit(ClientFX clientFXObjectEdit) {
        this.clientFXObjectEdit.set(clientFXObjectEdit);
    }

    public ObservableList<ClientFX> getClientsList() {
        return clientsList;
    }

    public void setClientsList(ObservableList<ClientFX> clientsList) {
        this.clientsList = clientsList;
    }
}
