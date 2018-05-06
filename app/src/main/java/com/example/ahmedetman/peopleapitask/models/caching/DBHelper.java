package com.example.ahmedetman.peopleapitask.models.caching;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class DBHelper<T> extends OrmLiteSqliteOpenHelper {

    // Fields

    public static final String DB_NAME = "characters_db.db";
    private static final int DB_VERSION = 1;

    // Public methods

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {

            // Create Table with given table name with columnName
            TableUtils.createTableIfNotExists(cs, CharacterItem.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }


    /**
     * getting all the items inside the db
     * @param clazz
     * @return
     * @throws SQLException
     */
    public List getAll(Class clazz) throws SQLException {
        Dao<T, ?> dao = null;

        try {
            dao = getDao((Class<T>) clazz);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            if (dao != null) {
                return dao.queryForAll();
            } else
                return null;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * creating or updating new objects
     * @param obj
     * @return
     * @throws SQLException
     * @throws java.sql.SQLException
     */
    public Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    /**
     * updating specific object based on the id
     * @param itemId
     */
    public void update(int itemId) {
        UpdateBuilder<CharacterItem, Long> updateBuilder = null;
        try {
            updateBuilder = (UpdateBuilder<CharacterItem, Long>) DBHelper.this.getDao(CharacterItem.class).updateBuilder();
            // set the criteria like you would a QueryBuilder
            updateBuilder.where().eq("id", itemId);
            // update the value of your field(s)
            updateBuilder.updateColumnValue("isFavorite" /* column */, true /* value */);
            updateBuilder.update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

}