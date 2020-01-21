package com.njit.featurers.java8.stream;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @description:
 * @author: 123
 * @date: Created in 2020/1/21 13:32
 * @version: 1.0
 * @modified By:
 */
public class TestLocalDate {

    /**
     *  获取时间日期，
     */
    @Test
    public void getDate(){
        //获取当前日期
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate);

        //获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println(nowDateTime);

        LocalDateTime dateTimeMax = LocalDateTime.MAX;
        System.out.println(dateTimeMax);

        //获取当月第一天的日期，如果超过比如1月只有31天，传入32会运行报错
        LocalDate firstDayOfM = nowDate.withDayOfMonth(1);
        //同理
        LocalDate firstDayOfM2 = nowDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfM);

        //判断 是否闰年
        Boolean isLeap = firstDayOfM.isLeapYear();
        System.out.println(firstDayOfM.getYear()+(isLeap?"是":"不是")+"闰年");

        //
        System.out.println("当前日期是当月的第"+firstDayOfM.getDayOfMonth()+"天");
        //getDayOfWeek()方法是 获取的DayOfWeek枚举对象
        System.out.println("当前日期是"+firstDayOfM.getDayOfWeek()+"");
        //获取月份 getMonth()返回值是Month对象，getMonthValue()获取的是 int，1-12
        System.out.println(firstDayOfM.getMonth());
        System.out.println(firstDayOfM.getMonthValue());

        //获取指定日期
        LocalDate appointDate = LocalDate.of(2020,2,2);
        System.out.println(appointDate);


    }

    /**
     * 计算间隔
     */
    @Test
    public void getPeriod(){
        LocalDate nowDate = LocalDate.of(2020,1,10);
        LocalDate lastDayOfM = LocalDate.of(2020,3,8);
        System.out.println(lastDayOfM);
        //封装间隔时间的类
        Period period = Period.between(nowDate,lastDayOfM);


        System.out.println("================使用period计算间隔=============");
        //获取间隔 显示1个月27天
        System.out.println(period.toString());
        System.out.println(period.getDays());
        System.out.println(period.getMonths());

        System.out.println("=============使用nowDate.until计算间隔=============");
        //统一单位
        System.out.println(nowDate.until(lastDayOfM, ChronoUnit.DAYS));
        System.out.println(nowDate.until(lastDayOfM, ChronoUnit.MONTHS));

        //获取时间的差值
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime appointDateTime = LocalDateTime.of(2020,1,25,22,18);
        Duration duration = Duration.between(nowDateTime,appointDateTime);


        System.out.println("换算成天"+duration.toDays());
        System.out.println("换算成小时"+duration.toHours());
        System.out.println("换算成分钟"+duration.toMinutes());

    }

    /**
     * 时区
     */
    @Test
    public void testZoneId(){
        ZoneId localZone = ZoneId.systemDefault();
        System.out.println("当前时区"+localZone+"当前时间"+LocalDateTime.now());

        ZoneId amercia = ZoneId.of("America/Phoenix");
        String amerciaDateStr = LocalDateTime.now(amercia).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("America/Phoenix时间"+amerciaDateStr);
        System.out.println(ZonedDateTime.now(amercia));
    }

    /**
     * 类型转换
     */
    @Test
    public void testConvert(){

        Date nowDate = new Date();
        LocalDateTime nowDateTime = LocalDateTime.now();
        //date 转 localDateTime
        LocalDateTime date2Local = LocalDateTime.ofInstant(nowDate.toInstant(),ZoneId.systemDefault());
        System.out.println(date2Local);

        //localDateTime 转 date
        Instant localInstant = nowDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date local2Date = Date.from(localInstant);
        SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(local2Date.getClass()+"具体时间"+sif.format(local2Date));

        //localDateTime 转 String
        String nowDateTimeStr = nowDateTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        System.out.println(nowDateTimeStr);

        LocalDateTime str2Local = LocalDateTime.parse(nowDateTimeStr,DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        System.out.println("string转成LocalDateTime"+str2Local);
    }


}
