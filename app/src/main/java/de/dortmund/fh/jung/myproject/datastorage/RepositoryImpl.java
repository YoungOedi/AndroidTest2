
package de.dortmund.fh.jung.myproject.datastorage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.chaosview.Unit;

public class RepositoryImpl implements Repository {
    ChaosUnitsDatabaseHelper helper;
    SQLiteDatabase database;

    @Inject
    public RepositoryImpl(ChaosUnitsDatabaseHelper helper) {
        this.helper = helper;
    }

    @Override
    public long saveUnit(Unit unit) {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_NAME, unit.getName());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_IMAGE_PATH, unit.getPhotoFilePath());
        return database.insertOrThrow(ChaosUnitsDatabaseHelper.TABLE_UNITS, null, contentValues);
    }

    @Override
    public void getUnit(int id) {

    }

    @Override
    public void clearAll() {

    }
}
