package com.lamdevops.stream.groupby;

import com.lamdevops.stream.pojo.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GroupByPlayer {

    private List<Player> players;

    public GroupByPlayer() {
        this.players = new ArrayList<>();
    }

    public void importCSVToList(String fileName) {
        Pattern pattern = Pattern.compile(",");
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            players = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(
                                Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, List<Player>> importCSVGroupingByYear(final String fileName) {
        Map<Integer, List<Player>> mapPlayers = null;
        Pattern pattern = Pattern.compile(",");
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            mapPlayers = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(
                                Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    }).collect(Collectors.groupingBy(x -> x.getYear()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    public Map<List<String>, List<Player>> importCSVGroupByFiles(final String fileName) {
        Map<List<String>, List<Player>> mapPlayers = null;
        Pattern pattern = Pattern.compile(",");
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            mapPlayers = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    })
                    .collect(Collectors.groupingBy(x -> {
                        return new ArrayList<String>(Arrays.asList(Integer.toString(x.getYear()), x.getTeamID()));
                    }));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    public Map<List<String>, List<Player>> importCSVGroupByFiles(final String fileName, String... methods) {
        Map<List<String>, List<Player>> mapPlayers = null;
        Pattern pattern = Pattern.compile(",");

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            mapPlayers = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    })
                    .collect(Collectors.groupingBy(x -> getMethods(x, methods)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    public Map<List<String>, Set<Player>> importCSVGroupByFilesToSet(final String fileName, String... methods) {
        Map<List<String>, Set<Player>> mapPlayers = null;
        Pattern pattern = Pattern.compile(",");

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            mapPlayers = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    })
                    .collect(Collectors.groupingBy(x -> getMethods(x, methods), Collectors.toSet()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    public Map<List<String>, Integer> importCSVGroupByFilesAndSumSallary(
            final String fileName, ToIntFunction<Player> mapper, String... methods) {

        Map<List<String>, Integer> mapPlayers = null;
        Pattern pattern = Pattern.compile(",");

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            mapPlayers = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(Integer.parseInt(arr[0]),
                                arr[1],
                                arr[2],
                                arr[3],
                                Integer.parseInt(arr[4]));
                    })
                    .collect(Collectors.groupingBy(x -> getMethods(x, methods), Collectors.summingInt(mapper)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    private List<String> getMethods(Player x, String[] methods) {
        List<String> lstMethod = new ArrayList<>();
        if(methods != null) {
            Arrays.stream(methods).forEach(method -> {
                try {
                    lstMethod.add(x.getClass().getMethod(method).invoke(x).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return lstMethod;
    }


    public List<Player> getPlayers() {
        return players;
    }
}