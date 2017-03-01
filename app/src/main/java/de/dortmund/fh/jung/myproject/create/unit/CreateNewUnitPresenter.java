
package de.dortmund.fh.jung.myproject.create.unit;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.chaosview.Unit;

public class CreateNewUnitPresenter implements CreateNewUnitContract.Presenter {

    CreateNewUnitContract.View view;
    Unit unit;
    int[][] highlightedViews;

    @Inject
    public CreateNewUnitPresenter() {
        unit = new Unit();
        highlightedViews = new int[4][];
        highlightedViews[0] = new int[4];
        highlightedViews[1] = new int[3];
        highlightedViews[2] = new int[3];
        highlightedViews[3] = new int[3];
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

    @Override
    public void handleClickEvent(final int row, final int column) {
        removeHighlightsInARow(row);
        highlightedViews[row][column] = 1;
        view.updateHighlights(row, highlightedViews[row]);
        this.setUnitStats(row, column);
    }

    @Override
    public boolean isHighlighted(final int row, final int column) {
        if (highlightedViews[row][column] == 1) {
            return true;
        }
        return false;
    }

    private void removeHighlightsInARow(final int row) {
        for(int c=0; c<highlightedViews[row].length;c++){
            highlightedViews[row][c]=0;
        }
    }

    private void setUnitStats(final int row, final int column) {

    }

    private void saveNewUnit() {

    }
}
