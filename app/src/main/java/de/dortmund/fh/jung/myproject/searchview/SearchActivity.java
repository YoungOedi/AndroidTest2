
package de.dortmund.fh.jung.myproject.searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity implements SearchContract.View {
    @Inject
    SearchContract.Presenter mPresenter;

    private Disposable mDisposable;

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
            mAdapter.setCheeses(Collections.emptyList());
        } else {
            mAdapter.setCheeses(result);
        }
    }

    private Observable<String> createButtonClickObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) {
                mSearchButton.setOnClickListener(view ->
                        emitter.onNext(mQueryEditText.getText().toString()));
                emitter.setCancellable(() ->
                        mSearchButton.setOnClickListener(null));
            }
        });
    }

    private Observable<String> createButtonClickObservableLONGVERSION() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) {
                mSearchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        emitter.onNext(mQueryEditText.getText().toString());
                    }
                });
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mSearchButton.setOnClickListener(null);
                    }
                });
            }
        });
    }

    private Observable<String> createTextChangeObservable() {
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        emitter.onNext(s.toString());
                    }
                };
                mQueryEditText.addTextChangedListener(watcher);

                emitter.setCancellable(() ->
                        mQueryEditText.removeTextChangedListener(watcher));
            }
        });

        return textChangeObservable.filter((query) ->
                query.length() > 1
        ).debounce(650, TimeUnit.MILLISECONDS);
    }

    private Observable<String> createTextChangeObservableLONGVERSION() {
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        emitter.onNext(s.toString());
                    }
                };
                mQueryEditText.addTextChangedListener(watcher);

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mQueryEditText.removeTextChangedListener(watcher);
                    }
                });
            }
        });

        return textChangeObservable.filter(new Predicate<String>() {
            @Override
            public boolean test(String query) throws Exception {
                return query.length() > 1;
            }
        }).debounce(500, TimeUnit.MILLISECONDS);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    @Override
    public void onStart() {
        super.onStart();
        Observable<String> searchTextObservable = createButtonClickObservable();
        Observable<String> textInputObservable = createTextChangeObservable();
        Observable<String> searchInputObservable = Observable.merge(searchTextObservable, textInputObservable);

        mDisposable = searchInputObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s ->
                        showProgressBar())
                .observeOn(Schedulers.io())
                .map(query ->
                        mPresenter.search(query))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<String> result) -> {
                    hideProgressBar();
                    showResult(result);
                });
    }

    private void onStartLONGVERSION() {
        super.onStart();
        Observable<String> searchTextObservable = createButtonClickObservable();
        Observable<String> textInputObservable = createTextChangeObservable();
        Observable<String> searchInputObservable = Observable.merge(searchTextObservable, textInputObservable);

        mDisposable = searchInputObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showProgressBar();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String query) {
                        return mPresenter.search(query);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> result) throws Exception {
                        hideProgressBar();
                        showResult(result);
                    }
                });
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
