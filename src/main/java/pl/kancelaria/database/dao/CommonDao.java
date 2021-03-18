package pl.kancelaria.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.omg.CORBA.portable.ApplicationException;
import pl.kancelaria.database.DbManager;
import pl.kancelaria.database.models.BaseModel;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao(){

        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws AppExc {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws AppExc {
        Dao<T,I> dao = getDao((Class<T>)baseModel.getClass());
        try {
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            DialogUtils.dialogError(e.getMessage());
            throw new AppExc("Blad przy odswiezeniu bazy danych");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel,I> void delete(BaseModel baseModel) throws AppExc {
        try {
            Dao<T,I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
            throw new AppExc("Nie mozna usunac wpisu");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel,I> void deleteById(Class<T> cls, Integer id) throws AppExc {
        try {
            Dao<T,I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
            throw new AppExc("Nie mozna usunac wpisu");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void findById(Class<T> cls, Integer id) throws AppExc {
        try {
            Dao<T,I> dao = getDao(cls);
            dao.queryForId((I) id);
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
            throw new AppExc("Nie mozna znalezc wpisu");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws AppExc {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
            throw new AppExc("nie znaleziono tabeli");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> Dao<T,I> getDao(Class<T> cls) throws AppExc {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException throwables) {
            DialogUtils.dialogError(throwables.getMessage());
            throw new AppExc("Nie mozna uzyskac dostepu do bazy danych");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws AppExc {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    private void closeDbConnection(){
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            DialogUtils.dialogError(e.getMessage());
        }
    }
}
