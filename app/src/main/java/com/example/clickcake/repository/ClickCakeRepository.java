package com.example.clickcake.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.clickcake.database.ClickCakeDatabase;
import com.example.clickcake.database.DatabaseDao;
import com.example.clickcake.database.Supplier;

import java.util.List;

public class ClickCakeRepository {
    private final DatabaseDao databaseDao;
    private final LiveData<List<Supplier>> allSuppliers;

    public ClickCakeRepository(Application application) {
        ClickCakeDatabase database = ClickCakeDatabase.getInstance(application);
        databaseDao = database.databaseDao();
        allSuppliers = databaseDao.getAllSuppliers();
        System.out.println(allSuppliers.getValue());
    }

    public void insertSupplier(Supplier supplier) {
        new InsertSupplierAsyncTask(databaseDao).execute(supplier);

    }

    public void updateSupplier(Supplier supplier) {
        new UpdateSupplierAsyncTask(databaseDao).execute(supplier);
    }

    public void deleteSupplier(Supplier supplier) {
        new DeleteSupplierAsyncTask(databaseDao).execute(supplier);

    }

    public void deleteAllSuppliers() {
        new DeleteAllSupplierAsyncTask(databaseDao).execute();

    }

    public LiveData<List<Supplier>> getAllSuppliers() {
        return allSuppliers;
    }

    private static class InsertSupplierAsyncTask extends AsyncTask<Supplier, Void, Void> {
        private final DatabaseDao databaseDao;

        private InsertSupplierAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(Supplier... suppliers) {
            databaseDao.insertSupplier(suppliers[0]);
            return null;
        }
    }

    private static class DeleteSupplierAsyncTask extends AsyncTask<Supplier, Void, Void> {
        private final DatabaseDao databaseDao;

        private DeleteSupplierAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(Supplier... suppliers) {
            databaseDao.deleteSupplier(suppliers[0]);
            return null;
        }
    }

    private static class UpdateSupplierAsyncTask extends AsyncTask<Supplier, Void, Void> {
        private final DatabaseDao databaseDao;

        private UpdateSupplierAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(Supplier... suppliers) {
            databaseDao.updateSupplier(suppliers[0]);
            return null;
        }
    }

    private static class DeleteAllSupplierAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DatabaseDao databaseDao;

        private DeleteAllSupplierAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseDao.deleteAllSuppliers();
            return null;
        }
    }
}
