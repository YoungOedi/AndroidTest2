
package de.dortmund.fh.jung.myproject.mvp;

/**
 * Created by hendrikjung on 30.12.16.
 */

public interface BasePresenter<T extends BaseView> {
    void bind(final T view);

    void unbind();
}
