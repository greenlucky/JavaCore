package com.lamdevops.collection.stream.groupby;

import com.lamdevops.collection.stream.pojo.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupByPlayerTest {

    private GroupByPlayer groupByPlayer;

    @Before
    public void init() throws Exception {
        groupByPlayer = new GroupByPlayer();
    }

    @Test
    public void testImportCSVToList() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");

        groupByPlayer.importCSVToList(currentDir.getAbsolutePath().toString());
        List<Player> players = groupByPlayer.getPlayers();
        System.out.println(players.toString());
    }

    @Test
    public void testImportCSVGroupingByYear() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<Integer, List<Player>> mapPlayer = groupByPlayer.importCSVGroupingByYear(currentDir.getAbsolutePath().toString());
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }

    @Test
    public void testImportCSVGroupingByFiles() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<List<String>, List<Player>> mapPlayer = groupByPlayer.importCSVGroupByFiles(currentDir.getAbsolutePath().toString());
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }

    @Test
    public void testImportCSVGroupingByDynamicFiles() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<List<String>, List<Player>> mapPlayer = groupByPlayer.importCSVGroupByFiles(currentDir.getAbsolutePath().toString(), "getYear", "getTeamID");
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }

    @Test
    public void testImportCSVGroupingByDynamicFilesIsNull() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<List<String>, List<Player>> mapPlayer = groupByPlayer.importCSVGroupByFiles(currentDir.getAbsolutePath().toString(), null);
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }

    @Test
    public void testImportCSVGroupingByDynamicFilesToSet() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<List<String>, Set<Player>> mapPlayer = groupByPlayer.importCSVGroupByFilesToSet(currentDir.getAbsolutePath().toString(), "getYear", "getTeamID");
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }

    @Test
    public void testImportCSVGroupingByDynamicFilesToSumSalary() {
        File currentDir = new File("src/main/resources/files/playerfile.csv");
        Map<List<String>, Integer> mapPlayer = groupByPlayer.importCSVGroupByFilesAndSumSallary(currentDir.getAbsolutePath().toString(), Player::getSalary, "getYear", "getTeamID");
        mapPlayer.entrySet().stream().forEach(System.out::println);
    }
}