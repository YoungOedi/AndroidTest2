
package de.dortmund.fh.jung.myproject.chaosview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.R;
import de.dortmund.fh.jung.myproject.create.unit.CreateNewUnitActivity;

public class ChaosActivity extends BaseActivity implements ChaosContract.View {

    @Inject
    ChaosContract.Presenter presenter;

    private UnitAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaos);

        this.initializeRecyclerView();

        getAppComponent().inject(this);
        presenter.bind(this);

        this.setOnClickListeners();

        adapter.setData(presenter.getUnitList());
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.chaosUnitRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UnitAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListeners() {
        FloatingActionButton b = (FloatingActionButton) findViewById(R.id.create_new_unit_fab);
        b.setOnClickListener(view -> presenter.goToNewUnitActivity());
    }

    @Override
    public void changeViewToNewUnitActivity() {
        Intent intent = new Intent(this, CreateNewUnitActivity.class);
        startActivity(intent);
    }
}
