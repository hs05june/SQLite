package com.example.sqlitecheck;

public class Employee {
    private int id;
    private String name;
    private String increment;

    public Employee(){}

    public Employee(int id,String name, String increment){
        this.id = id;
        this.name = name;
        this.increment = increment;
    }

    public Employee(String name, String increment){
        this.name = name;
        this.increment = increment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }
}
