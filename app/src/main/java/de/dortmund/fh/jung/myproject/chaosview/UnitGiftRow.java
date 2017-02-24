
package de.dortmund.fh.jung.myproject.chaosview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class UnitGiftRow extends View {

    TextView num0, num1, num2;

    public UnitGiftRow(Context context) {
        super(context);
    }

    public TextView getNum0() {
        return num0;
    }

    public void setNum0(TextView num0) {
        this.num0 = num0;
    }

    public TextView getNum1() {
        return num1;
    }

    public void setNum1(TextView num1) {
        this.num1 = num1;
    }

    public TextView getNum2() {
        return num2;
    }

    public void setNum2(TextView num2) {
        this.num2 = num2;
    }
}
