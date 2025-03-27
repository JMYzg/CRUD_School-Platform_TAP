package com.tap.schoolplatform.services;

import com.tap.schoolplatform.utils.SharedData;

public abstract class Service {
    protected final SharedData sharedData;

    protected Service() {
        this.sharedData = SharedData.getInstance();
    }
}
