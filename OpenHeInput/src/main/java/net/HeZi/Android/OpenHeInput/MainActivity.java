/*
  * Copyright (c) 2015 Guilin Ouyang. All rights reserved.
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

package net.HeZi.Android.OpenHeInput;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.RequestConfiguration;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements net.HeZi.Android.HeInputLibrary.HeInput_Activation_Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Register this phone as a test device (must run before initialize/loadAd)
        RequestConfiguration config = new RequestConfiguration.Builder()
                .setTestDeviceIds(Arrays.asList("PASTE_THE_LONG_HEX_ID_HERE"))
                .build();
        MobileAds.setRequestConfiguration(config);

        MobileAds.initialize(this, initializationStatus -> {});
        AdView adView = findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem donateItem = menu.findItem(R.id.action_donate);
        View actionView = donateItem.getActionView();
        if (actionView != null) {
            actionView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://buymeacoffee.com/4dventure"));
                startActivity(intent);
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_licenses) {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle(R.string.action_licenses)
                    .setMessage(R.string.open_source_licenses)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    //OnFragmentInteractionListener
    public void onFragmentInteraction(Uri uri)
    {}

    @Override
    public String getHostApplicationName(){
        return getPackageName();
    }
}
