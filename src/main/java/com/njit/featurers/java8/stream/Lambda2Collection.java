package com.njit.featurers.java8.stream;

import com.njit.featurers.java8.entity.Students;
import com.njit.featurers.java8.entity.SubjectEnum;
import com.njit.featurers.java8.entity.Subjects;
import org.junit.Test;

import javax.security.auth.Subject;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Lambda2Collection {
    public static double getGrade(int a,int b){
        return ((b+3)*5+a*7+9)%10*10+a+b+a/b*1.00;
    }


    public static List<Students> getList(){
        List<Students> students = new ArrayList<>();
        for(int i=1;i<5;i++){
            Students student = new Students();
            student.setNo("00"+i);
            student.setName("胡"+i);
            List<Subjects> subjects = new ArrayList<>();
            for (int j=0;j<3;j++){
                Subjects subject = new Subjects();
                subject.setName(SubjectEnum.getSubjectName(j));
                subject.setGrade(getGrade(j,i));
                subjects.add(subject);
            }
            student.setSubjectsList(subjects);
            student.setAverage(subjects);
            students.add(student);
        }
        return students;
    }


    //stream
    @Test
    public  void testAsList(){
        List<String> list = Arrays.asList("1","2");
        list.forEach(e-> System.out.println(e.toString()));
    }

    @Test
    public void testSort(){
        List<Students> students =getList();
        System.out.println(students.toString());

        System.out.println("===============通过no排序==================");
        System.out.println(students.stream().sorted(Comparator.comparing(Students::getNo).reversed()).collect(Collectors.toList()).toString());

        //思路就是将这个属性中的属性，提出来，然后比较方法同上
        System.out.println("===============通过对象属性中的某个属性排序==================");
        System.out.println(students.stream().sorted(Comparator.comparing(Students::getAverage)).collect(Collectors.toList()).toString());


    }

    /**
     * 可以直接生成stream，不需要先变成集合，再去转换stream
     */
    @Test
    public void testChangeType(){
       Stream.of("a1","a2","a3").map(s->s.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);
    }

    /**
     * 过滤对象几个中，根据属性的属性进行筛选
     */
    @Test
    public void testFilter(){
        List<Students> students =getList();
        List<Students> res = students.stream().filter(student->
                !(student.getSubjectsList().stream().min(Comparator.comparing(Subjects::getGrade)).get().getGrade()<30.0)
        ).collect(Collectors.toList());
        System.out.println(res.toString());
    }

    /**
     * list转map
     */
    @Test
    public void testList2Map(){
        List<Students> students = getList();
        Map<String,Students> infoMap = students.stream().collect(Collectors.toMap(Students::getNo,a->a,(K1,K2)->K1));
        infoMap.forEach((k,v)->{
            System.out.println(k+"    "+v.toString());
        });
    }




    //optional

    /**
     * 创建optional对象
     */
    @Test
    public void testNewOptional(){
        Optional<String> optional1 = Optional.of("String");
        System.out.println(optional1.get());

        //of 方法中的参数不能为null,否则报空指针异常
        //Optional<String> optional2 = Optional.of(null);

        Optional<String> optional2 = Optional.ofNullable("String2");
        System.out.println(optional2);
        Optional<String> optional3 = Optional.ofNullable(null);
        //判断 是否存在值
        System.out.println(optional3.isPresent());
        //如果该值是null，则使用传入的参数
        System.out.println(optional3.orElse("空值"));

        //使用map转换
        optional1 = optional1.map(value->value.toUpperCase());
        System.out.println(optional1.get());

        //使用filter进行过滤
        Optional<String> filter = optional1.filter(value->value.length()>7);
        System.out.println(filter.orElse("长度小于7哦"));

    }

}
