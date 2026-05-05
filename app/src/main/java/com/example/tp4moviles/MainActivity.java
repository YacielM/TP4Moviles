package com.example.tp4moviles;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp4moviles.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    public static ArrayList<com.example.tp4moviles.model.Producto> productos = new ArrayList<>();

    static {
        productos.add(new com.example.tp4moviles.model.Producto("001", "Leche", 2500));
        productos.add(new com.example.tp4moviles.model.Producto("002", "Azúcar 1kg", 1200));
        productos.add(new com.example.tp4moviles.model.Producto("003", "Frasco de Café", 6000));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        if (binding.appBarMain.fab != null) {
            binding.appBarMain.fab.setOnClickListener(view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setAnchorView(R.id.fab).show());
        }

        // NavHostFragment y NavController
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();

        // Usamos binding.navView y binding.drawerLayout (el layout debe contenerlos)
        NavigationView navigationView = binding.navView;
        androidx.drawerlayout.widget.DrawerLayout drawerLayout = binding.drawerLayout;

        // AppBarConfiguration con los top-level destinations
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.cargarFragment,
                R.id.listaFragment,
                R.id.nav_transform,
                R.id.nav_reflow,
                R.id.nav_slideshow,
                R.id.nav_settings
        ).setOpenableLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // 1) Primero sincronizamos NavigationView con NavController para que NavigationUI maneje la navegación
        NavigationUI.setupWithNavController(navigationView, navController);

        // 2) Luego interceptamos únicamente el click del item "Salir" sin sobrescribir el listener global
        navigationView.getMenu().findItem(R.id.nav_salir).setOnMenuItemClickListener(menuItem -> {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Estás seguro que querés cerrar la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .setCancelable(false)
                    .show();

            drawerLayout.closeDrawers();
            return true;
        });

        // Bottom navigation (id en content_main debe ser bottomNavView)
        BottomNavigationView bottomNavigationView = binding.appBarMain.contentMain.bottomNavView;
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // No inflamos overflow si el drawer está presente (con el layout que usamos el drawer siempre existe)
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Manejo de Settings desde overflow (si existe)
        if (item.getItemId() == R.id.nav_settings) {
            NavController navController = androidx.navigation.Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_settings);
            return true;
        }

        // Manejo de Salir desde overflow
        if (item.getItemId() == R.id.nav_salir) {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Estás seguro que querés cerrar la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .setCancelable(false)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = androidx.navigation.Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}
