package com.example.clickcake.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.clickcake.database.Supplier;
import com.example.clickcake.repository.ClickCakeRepository;

import java.util.List;

public class ClickCakeViewModel extends AndroidViewModel {
    private ClickCakeRepository repository;
    private LiveData<List<Supplier>> allSuppliers;

    public ClickCakeViewModel(@NonNull Application application) {
        super(application);
        repository = new ClickCakeRepository(application);
        allSuppliers = repository.getAllSuppliers();
    }

    public void insert(Supplier supplier) {
        repository.insertSupplier(supplier);
    }

    public void update(Supplier supplier) {
        repository.updateSupplier(supplier);
    }

    public void delete(Supplier supplier) {
        repository.deleteSupplier(supplier);
    }

    public void deleteAllSuppliers() {
        repository.deleteAllSuppliers();
    }

    public LiveData<List<Supplier>> getAllSuppliers(){
        return allSuppliers;
    }
}
