package com.campus.domain;

public class Food {
    private String foodName;
    private int calories;
    private Float caloriesInGram;

    public Food() {}

    public Food(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.caloriesInGram = calories / 100.f;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", calories='" + calories + '\'' +
                '}';
    }

    public float caloriesEaten(int gram){
        return gram * caloriesInGram;
    }
}
