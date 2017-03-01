
package de.dortmund.fh.jung.myproject.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChaosUnitsDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = ChaosUnitsDatabaseHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "GIFTSOFCHAOS_DATABASE";

    public static final String TABLE_NAME = "unit";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE_PATH = "image_path";

    private static final String SQL_CREATE_UNIT_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMATRY KEY," + KEY_NAME
                    + " TEXT," + KEY_IMAGE_PATH + " TEXT" + ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ChaosUnitsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(SQL_CREATE_UNIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
