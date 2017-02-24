
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.List;

public class Unit {

    private String name;
    private int masteryLevel;
    private ChaosPresenter.God god;
    private List<Integer> giftSmall;
    private List<Integer> gitftMidle;
    private List<Integer> giftGreat;
    private String photoFilePath;

    public Unit(String name, ChaosPresenter.God god) {
        this(name, god, 0);
    }

    public Unit(String name, ChaosPresenter.God god, int masteryLevel) {
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

    public List<Integer> getGiftGreat() {
        return giftGreat;
    }

    public void setGiftGreat(List<Integer> giftGreat) {
        this.giftGreat = giftGreat;
    }

    public List<Integer> getGitftMidle() {
        return gitftMidle;
    }

    public void setGitftMidle(List<Integer> gitftMidle) {
        this.gitftMidle = gitftMidle;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public ChaosPresenter.God getGod() {
        return god;
    }

    public void setGod(ChaosPresenter.God god) {
        this.god = god;
    }

    public List<Integer> getGiftSmall() {
        return giftSmall;
    }

    public void setGiftSmall(List<Integer> giftSmall) {
        this.giftSmall = giftSmall;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }
}
