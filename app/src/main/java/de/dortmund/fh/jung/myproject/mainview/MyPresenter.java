
package de.dortmund.fh.jung.myproject.mainview;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.R;
import de.dortmund.fh.jung.myproject.searchview.SearchActivity;

public final class MyPresenter implements Contract.Presenter {

    private Contract.View view;

    @Inject
    MyPresenter() {
    }

    @Override
    public void handleClickEvent(final int id) {
        switch(id){
            case R.id.dashboard_container_cheesefinder: view.goToSearchActivity(); break;
            case R.id.dashboard_container_matze_meter: view.goToChaosActivity(); break;
            default: view.setText("Hi. Presenter told me to.");
        }
    }

    @Override
    public void bind(Contract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }
}
