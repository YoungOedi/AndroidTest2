
package de.dortmund.fh.jung.myproject.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import de.dortmund.fh.jung.myproject.BuildConfig;

public class ChaosUnitsDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = ChaosUnitsDatabaseHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "GIFTSOFCHAOS_DATABASE";

    public static final String TABLE_UNITS = "unit";
    public static final String KEY_UNIT_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE_PATH = "image_path";
    public static final String KEY_GOD = "god";
    public static final String KEY_LESSER_GIFT_NUMBER = "lesser_gift_number";
    public static final String KEY_MIDDLE_GIFT_NUMBER = "middle_gift_number";
    public static final String KEY_GREATER_GIFT_NUMBER = "greater_gift_number";
    public static final String KEY_MASTERY_LEVEL = "mastery_level";

    private static final String STORAGE_DIRECTORY = "/storage/sdcard1";
    private static final String APP_PATH = "/Android/data/de.dortmund.fh.jung.myproject/";

    private static final String SQL_CREATE_UNIT_TABLE = "CREATE TABLE " + TABLE_UNITS + "("
            + KEY_UNIT_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_GOD + " TEXT,"
            + KEY_LESSER_GIFT_NUMBER + " INTEGER,"
            + KEY_MIDDLE_GIFT_NUMBER + " INTEGER,"
            + KEY_GREATER_GIFT_NUMBER + " INTEGER,"
            + KEY_MASTERY_LEVEL + " INTEGER,"
            + KEY_IMAGE_PATH + " TEXT" + ")";

    public static final String TABLE_GIFTS = "gift_table";

    private static final String SQL_CREATE_GIFT_TABLE = "CREATE TABLE " + TABLE_GIFTS
            + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, god TEXT, number INTEGER, type TEXT )";
    private static final String SQL_DELETE_UNIT_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_UNITS;
    private static final String SQL_DELETE_GIFT_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_GIFTS;

    public ChaosUnitsDatabaseHelper(Context context) {
        super(context,
                new File(STORAGE_DIRECTORY).exists() ? STORAGE_DIRECTORY + APP_PATH + DATABASE_NAME + ".db"
                        : Environment.getExternalStorageDirectory().getPath()
                                + APP_PATH + DATABASE_NAME + ".db",
                null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Deleting all Tables");
            db.execSQL(SQL_DELETE_UNIT_ENTRIES);
            db.execSQL(SQL_DELETE_GIFT_ENTRIES);
        }
        Log.d(TAG, "Creating new Tables");
        db.execSQL(SQL_CREATE_UNIT_TABLE);
        db.execSQL(SQL_CREATE_GIFT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "Deleting all Tables");
        db.execSQL(SQL_DELETE_UNIT_ENTRIES);
        db.execSQL(SQL_DELETE_GIFT_ENTRIES);
        onCreate(db);
    }
}
