package com.appsnipp.education;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.AttributeSet;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatDelegate;
        import androidx.coordinatorlayout.widget.CoordinatorLayout;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.navigation.fragment.NavHostFragment;
        import androidx.navigation.ui.NavigationUI;

        import com.appsnipp.education.DataBase.AppDataBase;
        import com.appsnipp.education.Entity.User;
        import com.appsnipp.education.databinding.ActivityMainBinding;
        import com.appsnipp.education.ui.Events.ListEventsFront;
        import com.appsnipp.education.ui.Forum.Forum;
        import com.appsnipp.education.ui.helpers.BottomNavigationBehavior;
        import com.appsnipp.education.ui.helpers.DarkModePrefManager;
        import com.appsnipp.education.ui.login.SigninPage;
        import com.appsnipp.education.ui.login.WelcomePage;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DarkModePrefManager darkModePrefManager;
    ActivityMainBinding binding;
    NavHostFragment navHostFragment;
    private BottomNavigationView bottomNavigationView;
    private AppDataBase appDatabase;


    private TextView navHeaderUsername;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        setAppTheme();
        setSupportActionBar(binding.appBarMain.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        //region REGION OLD METHOD BOTTOM NAVIGATION
//        binding.appBarMain.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.appBarMain.bottomNavigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());
//        binding.appBarMain.bottomNavigationView.setSelectedItemId(R.id.navigationHome);
        //endregion

        setupNavigation();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0); // Get the first header view
        navHeaderUsername = headerView.findViewById(R.id.navHeaderUsername);

        // Retrieve user ID from SharedPreferences
        int userId = getUserID();
        String firstName = getFirstName();
        String lastName = getLastName();

        if (userId != -1 && firstName != null && lastName != null) {
            // User details are available
            String welcomeMessage =  firstName + " " + lastName ;
            navHeaderUsername.setText(welcomeMessage);
            System.out.println(welcomeMessage);
        } else {
            Intent intent = new Intent(this, SigninPage.class);
            startActivity(intent);
            finish(); // Close this activity to prevent going back to it with the back button
        }


    }
    private int getUserID() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getInt("user_id", -1);
    }
    private String getFirstName() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_firstname", null);
    }

    private String getLastName() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_lastname", null);
    }
    private String getEmail() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_email", null);
    }


    private void setAppTheme() {
        darkModePrefManager = new DarkModePrefManager(this);
        boolean isDarkModeEnabled = darkModePrefManager.isNightMode();
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

    }

    private void setupNavigation() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.appBarMain.bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
        }
    }


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void clearUserSession() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {


        } else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_logout) {
            // Clear the user session and redirect to the login page
            clearUserSession();
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, WelcomePage.class); // Replace WelcomePage with the actual welcome page class
            startActivity(intent);
            finish(); // Close this activity to prevent going back to it with the back button
        }


        else if (id == R.id.nav_dark_mode) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click

//            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//            finish();
//            overridePendingTransition(0, 0);

            toggleDarkMode();
        }

        // Fermez le tiroir de navigation (si nécessaire).
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // Redirigez vers l'activité ProfileSettings si l'option "Tools" est sélectionnée.
        if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, ProfileSettings.class);
            intent.putExtra("user_firstname", getFirstName());
            intent.putExtra("user_lastname", getLastName());
            intent.putExtra("user_email",getEmail());
            startActivity(intent);// Retournez true pour indiquer que l'item a été traité.
        }
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, Forum.class);
            startActivity(intent);
            return true; // Retournez true pour indiquer que l'item a été traité.
        }
        if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, ListEventsFront.class);
            startActivity(intent);
            return true; // Retournez true pour indiquer que l'item a été traité.
        }
        return true;

    }

    // Método para cambiar el estado del modo oscuro
    private void toggleDarkMode() {
        boolean isDarkModeEnabled = darkModePrefManager.isNightMode();
        darkModePrefManager.setDarkMode(!isDarkModeEnabled);
//        recreate();

        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(0, 0);

    }


}
