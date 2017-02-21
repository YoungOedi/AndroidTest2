
package de.dortmund.fh.jung.myproject.mainview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.dortmund.fh.jung.myproject.BaseActivity;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.R;
import de.dortmund.fh.jung.myproject.chaosview.ChaosActivity;
import de.dortmund.fh.jung.myproject.searchview.SearchActivity;

public class MainActivity extends BaseActivity implements Contract.View {

    @Inject
    Contract.Presenter presenter;
    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getAppComponent().inject(this);

        // Bind this view to presenter
        presenter.bind(this);

        findViewById(R.id.dashboard_container_cheesefinder).setOnClickListener(view -> {
            presenter.handleClickEvent(view.getId());
        });

        findViewById(R.id.dashboard_container_matze_meter).setOnClickListener(view -> {
            presenter.handleClickEvent(view.getId());
        });
    }

    public void goToSearchActivity() {
        Intent intent = new Intent(this.getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void goToChaosActivity() {
        Intent intent = new Intent(this.getApplicationContext(), ChaosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.handleClickEvent(id);
        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
