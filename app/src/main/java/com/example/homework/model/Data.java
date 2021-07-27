package com.example.homework.model;

import com.example.homework.model.Item.Header;

import java.util.ArrayList;
import java.util.List;

import static com.example.homework.model.Item.*;

public class Data {
    public static final String GITHUB_URL = "https://github.com/PavelBobnev";
    private static List<Item> dataList = new ArrayList<Item>();
    private static List<String> filtersList = new ArrayList<>();
    public static boolean[] checked = new boolean[4];
    public static final String ALL = "All";
    public static final String ONE_YEAR_EXPERIENCE = "1 year";
    public static final String TWO_YEARS_EXPERIENCE = "2 years";
    public static final String FIVE_YEARS_EXPERIENCE = "5 years";

    public void setDataList(List<Item> dataList) {
        this.dataList = dataList;
    }

    public static List<Item> getData() {
        dataList = new ArrayList<>();
        dataList.add(new Header("Pavel", "Bobnev", "11 grade"));
        dataList.add(new ProjectDescription("Project idea", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
        dataList.add(new SkillHeader("Skill header"));
        try {
            addSkillsData(checked);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static List<String> getFilters() {
        filtersList = new ArrayList<>();
        filtersList.add(ALL);
        filtersList.add(ONE_YEAR_EXPERIENCE);
        filtersList.add(TWO_YEARS_EXPERIENCE);
        filtersList.add(FIVE_YEARS_EXPERIENCE);
        return filtersList;
    }

    public static List<String> getFiltersList() {
        return filtersList;
    }


    private static void addSkillsData(boolean[] array) {
        if (array[0]) {
            dataList.add(new SkillItem("Kotlin", ONE_YEAR_EXPERIENCE));
            dataList.add(new SkillItem("Java", TWO_YEARS_EXPERIENCE));
            dataList.add(new SkillItem("SQL", FIVE_YEARS_EXPERIENCE));
        } else {
            if (array[1])
                dataList.add(new SkillItem("Kotlin", ONE_YEAR_EXPERIENCE));
            if (array[2])
                dataList.add(new SkillItem("Java", TWO_YEARS_EXPERIENCE));
            if (array[3])
                dataList.add(new SkillItem("SQL", FIVE_YEARS_EXPERIENCE));
        }
    }
}
