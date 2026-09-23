package com.sntiago05.codearena.domain.user;

public class CalculateLevel {

    private CalculateLevel() {

    }

    public static UserLevel calculate(Integer experience) {
        if (experience == null || experience < 0) {
            return UserLevel.ROOKIE;
        }
        if (experience >= 10000) {
            return UserLevel.LEGEND;
        } else if (experience >= 5000) {
            return UserLevel.MASTER;
        } else if (experience >= 3000) {
            return UserLevel.SENIOR;
        } else if (experience >= 1500) {
            return UserLevel.DEVELOPER;
        } else if (experience >= 500) {
            return UserLevel.JUNIOR;
        } else {
            return UserLevel.ROOKIE;
        }
    }
}
