
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.Collections;
import java.util.List;

import de.dortmund.fh.jung.myproject.God;

public class Unit {

    private int id;

    private String name;

    private God god;
    private List<Integer> lesserGifts;
    private List<Integer> middleGifts;
    private List<Integer> greaterGifts;
    private String photoFilePath;
    private int masteryLevel;

    public Unit() {
        this(-1, "No Name", God.NOGOD, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), "",
                0);
    }

    public Unit(int id, String name, God god, String photoFilePath, int masteryLevel) {
        this(id,name,god,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),photoFilePath, masteryLevel);
    }

    private Unit(int id, String name, God god, List<Integer> lesserGifts, List<Integer> middleGifts,
            List<Integer> greaterGifts, String photoFilePath, int masteryLevel) {
        this.id = id;
        this.name = name;
        this.god = god;
        this.lesserGifts = lesserGifts;
        this.middleGifts = middleGifts;
        this.greaterGifts = greaterGifts;
        this.photoFilePath = photoFilePath;
        this.masteryLevel = masteryLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getLesserGifts() {
        return lesserGifts;
    }

    public void setLesserGifts(List<Integer> lesserGifts) {
        this.lesserGifts = lesserGifts;
    }

    public List<Integer> getMiddleGifts() {
        return middleGifts;
    }

    public void setMiddleGifts(List<Integer> middleGifts) {
        this.middleGifts = middleGifts;
    }

    public List<Integer> getGreaterGifts() {
        return greaterGifts;
    }

    public void setGreaterGifts(List<Integer> greaterGifts) {
        this.greaterGifts = greaterGifts;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public int getNumberOfLesserGifts(){
        return getLesserGifts().size();
    }

    public int getNumberOfMiddleGifts(){
        return getMiddleGifts().size();
    }

    public int getNumberOfGreaterGifts(){
        return getGreaterGifts().size();
    }
}
