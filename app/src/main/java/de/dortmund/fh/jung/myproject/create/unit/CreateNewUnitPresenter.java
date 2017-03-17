
package de.dortmund.fh.jung.myproject.create.unit;

import android.util.Log;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.chaosview.Unit;
import de.dortmund.fh.jung.myproject.datastorage.ChaosUnitsDatabaseHelper;
import de.dortmund.fh.jung.myproject.datastorage.Repository;

public class CreateNewUnitPresenter implements CreateNewUnitContract.Presenter {

    private static final String TAG = CreateNewUnitPresenter.class.getSimpleName();

    CreateNewUnitContract.View view;
    Unit unit;
    Repository repository;
    int[][] highlightedViews;
    int lesserGiftCounter;
    int middleGiftCounter;
    int greaterGiftCounter;

    @Inject
    public CreateNewUnitPresenter(Repository repository) {
        unit = new Unit();
        highlightedViews = new int[4][];
        highlightedViews[0] = new int[4];
        highlightedViews[1] = new int[3];
        highlightedViews[2] = new int[3];
        highlightedViews[3] = new int[3];

        this.repository = repository;
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
        // TODO
        Log.i(TAG, "Es wurden folgende Daten erfasst:");
        Log.i(TAG, "Unit Name: " + unit.getName());
        Log.i(TAG, "God: " + unit.getGod().name());
        Log.i(TAG, "Mastery Level: " + unit.getMasteryLevel());
        Log.i(TAG, "GreaterGifts: " + greaterGiftCounter);
        Log.i(TAG, "MiddleGifts: " + middleGiftCounter);
        Log.i(TAG, "LesserGifts: " + lesserGiftCounter);
        Log.i(TAG, "Image lies at: " + unit.getPhotoFilePath());

        try {
            long id = this.saveNewUnit();
            Log.d(TAG, "ID is : "+id);
        } catch (Exception e){
            e.printStackTrace();
        }
        view.switchToChaosActivity(1);
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

    @Override
    public void changeGod(God god) {
        if (god == God.NURGLE) {
            view.changeThemeToNurgle();
            unit.setGod(God.NURGLE);
            view.changeMasteryLevelVisibility(true);
            view.showNurgleMessage("Want a hug?");
        } else if (god == God.KHORNE) {
            view.changeThemeToKhorne();
            unit.setGod(God.KHORNE);
            view.changeMasteryLevelVisibility(false);
            view.showKhorneMessage("Blood for the Bloodgod");
        } else if (god == God.SLAANESCH) {
            view.changeThemeToSlaanesh();
            unit.setGod(God.SLAANESCH);
            view.changeMasteryLevelVisibility(true);
            view.showSlaaneshMessage("( ͡° ͜ʖ ͡°)");
        } else if (god == God.TZZENCH) {
            view.changeThemeToTzzench();
            unit.setGod(God.TZZENCH);
            view.changeMasteryLevelVisibility(true);
        }
    }

    @Override
    public void setUnitName(String name) {
        unit.setName(name);
    }

    private void removeHighlightsInARow(final int row) {
        for (int c = 0; c < highlightedViews[row].length; c++) {
            highlightedViews[row][c] = 0;
        }
    }

    private void setUnitStats(final int row, final int column) {
        if (row == 0) {
            unit.setMasteryLevel(column);
        } else if (row == 1) {
            greaterGiftCounter = column;
        } else if (row == 2) {
            middleGiftCounter = column;
        } else if (row == 3) {
            lesserGiftCounter = column;
        }
    }

    private long saveNewUnit() {
        return repository.saveUnit(unit);
    }
}
