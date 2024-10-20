package com.example.projectsrule;

public class UserModel {
    private int id;
    private String date;
    private int weight;

    public UserModel(int id, String date, int weight){
        this.id = id;
        this.date = date;
        this.weight = weight;
        }

    public UserModel(){    }
    //print data toString
    @Override
    public String toString(){
        return date + ' ' + weight + ' ';
    }
    //setters
    public void setId(int id){
        this.id = id;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    //getters
    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public int getWeight(){
        return weight;
    }

}
