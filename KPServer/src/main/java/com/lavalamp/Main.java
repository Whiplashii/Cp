package com.lavalamp;

import com.lavalamp.serverlauncher.ServerLauncher;

public class Main {
    public static void main(String[] args) {
        ServerLauncher serverLauncher = new ServerLauncher();
        serverLauncher.WaitClients();
    }
}