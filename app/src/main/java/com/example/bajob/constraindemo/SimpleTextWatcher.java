package com.example.bajob.constraindemo;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by bajob on 4/7/2017.
 */

abstract class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        duringTextChange(charSequence,i,i1,i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    abstract void duringTextChange(CharSequence charSequence, int i, int i1, int i2);
}
