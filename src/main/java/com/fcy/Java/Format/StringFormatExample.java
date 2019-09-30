package com.fcy.Java.Format;

import java.util.Date;
import java.util.logging.Logger;
/*
*
* %tA	Full name of the day of the week, e.g. “Sunday“, “Monday“
 %ta	Abbreviated name of the week day e.g. “Sun“, “Mon“, etc.
 %tB	Full name of the month e.g. “January“, “February“, etc.
 %tb	Abbreviated month name e.g. “Jan“, “Feb“, etc.
 %tC	Century part of year formatted with two digits e.g. “00” through “99”.
 %tc	Date and time formatted with “%ta %tb %td %tT %tZ %tY” e.g. “Fri Feb 17 07:45:42 PST 2017“
 %tD	Date formatted as “%tm/%td/%ty“
 %td	Day of the month formatted with two digits. e.g. “01” to “31“.
 %te	Day of the month formatted without a leading 0 e.g. “1” to “31”.
%tF	ISO 8601 formatted date with “%tY-%tm-%td“.
%tH	Hour of the day for the 24-hour clock e.g. “00” to “23“.
%th	Same as %tb.
%tI	Hour of the day for the 12-hour clock e.g. “01” – “12“.
%tj	Day of the year formatted with leading 0s e.g. “001” to “366“.
%tk	Hour of the day for the 24 hour clock without a leading 0 e.g. “0” to “23“.
%tl	Hour of the day for the 12-hour click without a leading 0 e.g. “1” to “12“.
%tM	Minute within the hour formatted a leading 0 e.g. “00” to “59“.
%tm	Month formatted with a leading 0 e.g. “01” to “12“.
%tN	Nanosecond formatted with 9 digits and leading 0s e.g. “000000000” to “999999999”.
%tp	Locale specific “am” or “pm” marker.
%tQ	Milliseconds since epoch Jan 1 , 1970 00:00:00 UTC.
%tR	Time formatted as 24-hours e.g. “%tH:%tM“.
%tr	Time formatted as 12-hours e.g. “%tI:%tM:%tS %Tp“.
%tS	Seconds within the minute formatted with 2 digits e.g. “00” to “60”. “60” is required to support leap seconds.
%ts	Seconds since the epoch Jan 1, 1970 00:00:00 UTC.
%tT	Time formatted as 24-hours e.g. “%tH:%tM:%tS“.
%tY	Year formatted with 4 digits e.g. “0000” to “9999“.
%ty	Year formatted with 2 digits e.g. “00” to “99“.
%tZ	Time zone abbreviation. e.g. “UTC“, “PST“, etc.
%tz e Zone Offset from GMT e.g. “

-0%a	floating point (except BigDecimal)	Hex output of floating point number
%b	Any type	“true” if non-null, “false” if null
%c	character	Unicode character
%d	integer (incl. byte, short, int, long, bigint)	Decimal Integer
%e	floating point	decimal number in scientific notation
%f	floating point	decimal number
%g	floating point	decimal number, possibly in scientific notation depending on the precision and value.
%h	any type	Hex String of value from hashCode() method.
 %n	none	Platform-specific line separator.
%o	integer (incl. byte, short, int, long, bigint)	Octal number
%s	any type	String value
%t	Date/Time (incl. long, Calendar, Date and TemporalAccessor)	%t is the prefix for Date/Time conversions. More formatting flags are needed after this. See Date/Time conversion below.
%x	integer (incl. byte, short, int, long, bigint)
Hex string.
“.
*
*
* */
public class StringFormatExample {
    private static Logger logger = Logger.getLogger(StringFormatExample.class.getName());

    public static void main(String args[]) throws Exception {
        Date date=new Date();
        System.out.println("星期几:    "+String.format("%tA", date));
        System.out.println("星期几缩写:  "+String.format("%ta",date));
        System.out.println("月份: "+String.format("%tB",date));
        System.out.println(String.format("%tb",date));
        System.out.println(String.format("%tC",date));
        System.out.println(String.format("%tc",date));
        System.out.println(String.format("%tD",date));
        System.out.println("一月中的第几天:"+String.format("%td",date));
        System.out.println("一月中的第几天:"+String.format("%te",date));
        System.out.println(String.format("%tF",date));
        System.out.println("24小时:"+String.format("%tH",date));
        System.out.println("月份:"+String.format("%th",date));
        System.out.println("12小时:"+String.format("%tI",date));
        System.out.println("一年的第几天: "+String.format("%tj",date));
        System.out.println("24小时制:  "+String.format("%tk",date));
        System.out.println("12小时制:  "+String.format("%tl",date));
        System.out.println("分钟: "+String.format("%tM",date));
        System.out.println("月份: "+String.format("%tm",date));
        System.out.println("纳秒:  "+String.format("%tN",date));
        System.out.println("上午下午am,pm"+String.format("%tp",date));
        System.out.println("1970毫秒:"+String.format("%tQ",date));
        System.out.println("24时分:"+String.format("%tR",date));
        System.out.println("12时分秒:"+String.format("%tr",date));
        System.out.println("秒:"+String.format("%tS",date));
        System.out.println("1970多少秒:"+String.format("%ts",date));
        System.out.println("时分秒:"+String.format("%tT",date));
        System.out.println("年:"+String.format("%tY",date));
        System.out.println("年缩写:"+String.format("%ty",date));
        System.out.println(String.format("%tZ",date));
        System.out.println(String.format("%tz",date));

    }
}
