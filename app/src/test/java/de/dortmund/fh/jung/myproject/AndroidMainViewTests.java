package de.dortmund.fh.jung.myproject;


import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import de.dortmund.fh.jung.myproject.mainview.MainActivity;
import de.dortmund.fh.jung.myproject.searchview.SearchActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class AndroidMainViewTests{

    @Test
    public void searchViewButtonTextTest() {
        SearchActivity activity = Robolectric.setupActivity(SearchActivity.class);
        Button button = (Button)activity.findViewById(R.id.search_button);
        assertEquals(button.getText(),"search");
    }

}
