package com.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class Leaderboard {

    public static List<LeaderboardEntry> addEntry(List<LeaderboardEntry> entries, String name, int score) {
        List<LeaderboardEntry> newEntries = new ArrayList<>(entries);
        newEntries.add(new LeaderboardEntry(name, score));
        return newEntries;
    }

    public static List<String> getLeaderboardOutput(List<LeaderboardEntry> entries) {
        return entries.stream()
                .sorted(Comparator.comparingInt(LeaderboardEntry::getScore).reversed())
                .map(entry -> entry.getName() + " - " + entry.getScore())
                .collect(Collectors.toList());
    }
}
