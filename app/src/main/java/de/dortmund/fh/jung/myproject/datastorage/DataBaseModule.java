
package de.dortmund.fh.jung.myproject.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Provides
    public static ChaosUnitsDatabaseHelper provideLocalDatabase(Context context) {
        ChaosUnitsDatabaseHelper helper = new ChaosUnitsDatabaseHelper(context);
        return helper;
    }

    @Provides
    public static Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }

}
