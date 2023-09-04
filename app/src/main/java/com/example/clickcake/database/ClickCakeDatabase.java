package com.example.clickcake.database;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Supplier.class}, version = 1)
public abstract class ClickCakeDatabase extends RoomDatabase {

    private static ClickCakeDatabase instance;

    public abstract DatabaseDao databaseDao();

    public static synchronized ClickCakeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ClickCakeDatabase.class, "clickcake_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("Room", "Database onCreate callback triggered");
            new PopulateDbAsyncTask(instance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DatabaseDao databaseDao;

        private PopulateDbAsyncTask(ClickCakeDatabase clickCakeDatabase) {
            this.databaseDao = clickCakeDatabase.databaseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("Room", "PopulateDbAsyncTask doInBackground called");
            databaseDao.insertSupplier(new Supplier("Josef Mamo", "Cheap Cakes", "Rosh Hayin"));
            databaseDao.insertSupplier(new Supplier("Lian Mamo", "Expensive Cakes", "Rosh Hayin"));
            databaseDao.insertSupplier(new Supplier("Eden Moshe", "Fancy Cakes", "Kinneret"));
            return null;
        }
    }
}
