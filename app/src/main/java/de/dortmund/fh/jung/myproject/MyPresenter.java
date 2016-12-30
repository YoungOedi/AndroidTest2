
package de.dortmund.fh.jung.myproject;

import android.view.View;

import javax.inject.Inject;

/**
 * Created by hendrikjung on 29.12.16.
 */

public final class MyPresenter implements Contract.Presenter{

    private final Contract.View mView;

    @Inject
    MyPresenter(Contract.View view) {
       mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void handleClickEvent() {
        mView.setText("Hi. Presenter told me too.");
    }
}
