package com.mzx;

import sun.plugin2.gluegen.runtime.CPU;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhenXinMa
 * @date 2020/4/15 8:50
 */
public class JDKMain {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("5");
        list.add("4");
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三1",18));
        students.add(new Student("张三2",15));
        students.add(new Student("张三3",17));
        students.add(new Student("张三4",23));
        students.add(new Student("张三4",23));
        students.add(new Student("张三6",25));
        Stream<String> stream = list.stream();
        /* filter ： 返回由与此给定谓词匹配的此流的元素组成的流。
        * 过滤我们不想要的东西 */
        stream.filter(String-> "1".equals(list.get(0))).collect(Collectors.toList());

        /*过滤之后的*/
        List<Student> collect = students.stream().filter(student -> student.getAge() > 18).distinct().collect(Collectors.toList());
        List<Student> collect1 = students.stream().filter(student -> student.getAge() == 23).distinct().collect(Collectors.toList());
        for (Student student : collect1) {
            System.out.println(student);
        }

    }


}
