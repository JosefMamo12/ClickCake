package com.example.clickcake;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickcake.adapters.SupplierAdapter;
import com.example.clickcake.database.Supplier;
import com.example.clickcake.viewModel.ClickCakeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ClickCakeViewModel clickCakeViewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SupplierAdapter adapter = new SupplierAdapter();
        recyclerView.setAdapter(adapter);

        clickCakeViewModel = new ViewModelProvider(this).get(ClickCakeViewModel.class);
        clickCakeViewModel.getAllSuppliers().observe
                (this, new Observer<List<Supplier>>() {
                    @Override
                    public void onChanged(List<Supplier> suppliers) {
                        Toast.makeText(MainActivity.this, "Toasted", Toast.LENGTH_SHORT).show();
                        adapter.setSuppliers(suppliers);
                    }
                });


    }
}