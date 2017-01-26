
package de.dortmund.fh.jung.myproject.searchview;

import java.util.LinkedList;
import java.util.List;

public class CheeseSearchEngine {

    private List<String> mCheeses;
    private int mCheesesCount;

    public CheeseSearchEngine(List<String> cheeses) {
        mCheeses = cheeses;
        mCheesesCount = mCheeses.size();
    }

    public void initCheese(List<String> cheeses) {
        mCheeses = cheeses;
        mCheesesCount = mCheeses.size();
    }

    public List<String> search(String query) {
        query = query.toLowerCase();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> result = new LinkedList<>();

        for (int i = 0; i < mCheesesCount; i++) {
            if (mCheeses.get(i).toLowerCase().contains(query)) {
                result.add(mCheeses.get(i));
            }
        }

        return result;
    }

}
