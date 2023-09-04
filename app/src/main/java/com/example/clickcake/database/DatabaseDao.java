package com.example.clickcake.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Insert
    void insertSupplier(Supplier supplier);

    @Update
    void updateSupplier(Supplier supplier);

    @Delete
    void deleteSupplier(Supplier supplier);

    @Query("Delete FROM supplier_table")
    void deleteAllSuppliers();

    @Query("SELECT * FROM supplier_table ORDER BY supplier_name DESC")
    LiveData<List<Supplier>> getAllSuppliers();

}
