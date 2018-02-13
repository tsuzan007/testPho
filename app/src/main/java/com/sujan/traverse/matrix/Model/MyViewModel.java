package com.sujan.traverse.matrix.Model;

import android.arch.lifecycle.ViewModel;

import com.sujan.traverse.matrix.Presenter.MainPresenter;

/**
 * Created by macbookpro on 2/2/18.
 */

/**
 * This class saves the instance of mainPresenter during configuration changes.
 */
public class MyViewModel extends ViewModel {
    private MainPresenter mainPresenter;


    public MainPresenter getMainPresenter() {
        if (mainPresenter == null) {
            mainPresenter = new MainPresenter();

        }
        return mainPresenter;
    }
}
