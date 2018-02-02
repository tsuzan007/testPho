package com.sujan.traverse.matrix.ModelTest;

import com.sujan.traverse.matrix.Model.MyViewModel;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by macbookpro on 2/2/18.
 */

public class MyViewModelTest {

    @Test
    public void getMainPresenter_Null_ReturnsNotNullMainPresenter(){
        MyViewModel myViewModel=new MyViewModel();
        assertNotNull(myViewModel.getMainPresenter());

    }


}
