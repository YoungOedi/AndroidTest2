package de.dortmund.fh.jung.myproject.chaosview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.R;


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
        recyclerView = (RecyclerView) findViewById(R.id.chaosUnitRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UnitAdapter();
        recyclerView.setAdapter(adapter);

        getAppComponent().inject(this);
        presenter.bind(this);

        adapter.setData(presenter.getDummyUnitList());
    }

}
