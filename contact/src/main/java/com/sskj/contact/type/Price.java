package com.sskj.contact.type;

import com.sskj.common.App;
import com.sskj.contact.R;

public enum Price {

    MARKET(1, App.INSTANCE.getString(R.string.contact_dealFragment7)), LIMIT(2, App.INSTANCE.getString(R.string.contact_dealFragment8));

    public int value;
    public String name;

    Price(int value,String name) {
        this.value = value;
        this.name=name;
    }

}
