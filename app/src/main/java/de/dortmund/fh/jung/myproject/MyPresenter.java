
package de.dortmund.fh.jung.myproject;

import android.view.View;

import javax.inject.Inject;

/**
 * Created by hendrikjung on 29.12.16.
 */

public final class MyPresenter implements Contract.Presenter{

    private Contract.View mView;

    @Inject
    MyPresenter() {
    }

    @Override
    public void handleClickEvent() {
        mView.setText("Hi. Presenter told me to.");
    }

    @Override
    public void bind(Contract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView=null;
    }
}
