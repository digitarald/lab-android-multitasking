
package org.mozilla.labs.soup.playground2;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Home extends Activity implements OnClickListener, OnItemSelectedListener {

    private static final String TAG = Home.class.getSimpleName();

    private static ArrayList<App> apps = new ArrayList<App>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button)findViewById(R.id.button1)).setOnClickListener(this);
        ((Button)findViewById(R.id.button2)).setOnClickListener(this);
        ((Button)findViewById(R.id.button3)).setOnClickListener(this);

        Spinner spinner = (Spinner)findViewById(R.id.list);

        String[] list = new String[apps.size() + 1];

        list[0] = "Switch to app …";

        int step = 0;
        for (Iterator<App> i = apps.iterator(); i.hasNext();) {
            App current = (App)i.next();
            step = step + 1;

            list[step] = (String)current.getTitle();

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    public static void registerApp(App app) {
        if (!apps.contains(app)) {
            apps.add(app);
        }
    }

    public static void unregisterApp(App app) {
        if (apps.contains(app)) {
            apps.remove(app);
        }
    }

    public void onClick(View v) {

        Intent intent = new Intent(Home.this, App.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        switch (v.getId()) {
            case R.id.button1:

                intent.setData(Uri.parse("http://app1.com"));
                startActivity(intent);

                break;

            case R.id.button2:

                intent.setData(Uri.parse("http://app2.com"));
                startActivity(intent);

                break;

            case R.id.button3:

                intent.setData(Uri.parse("http://app3.com"));
                startActivity(intent);

                break;
        }

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Log.d(TAG, "Selected " + position);

        if (position < 1) {
            return;
        }

        App app = apps.get(position - 1);

        Log.d(TAG, "… " + app.getTitle());

        Intent intent = app.getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

        startActivity(intent);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
}
