package cannonball.cannonball.Domain;

import java.sql.Date;

public class RandomGroup {
    String randomName;
    int boyGirlNum;
    Date deadLine;
    Date raiseRandom;
    int inGroupOf;
    Date startRandom;

    public String getRandomName() {
        return randomName;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }

    public int getBoyGirlNum() {
        return boyGirlNum;
    }

    public void setBoyGirlNum(int boyGirlNum) {
        this.boyGirlNum = boyGirlNum;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getRaiseRandom() {
        return raiseRandom;
    }

    public void setRaiseRandom(Date raiseRandom) {
        this.raiseRandom = raiseRandom;
    }

    public int getInGroupOf() {
        return inGroupOf;
    }

    public void setInGroupOf(int inGroupOf) {
        this.inGroupOf = inGroupOf;
    }

    public Date getStartRandom() {
        return startRandom;
    }

    public void setStartRandom(Date startRandom) {
        this.startRandom = startRandom;
    }
}
