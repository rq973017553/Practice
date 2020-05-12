package com.rq.practice.database;

public class DBManager {

    private DBManager() {
        // Empty Method
    }

    private static final class Holder {
        private static final DBManager INSTANCE = new DBManager();
    }

    public static final DBManager getInstance() {
        return Holder.INSTANCE;
    }
}
