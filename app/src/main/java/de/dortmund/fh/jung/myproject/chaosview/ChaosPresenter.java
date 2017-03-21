
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.datastorage.Repository;

public class ChaosPresenter implements ChaosContract.Presenter {

    private ChaosContract.View view;
    Repository repository;

    @Inject
    public ChaosPresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void goToNewUnitActivity() {
        view.changeViewToNewUnitActivity();
    }

    @Override
    public List<Unit> provideUnitList() {
        return repository.getAllUnits();
    }

    @Override
    public void removeUnit(final int id) {
        repository.deleteUnit(id);
    }

    @Override
    public void bind(ChaosContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void accept(final Unit unit) throws Exception {
        removeUnit(unit.getId());
    }
}
