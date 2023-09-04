package com.example.clickcake.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "supplier_table")
public class Supplier {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "supplier_id")
    private int supplierId;

    @ColumnInfo(name = "supplier_name")
    private String supplierName;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "location")
    private String location;

    public Supplier(String supplierName, String description, String location) {
        this.supplierName = supplierName;
        this.description = description;
        this.location = location;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
