package pl.kancelaria.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.AppExc;

import java.sql.SQLException;

public class TaskDao extends CommonDao {

    public TaskDao(){
        super();
    }

    public void deleteByColumnName(String colName, int id) throws AppExc, SQLException {
        Dao<Task, Object> dao = getDao(Task.class);
        DeleteBuilder<Task, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(colName,id);
        dao.delete(deleteBuilder.prepare());
    }
}
