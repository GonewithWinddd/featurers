package com.njit.featurers.java8.entity;

import lombok.Data;

import java.util.List;

@Data
public class Students {
    private String no;
    private String name;
    private List<Subjects> subjectsList;
    private double average;

//    public Students(){
//        this.average = subjectsList.stream().mapToDouble(Subjects::getGrade).average().getAsDouble();
//    }


    public double getAverage() {
        return average;
    }

    public void setAverage(List<Subjects> subjectsList) {
        this.average = subjectsList.stream().mapToDouble(Subjects::getGrade).average().getAsDouble();
    }

    @Override
    public String toString() {
        String subjectStr = "[";
        for (Subjects subject:subjectsList){
            subjectStr+=subject.getName()+":  "+subject.getGrade()+" ";
        }
        String averageStr = "";
        averageStr+=" "+subjectsList.stream().mapToDouble(Subjects::getGrade).average().getAsDouble();
        subjectStr+="]";
        return "\nStudents{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", subjectsList=" + subjectStr +
                ", average=" + average +
                '}';
    }
}
