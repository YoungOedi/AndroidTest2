
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.List;

import de.dortmund.fh.jung.myproject.God;

public class Unit {

    private String name;
    private int masteryLevel;
    private God god;
    private List<Integer> lesserGift;
    private List<Integer> middleGift;
    private List<Integer> greaterGift;
    private String photoFilePath;

    public Unit(){
        this("No Name", God.NOGOD);
    }

    public Unit(String name, God god) {
        this(name, god, 0);
    }

    public Unit(String name, God god, int masteryLevel) {
        this.name = name;
        this.god = god;
        this.masteryLevel = masteryLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public God getGod() {
        return god;
    }

    public void setGod(God god) {
        this.god = god;
    }

    public List<Integer> getLesserGift() {
        return lesserGift;
    }

    public void setLesserGift(List<Integer> lesserGift) {
        this.lesserGift = lesserGift;
    }

    public List<Integer> getMiddleGift() {
        return middleGift;
    }

    public void setMiddleGift(List<Integer> middleGift) {
        this.middleGift = middleGift;
    }

    public List<Integer> getGreaterGift() {
        return greaterGift;
    }

    public void setGreaterGift(List<Integer> greaterGift) {
        this.greaterGift = greaterGift;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }
}
