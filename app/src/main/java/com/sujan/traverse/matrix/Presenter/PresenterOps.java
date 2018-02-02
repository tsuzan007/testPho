package com.sujan.traverse.matrix.Presenter;

import com.sujan.traverse.matrix.HelperClass.Position;

/**
 * Created by macbookpro on 2/2/18.
 */

public interface PresenterOps {
    public boolean onClick_submit();

    public void onTableTextChange(Position p, CharSequence c);

    public void initMatrices();
}
