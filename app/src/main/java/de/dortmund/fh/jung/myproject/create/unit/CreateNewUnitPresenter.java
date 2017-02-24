
package de.dortmund.fh.jung.myproject.create.unit;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.chaosview.Unit;

public class CreateNewUnitPresenter implements CreateNewUnitContract.Presenter {

    CreateNewUnitContract.View view;
    Unit unit;

    @Inject
    public CreateNewUnitPresenter() {

    }

    @Override
    public void bind(CreateNewUnitContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void saveNewPhotoLocation(String filePath) {
        unit.setPhotoFilePath(filePath);
    }

    @Override
    public void createNewUnit() {

    }

    private void saveNewUnit() {

    }
}
