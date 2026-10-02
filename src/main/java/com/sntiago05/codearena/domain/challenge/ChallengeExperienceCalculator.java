package com.sntiago05.codearena.domain.challenge;

/**
 * Utility for calculating experience points based on challenge difficulty.
 */
public class ChallengeExperienceCalculator {
    
    private ChallengeExperienceCalculator() {

    }

    public static int calculate(ChallengeDifuculty difuculty) {
        if (difuculty == null) {
            return 0;
        }
        return switch (difuculty) {
            case EASY -> 100;
            case MEDIUM -> 250;
            case HARD -> 500;
            case LEGENDARY -> 1000;
        };
    }
}
