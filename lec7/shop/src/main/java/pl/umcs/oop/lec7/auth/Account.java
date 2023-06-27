package pl.umcs.oop.lec7.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

class Account {
    private int id;
    private String name;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
