import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author wangjialong
 */
public class Instants {

    public static void main(String[] args) {
        /*获取今天的日期*/
        LocalDate todayDate = LocalDate.now();
        System.out.println("今天的日期："+todayDate);

        /*指定日期，进行相应操作*/
        /*2019-04月的第一天*/
        LocalDate firstDay = todayDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天日期："+firstDay);

        /*2019-04月的第一天 另一种方式*/
        LocalDate firstDayOther = todayDate.withDayOfMonth(1);
        System.out.println("本月第一天日期："+firstDayOther);

        /*取本月的最后一天 大月、小月、平年、闰年的问题都不用考虑*/
        LocalDate lastDay = todayDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月最后一天日期："+lastDay);

        /*当前日期＋1天*/
        LocalDate tomorrow = todayDate.plusDays(1);
        System.out.println("明天日期："+tomorrow);

        /*判断是否为闰年*/
        boolean isLeapYear = tomorrow.isLeapYear();
        System.out.println("是闰年吗："+isLeapYear);


        /*日期比较*/
        System.out.println("明天在今天之后吗："+tomorrow.isAfter(todayDate));
        System.out.println("明天在今天之前吗："+tomorrow.isBefore(todayDate));
    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        LocalDate birthday = LocalDate.of(1988,7,5);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(),birthday.getDayOfMonth());
        MonthDay toDay = MonthDay.from(todayDate);
        System.out.println("今天是生日吗："+toDay.equals(birthdayMd));


    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        /*获取当前时间*/
        LocalTime now = LocalTime.now();
        System.out.println("当前时间为："+now);

        /*如果不想显示毫秒*/
        now = now.withNano(0);
        System.out.println("当前时间为："+now);

        /*指定时间*/
        LocalTime middleToday = LocalTime.of(12,0,1);
        LocalTime nineClock = LocalTime.parse("00:00:00");
        System.out.println("中午时间是："+middleToday.format(DateTimeFormatter.ISO_TIME));
        System.out.println("上午时间是："+nineClock.format(DateTimeFormatter.ISO_TIME));

        /*时间增减*/
        LocalTime nowTimePlusTenHour =now.plusHours(10);
        LocalTime nowTimePlus2Hour = now.plus(2, ChronoUnit.HOURS);
        System.out.println("10小时后时间是："+nowTimePlusTenHour.format(DateTimeFormatter.ISO_TIME));
        System.out.println("2小时后时间是："+nowTimePlus2Hour.format(DateTimeFormatter.ISO_TIME));

        /*时间比较*/
        System.out.println("10小时之后时间比2小时之后时间大吗："+nowTimePlusTenHour.isAfter(nowTimePlus2Hour));
        System.out.println("10小时之后时间比2小时之后时间小吗："+nowTimePlusTenHour.isBefore(nowTimePlus2Hour));

    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        /*查看当前时区*/
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println("当前时区为："+defaultZone);

        /*查看美国纽约当前时间*/
        ZoneId america = ZoneId.of("America/New_York");
        System.out.println("上海当前时间为："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("美国当前时间为"+LocalDateTime.now(america).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        /*带有时区的时间*/
        ZonedDateTime americaZonedDateTime = ZonedDateTime.now(america);
        System.out.println("美国的时区时间为："+americaZonedDateTime);

    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        /*日期比较*/
        LocalDate lastYearDate = firstDay.plusYears(-1);
        Period period = Period.between(lastYearDate,todayDate);
        System.out.println("相差的天数为："+period.getDays());
        System.out.println("相差的月数为："+period.getMonths());
        System.out.println("相差的年数为："+period.getYears());
        System.out.println("相差的总天数为："+lastYearDate.until(todayDate,ChronoUnit.DAYS));
        System.out.println("相差的总月数为："+lastYearDate.until(todayDate,ChronoUnit.MONTHS));
        System.out.println("相差的总年数为："+lastYearDate.until(todayDate,ChronoUnit.YEARS));


    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");


        /*时间比较*/
        Duration duration = Duration.between(nowTimePlusTenHour,nowTimePlus2Hour);
        System.out.println("相差的天数:"+duration.toDays());
        System.out.println("相差的小时数:"+duration.toHours());
        System.out.println("相差的分钟数:"+duration.toMinutes());
        System.out.println("相差毫秒数:"+duration.toMillis());
        System.out.println("相差的纳秒数:"+duration.toNanos());

        /*带日期的时间比较*/
        duration = Duration.between(LocalDateTime.now(america),LocalDateTime.now());
        System.out.println("相差的天数:"+duration.toDays());
        System.out.println("相差的小时数:"+duration.toHours());
        System.out.println("相差的分钟数:"+duration.toMinutes());
        System.out.println("相差毫秒数:"+duration.toMillis());
        System.out.println("相差的纳秒数:"+duration.toNanos());


    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        /*Date与Instant的相互转化*/
        Instant instant  = Instant.now();
        Date date = Date.from(instant);
        Instant instant2 = date.toInstant();
        Instant instant3 = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(date.getTime());
        System.out.println(instant2.getEpochSecond());
        System.out.println(instant2.getNano());
        System.out.println(instant3.getEpochSecond());
        System.out.println(instant3.getNano());


    /*----------------------------------------------------------------*/
        System.out.println("----------------------------------------------------------------");

        /*Date转为LocalDateTime*/
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        /*LocalDateTime转Date*/
        Instant instant4 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        Date date3 = Date.from(instant4);

       /*LocalDate转Date*/
        /*因为LocalDate不包含时间，所以转Date时，会默认转为当天的起始时间，00:00:00*/
        Instant instant5 = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date4 = Date.from(instant);

    }
}
