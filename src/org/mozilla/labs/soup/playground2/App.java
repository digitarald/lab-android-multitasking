
package org.mozilla.labs.soup.playground2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class App extends Activity {

    private static final String TAG = App.class.getSimpleName();

    private String appTitle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);

        Home.registerApp(this);

        Uri data = getIntent().getData();

        appTitle = "No App!";

        if (data != null) {
            appTitle = data.toString();
        }

        ((TextView)findViewById(R.id.appView)).setText(appTitle);
        setTitle(appTitle);

        Log.d(TAG, "onCreate " + appTitle);
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause " + appTitle);
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume " + appTitle);
    }

    protected void onDestroy() {
        super.onDestroy();

        Home.unregisterApp(this);

        Log.d(TAG, "onDestroy " + appTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Home");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            default:
                startActivity(new Intent(App.this, Home.class));
                return true;
        }

    }
}
