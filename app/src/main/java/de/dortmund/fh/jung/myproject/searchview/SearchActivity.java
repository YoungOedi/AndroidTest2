
package de.dortmund.fh.jung.myproject.searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.R;

public class SearchActivity extends BaseActivity implements SearchContract.View {
    @Inject
    SearchContract.Presenter mPresenter;

    protected EditText mQueryEditText;
    protected Button mSearchButton;
    private CheeseAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    public String getSearchFieldText() {
        return null;
    }

    @Override
    public void showResult(List<String> result) {
        if (result.isEmpty()) {
            Toast.makeText(this, R.string.nothing_found, Toast.LENGTH_SHORT).show();
            mAdapter.setCheeses(Collections.<String>emptyList());
        } else {
            mAdapter.setCheeses(result);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheeses);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mAdapter = new CheeseAdapter());

        mQueryEditText = (EditText) findViewById(R.id.query_edit_text);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        List<String> cheeses = Arrays.asList(getResources().getStringArray(R.array.cheeses));
        getAppComponent().inject(this);
        mPresenter.bind(this);
        mPresenter.initList(cheeses);

    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
