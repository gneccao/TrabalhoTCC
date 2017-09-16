package com.example.gnecco.trabalhotcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gnecco.trabalhotcc.about.AboutActivity;
import com.example.gnecco.trabalhotcc.product.RegisterActivity;
import com.example.gnecco.trabalhotcc.product.ListProductActivity;
import com.example.gnecco.trabalhotcc.login.LoginActivity;
import com.example.gnecco.trabalhotcc.makecall.MakeCallActivity;
import com.example.gnecco.trabalhotcc.maps.MapsActivity;
import com.example.gnecco.trabalhotcc.share.ShareActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvLogado;
    private final String KEY_APP_PREFERENCES = "login";
    private final String KEY_LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        final String prefUserName = intent.getStringExtra("USERNAME");

        //SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        //final String prefUserName = pref.getString(KEY_LOGIN, "");

        TextView tvLogado = (TextView)findViewById(R.id.tvLogado);
        final String userName = tvLogado.getText().toString();
        tvLogado.setText(userName + prefUserName);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navCadastro) {

            startActivity(new Intent(this,RegisterActivity.class));

        } else if (id == R.id.navListaDados) {

            startActivity(new Intent(this,ListProductActivity.class));

        } else if (id == R.id.navSobre) {

            startActivity(new Intent(this,AboutActivity.class));


        } else if (id == R.id.navMapa) {

            startActivity(new Intent(this,MapsActivity.class));

        } else if (id == R.id.navCompartilhar) {

            startActivity(new Intent(this,ShareActivity.class));

        } else if (id == R.id.navLigacao) {

            startActivity(new Intent(this,MakeCallActivity.class));

        } else if(id == R.id.navExit){
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Deslogar(View view) {
        getApplicationContext().getSharedPreferences(KEY_APP_PREFERENCES, 0).edit().clear().apply();

        startActivity(new Intent(this, LoginActivity.class));

    }
}
