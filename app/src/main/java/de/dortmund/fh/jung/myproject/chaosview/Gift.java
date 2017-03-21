
package de.dortmund.fh.jung.myproject.chaosview;

import de.dortmund.fh.jung.myproject.God;

public class Gift {

    private int id;
    private int number;
    private String description;
    private God god;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public God getGod() {
        return god;
    }

    public void setGod(God god) {
        this.god = god;
    }
}
