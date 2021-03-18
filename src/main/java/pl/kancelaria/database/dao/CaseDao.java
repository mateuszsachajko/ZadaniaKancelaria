package pl.kancelaria.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.AppExc;

import java.sql.SQLException;

public class CaseDao extends CommonDao{
    public CaseDao(){
        super();
    }
    public void deleteByColumn(String colName, int id) throws AppExc, SQLException {
        Dao<Case, Object> dao = getDao(Case.class);
        DeleteBuilder<Case, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(colName,id);
        dao.delete(deleteBuilder.prepare());
    }
}
