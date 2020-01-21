package com.njit.featurers.java8.entity;

import lombok.Data;


public enum SubjectEnum {
    CHINESE("语文",0),MATH("数学",1),ENGLISH("英语",2);

    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    SubjectEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static String getSubjectName(int code){
        for (SubjectEnum subjectEnum:SubjectEnum.values()){
            if (subjectEnum.getCode()==code){
                return subjectEnum.getName();
            }
        }
        return null;
    }
}
