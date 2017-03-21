
package de.dortmund.fh.jung.myproject.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.chaosview.Gift;
import de.dortmund.fh.jung.myproject.chaosview.Unit;

public class RepositoryImpl implements Repository {

    private static final String TAG = RepositoryImpl.class.getSimpleName();

    private ChaosUnitsDatabaseHelper helper;
    private SQLiteDatabase database;

    private static final String SQL_GET_All_UNITS = "SELECT * FROM " + ChaosUnitsDatabaseHelper.TABLE_UNITS;

    @Inject
    public RepositoryImpl(ChaosUnitsDatabaseHelper helper) {
        this.helper = helper;
    }

    @Override
    public long saveUnit(Unit unit) {
        database = helper.getWritableDatabase();
        Log.d(TAG, "Database found at: " + database.getPath());
        ContentValues contentValues = new ContentValues();
        if (unit.getId() > 0) {
            contentValues.put(ChaosUnitsDatabaseHelper.KEY_UNIT_ID, unit.getId());
        }
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_NAME, unit.getName());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_GOD, unit.getGod().toString());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_LESSER_GIFT_NUMBER, unit.getNumberOfLesserGifts());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_MIDDLE_GIFT_NUMBER, unit.getNumberOfMiddleGifts());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_GREATER_GIFT_NUMBER, unit.getNumberOfGreaterGifts());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_IMAGE_PATH, unit.getPhotoFilePath());
        contentValues.put(ChaosUnitsDatabaseHelper.KEY_MASTERY_LEVEL, unit.getMasteryLevel());
        long id = database.insertOrThrow(ChaosUnitsDatabaseHelper.TABLE_UNITS, null, contentValues);
        database.close();
        return id;
    }

    @Override
    public List<Unit> getAllUnits() {
        database = helper.getReadableDatabase();
        List<Unit> units = new ArrayList<>();

        try (Cursor cursor = database.rawQuery(SQL_GET_All_UNITS, null)){
            if (cursor != null && cursor.moveToFirst()) {
                readAndAddUnitToList(cursor, units);
            }
            while (cursor.moveToNext()){
                readAndAddUnitToList(cursor, units);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return units;
    }

    private void readAndAddUnitToList(Cursor cursor, List<Unit> units) {
        int id, masteryLevel;
        String name, photoFilePath;
        God god;
        id = cursor.getInt(cursor.getColumnIndex(ChaosUnitsDatabaseHelper.KEY_UNIT_ID));
        name = cursor.getString(cursor.getColumnIndex(ChaosUnitsDatabaseHelper.KEY_NAME));
        god = God.valueOf(cursor.getString(cursor.getColumnIndex(ChaosUnitsDatabaseHelper.KEY_GOD)));
        photoFilePath = cursor.getString(cursor.getColumnIndex(ChaosUnitsDatabaseHelper.KEY_IMAGE_PATH));
        masteryLevel = cursor.getInt(cursor.getColumnIndex(ChaosUnitsDatabaseHelper.KEY_MASTERY_LEVEL));
        units.add(new Unit(id, name, god, photoFilePath, masteryLevel));
    }

    @Override
    public Gift getGift(God god, int diceRoll) {
        return null;
    }

    @Override
    public void clearAll() {

    }

    @Override
    public void deleteUnit(int id) {
        String[] arguments = {
                String.valueOf(id)
        };
        String whereClause = ChaosUnitsDatabaseHelper.KEY_UNIT_ID + " = ?";
        database = helper.getWritableDatabase();
        int out = database.delete(ChaosUnitsDatabaseHelper.TABLE_UNITS, whereClause, arguments);
        database.close();
        Log.d(TAG, out >= 1? "One or more Units were deleted":"Deleting not successful");
    }
}
