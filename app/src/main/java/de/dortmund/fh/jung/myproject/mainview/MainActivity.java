
package de.dortmund.fh.jung.myproject.mainview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.di.DaggerMyComponent;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.R;

public class MainActivity extends BaseActivity implements Contract.View {

    @Inject
    Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inject Context of ParentApplication per BaseActivity
        //Eigentlich per Interface umsetzen!!
        getAppComponent().inject(this);

        // Create the presenter
        DaggerMyComponent.builder()
                .presenterModule(new PresenterModule())
                .build()
                .inject(this);

        //Bind this view to presenter
        mPresenter.bind(this);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.handleClickEvent();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        mPresenter.handleClickEvent();
        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setText(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

}
