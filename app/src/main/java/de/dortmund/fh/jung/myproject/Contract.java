package de.dortmund.fh.jung.myproject;

/**
 * Created by hendrikjung on 29.12.16.
 */

public interface Contract {
    interface View{
        void setText(String text);
    }

    interface Presenter{
        void handleClickEvent();
        void bind(Contract.View view);
        void unbind();
    }

}
