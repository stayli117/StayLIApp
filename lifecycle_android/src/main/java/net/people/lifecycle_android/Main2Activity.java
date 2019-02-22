package net.people.lifecycle_android;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.people.lifecycle_android.database.CityDataBase;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        // 每次打开 先 检查数据库迁移问题
        CityDataBase.addMIGRATION(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5
                , new Migration(5, 6) {
                    @Override
                    public void migrate(SupportSQLiteDatabase database) {
                        // Since we didn't alter the table, there's nothing else to do here.
                        // 插入新字段 news_con
                        database.execSQL("ALTER TABLE dbcitybean ADD COLUMN news_cont TEXT");
                    }
                });


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl, new TextFragment());
        transaction.commit();

    }

    /**
     * 数据库升级
     * 1. 更改版本号
     * 2. 实现migration
     * <p>
     * 从版本1 到2 的升级
     */
    private final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            // 插入新字段 city_name

            database.execSQL("ALTER TABLE dbcitybean ADD COLUMN city_name TEXT");

        }
    };
    private final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            // 插入新字段 last_update

            database.execSQL("ALTER TABLE dbcitybean ADD COLUMN last_update TEXT");

        }
    };

    private final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            // 插入新字段 news_id
            database.execSQL("ALTER TABLE dbcitybean ADD COLUMN news_id INTEGER");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl, new TextFragment());
            transaction.commit();

        }
    };
    private static final String TAG = "Main2Activity";
    private final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            // 插入新字段 news_con
            database.execSQL("ALTER TABLE dbcitybean ADD COLUMN news_con TEXT");
            Log.e(TAG, "migrate: ");


        }
    };


}
