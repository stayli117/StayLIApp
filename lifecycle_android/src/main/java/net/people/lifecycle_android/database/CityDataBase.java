package net.people.lifecycle_android.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {DbCityBean.class}, version = 6)
public abstract class CityDataBase extends RoomDatabase {
    public abstract CityDao cityDao();

    private static CityDataBase INSTANCE;
    private static final Object sLock = new Object();

    private static final String TAG = "CityDataBase";

    private static Migration[] mMigrations;

    public static void addMIGRATION(Migration... migrations) {
        mMigrations = migrations;
    }

    public static CityDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CityDataBase.class, "dbcitybean")
                        .addMigrations(mMigrations)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Log.e(TAG, "onCreate: " + System.currentTimeMillis());
                            }

                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                super.onOpen(db);
                                Log.e(TAG, "onOpen: " + System.currentTimeMillis());
                            }
                        })
                        .build();
            }
            return INSTANCE;
        }
    }





}
