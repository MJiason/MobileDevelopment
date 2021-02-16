package ua.kpi.comsys.io8214.mobileapp;

import java.util.*;

public class Task1 {
    private final Random rand = new Random(System.currentTimeMillis());
    private final String students = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; " +
            "Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; " +
            "Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; " +
            "Лихацька Юлія- ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; " +
            "Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; " +
            "Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; " +
            "Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81;" +
            " Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83;" +
            " Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; " +
            "Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; " +
            "Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82";

    private Map<String, ArrayList<String>> studentsMap;

    public void task1() {
        studentsMap = new HashMap<>();

        for (String elem : students.split(";")) {
            String[] arr;
            arr = elem.split("-");
            String key = arr[1] + "-" + arr[2];
            String name = arr[0];

            if (!studentsMap.containsKey(key)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(name);
                studentsMap.put(key, list);
            } else {
                studentsMap.get(key).add(name);
            }
        }

        for (String key : studentsMap.keySet()) {
            Collections.sort(studentsMap.get(key));
            System.out.println(key);
            for (String name : studentsMap.get(key)) {
                System.out.println(name);
            }
        }
        System.out.println(studentsMap.toString());
    }

    private final int[] points = {12, 12, 12, 12, 12, 12, 12, 16};
    private final int numPoints = points.length;

    private int randomValue(int maxValue) {
        switch (rand.nextInt(6)) {
            case 1:
                return (int) Math.ceil((float) (maxValue) * 0.7);
            case 2:
                return (int) Math.ceil((float) (maxValue) * 0.9);
            case 3:
            case 4:
            case 5:
                return maxValue;
            default:
                return 0;
        }
    }

    private Map<String, Map<String, int[]>> studentPoints;

    public void task2() {
        studentPoints = new HashMap<>();

        for (String group : studentsMap.keySet()) {
            Map<String, int[]> pointsMap = new HashMap<>();
            for (String student : studentsMap.get(group)) {
                int[] pointsCurr = new int[numPoints];
                for (int i = 0; i < numPoints; i++) {
                    pointsCurr[i] = randomValue(points[i]);
                }
                pointsMap.put(student, pointsCurr);
            }
            studentPoints.put(group, pointsMap);
        }
        for (String group : studentPoints.keySet()) {
            System.out.println(group);
            for (String student : studentPoints.get(group).keySet()) {
                System.out.print(student + ": ");
                for (int point : studentPoints.get(group).get(student)) {
                    System.out.print(point + " ");
                }
                System.out.println();
            }
        }
    }

    private Map<String, Map<String, Integer>> sumPoints;

    public void task3() {
        sumPoints = new HashMap<>();
        for (String group : studentPoints.keySet()) {
            Map<String, Integer> studentSum = new HashMap<>();
            for (String student : studentPoints.get(group).keySet()) {
                int pointsSum = 0;
                for (int point : studentPoints.get(group).get(student)) {
                    pointsSum += point;
                }
                studentSum.put(student, pointsSum);
            }
            sumPoints.put(group, studentSum);
        }
        for (String group : sumPoints.keySet()) {
            System.out.println(group);
            for (String student : sumPoints.get(group).keySet()) {
                System.out.println(student + " " + sumPoints.get(group).get(student));
            }
        }
    }

    private Map<String, Float> groupAvg;

    public void task4() {
        groupAvg = new HashMap<>();
        for (String group : sumPoints.keySet()) {
            int groupSum = 0;
            int counter = 0;
            for (String student : sumPoints.get(group).keySet()) {
                groupSum += sumPoints.get(group).get(student);
                counter++;
            }
            groupAvg.put(group, (float) groupSum / counter);
        }
        for (String group : groupAvg.keySet()) {
            System.out.format("%s %.3f\n", group, groupAvg.get(group));
        }
    }

    private Map<String, ArrayList<String>> passedPerGroup;

    public void task5() {
        passedPerGroup = new HashMap<>();
        for (String group : sumPoints.keySet()) {
            ArrayList<String> students = new ArrayList<>();
            for (String student : sumPoints.get(group).keySet()) {
                if (sumPoints.get(group).get(student) > 59){
                    students.add(student);
                }
            }
            passedPerGroup.put(group, students);
        }
        for (String group : passedPerGroup.keySet()){
            System.out.println(group);
            for (String student : passedPerGroup.get(group)){
                System.out.println(student);
            }
        }
    }

}