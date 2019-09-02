package com.sskj.contact.type;

import com.sskj.common.App;
import com.sskj.contact.R;

public enum Trade {
    UP( App.INSTANCE.getString(R.string.contact_dealFragment5), 1), DOWN( App.INSTANCE.getString(R.string.contact_dealFragment6), 2);

    public int value;
    public String name;

    Trade(String name, int value) {
        this.value = value;
        this.name = name;
    }
}
