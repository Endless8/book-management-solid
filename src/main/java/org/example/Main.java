package org.example;

import org.example.app.AppStarter;
import org.example.app.AppStarterImp;

public class Main {
    public static void main(String[] args) {
        AppStarter appStarter = new AppStarterImp();
        appStarter.start();
    }
}