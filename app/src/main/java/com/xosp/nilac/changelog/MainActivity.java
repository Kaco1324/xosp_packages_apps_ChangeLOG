package com.xosp.nilac.changelog;
/*
 * Copyright (C) 2016 Calin Neamtu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;
import com.xosp.nilac.changelog.devices.*;
import com.xosp.nilac.changelog.util.CheckConnection;
import com.xosp.nilac.changelog.util.ProportionalImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_menu_activuty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(Color.rgb(39,174,96));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.xosp_contact_title, Snackbar.LENGTH_LONG).setActionTextColor(Color.rgb(39,174,96))
                        .setAction(R.string.xosp_contact_summary, new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri uriUrl = Uri.parse("mailto:xperiaopensourceproject@gmail.com");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setImageConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.slide_menu_activuty, menu);
        return true;
    }

    public void setImageConnection(){

        CheckConnection checkN = new CheckConnection();
        it.gmariotti.changelibs.library.view.ChangeLogRecyclerView changelogView =  (it.gmariotti.changelibs.library.view.ChangeLogRecyclerView) findViewById(R.id.changelog_view);
        ProportionalImageView noConnectionImage= (ProportionalImageView) findViewById(R.id.image);
        Toast toast=null;

        if(checkN.sendConnectionState(getApplicationContext())) {
            if(changelogView.getVisibility()==View.VISIBLE){
                Toast updated=null;
                toast = Toast.makeText(getApplicationContext(),R.string.generic_connection_state_true,Toast.LENGTH_LONG);
                toast.show();
            }
            changelogView.setVisibility(View.VISIBLE);
            noConnectionImage.setVisibility(View.GONE);
        }

        else {
            changelogView.setVisibility(View.GONE);
            noConnectionImage.setVisibility(View.VISIBLE);
            toast = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.check_connection) {
            setImageConnection();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        CheckConnection checkN = new CheckConnection();
        Toast connectState=null;

        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);

        //Maintainers stuff
        if(id == R.id.xosp_maintainers) {
            Intent intent = new Intent(this, MaintainersActivity.class);
            startActivity(intent);
        }

        //Devices stuff
        if (id == R.id.xosp_weeklies_bacon) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Bacon.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_bullhead) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Bullhead.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_d802) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, D802.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_hammerhead) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Hammerhead.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_mako) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Mako.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_oneplus3) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Oneplus3.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        }else if (id == R.id.xosp_weeklies_z008) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Z008.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        } else if (id == R.id.xosp_weeklies_z00a) {
            if(checkN.sendConnectionState(getApplicationContext())) {
                Intent intent = new Intent(this, Z00A.class);
                startActivity(intent);
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_true, Toast.LENGTH_LONG);
                connectState.show();
            }
            else{
                connectState = Toast.makeText(getApplicationContext(), R.string.generic_connection_state_false, Toast.LENGTH_LONG);
                connectState.show();
            }
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
