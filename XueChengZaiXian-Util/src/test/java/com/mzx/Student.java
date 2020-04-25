package com.mzx;

/**
 * @author ZhenXinMa
 * @date 2020/4/15 8:50
 */
public class Student {

    private String name;

    private Integer age;

    public Student(String name,Integer age){

        this.age = age;
        this.name = name;
    }

    public Student(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {

        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

}
