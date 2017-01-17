
package de.dortmund.fh.jung.myproject.mainview;

import javax.inject.Inject;

public final class MyPresenter implements Contract.Presenter{

    private Contract.View mView;

    @Inject
    MyPresenter() {
    }

    @Override
    public void handleClickEvent(final int id) {
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
