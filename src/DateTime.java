/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Norm;

import org.naomi.regex.*;
import java.util.*;

/**Example class that uses Naomi to parse an Email date-time component, as
specified by <a href="http://tools.ietf.org/html/rfc2822"> the IETF
date-time specification (RFC2822)</a>.
*/

/* Examples:
  15 Jul 2014 15:24 pdt'
  tue, 15 Jul 2014 15:24 Est
*/

public class DateTime extends ConcatenatePattern
{
// A DateTime consists of an optional day-of-week, a date, a time-of-day and a
// time-zone, These are white space delimited and optionally preceded and/or
// followed by white apace.

  public DateTime(String[] args)
  {
     Pretty pretty=new Pretty();
     setPretty(pretty);
     DayOfWeekPattern dayOfWeekPattern=new DayOfWeekPattern();
     DatePattern datePattern=new DatePattern();
     TimeOfDayPattern timeOfDayPattern=new TimeOfDayPattern();
     ZonePattern zonePattern=new ZonePattern();
     int errorCount=0;
     add
     (
        new WhitePattern(true),
        dayOfWeekPattern,
        new WhitePattern(true),
        datePattern,

        new WhitePattern(false),
        timeOfDayPattern,
        new WhitePattern(false),
        zonePattern,
        new WhitePattern(true)
     );

     A.p(getRegularExpression());
     for(String arg: args)
     {
        if(arg==null) continue;
         Matcher matcher=matcher(arg);
         if(!matcher.matches())
         {
           System.out.println(arg +"  "+ " Does not match");
           ++errorCount;
         }
         else
         {
           Calendar calendar=Calendar.getInstance();
           datePattern.apply(matcher,calendar);
           timeOfDayPattern.apply(matcher,calendar);
           zonePattern.apply(matcher,calendar);
           if(!dayOfWeekPattern.check(matcher,calendar))
              ++errorCount;
           long milliseconds=calendar.getTimeInMillis();
           Date date=new Date(milliseconds);
           System.out.println(arg + " = " + date);
         }

     }

     System.exit(errorCount==0? 0:1);
  }
}

class DatePattern extends ConcatenatePattern
{
// A date consists of digits (for the day of month) an abbreviated month name,
// and digits (for the year). These are white space delmited.

  EnumPattern months;
  Pattern days,years;
  DatePattern()
  {
     days=new DigitsPattern(1,2);
     months=new EnumPattern(MonthName.class);
     months.setCaseSensitive(false);

     years=new DigitsPattern(4);
     add
     (
        days,
        new WhitePattern(false),
        months,
        new WhitePattern(false),
        years
      );
  }

  void apply(Matcher matcher,Calendar calendar)
  {
     if(matcher.group(this)==null)
        throw new ParseException("Missing Date");
     calendar.set(Calendar.DATE,Integer.parseInt(matcher.group(days)));
     calendar.set(Calendar.MONTH,months.getOrdinal(matcher,true));
     calendar.set(Calendar.YEAR,Integer.parseInt(matcher.group(years)));
  }

}

class TimeOfDayPattern extends ConcatenatePattern
{
// A time-of=day consists of digits (for hours), digits (for minutes) and
// optional digits (for seconds), These are colon delimited.
  Pattern hours,minutes,seconds;
  TimeOfDayPattern()
  {
     hours=new DigitsPattern(2);
     minutes=new DigitsPattern(2);
     seconds=new DigitsPattern(2);
     Pattern secondsGroup=new ConcatenatePattern(new CharSequencePattern(":"),seconds);
     secondsGroup.setMinCount(0);
     add
     (
        hours,
        new CharSequencePattern(":"),
        minutes,
        secondsGroup
     );
  }

  void apply(Matcher matcher, Calendar calendar)
  {
     if(matcher.group(this)==null)
        throw new ParseException("Missing time-of-day");
     int hrs=Integer.parseInt(matcher.group(hours));
     calendar.set(Calendar.HOUR_OF_DAY,hrs-1);
     int mins=Integer.parseInt(matcher.group(minutes));
     calendar.set(Calendar.MINUTE,mins);
     String secsS= matcher.group(seconds);
     int secs=secsS==null? 0 :Integer.parseInt(secsS);
     calendar.set(Calendar.SECOND,secs);
     calendar.set(Calendar.MILLISECOND,0);
  }
}

class ZonePattern extends OrPattern
{
// A Zone consists of either letters or of an optional sign followed for 4 digits
// giving the time offset in hours and minutes from Coordinated Universal Time.

  Pattern letters,sign,hours,minutes,digits;
  ZonePattern()
  {
     letters=new EnumPattern(ZoneName.class);
     letters.setCaseSensitive(false);
     sign=new ExplicitCharClass("+-");
     hours=new DigitsPattern(2);
     minutes=new DigitsPattern(2);
     digits=new ConcatenatePattern(sign,hours,minutes);
     add(letters,digits);
  }

  private int getOffsetMinutes(Matcher matcher)
  {
     String zoneName=matcher.group(letters);
     if(zoneName != null)
        return ZoneName.getOffset(zoneName)*60;
    try
    {
        int hrs=Integer.parseInt(matcher.group(hours));;
        int mins=Integer.parseInt(matcher.group(minutes));
        if("-".equals(matcher.group(sign)))
           return -(60*hrs+mins);
        else
           return (60*hrs+mins);
     }
     catch(NumberFormatException ex){throw new Bug(ex);}
     catch(NullPointerException ex){throw new Bug(ex);}
  }

  void apply(Matcher matcher, Calendar calendar)//do ere all other applys
  {
     if(matcher.group(this)==null)
        throw new ParseException("Missing Zone");
     int calendarOffset=calendar.getTimeZone().getRawOffset();
     int matcherOffset=1000*60*getOffsetMinutes(matcher);
     calendar.add(Calendar.MILLISECOND,matcherOffset-calendarOffset);
  }
}

class WhitePattern extends BuiltInCharClass
{

  WhitePattern(boolean optional)
  {
     super(CoreBuiltIn.white);
     setMinCount(optional? 0:1);
     setMaxCount(null);// infinity
  }
}


class DigitsPattern extends BuiltInCharClass
{

  DigitsPattern(int min,int max)
  {
     super(CoreBuiltIn.digit);
     setMinCount(min);
     setMaxCount(max);
   }

  DigitsPattern(int count)
  {
     this(count,count);
  }

}

class DayOfWeekPattern extends ConcatenatePattern
{
// day-of-week, if present, consists of a abbreviated day name followed by a
// comma.

  EnumPattern dayNamePattern;
  DayOfWeekPattern()
  {
     dayNamePattern=new EnumPattern(DayName.class);
     dayNamePattern.setCaseSensitive(false);
     Pattern commaPattern= new CharSequencePattern(",");
     Pattern whitePattern=new WhitePattern(false);
     add(dayNamePattern, commaPattern,whitePattern);
     setMinCount(0);
  }

  boolean check(Matcher matcher, Calendar calender)
  {
     String day=matcher.group(dayNamePattern);
     if(day==null)
        return true;
     if
     (
        calender.get(Calendar.DAY_OF_WEEK)==
        dayNamePattern.getOrdinal(matcher,true)+1
     )
     return true;
     System.err.println("Inconsistent day of week");
     return false;
  }

}

enum DayName
{
  sun,
  mon,
  tue,
  wed,
  thu,
  fri,
  sat,
  ;

}

enum MonthName
{
  jan,
  feb,
  mar,
  apr,
  may,
  jun,
  jul,
  aug,
  sep,
  oct,
  nov,
  dec,
  ;
}

enum ZoneName
{
  ut(0),
  gmt(0),
  est(-5),
  edt(-4),
  cst(-6),
  cdt(-5),
  mst(-7),
  mdt(-6),
  pst(-8),
  pdt(-7),
  ;
  final int offset;

  ZoneName(int offset)
  {
     this.offset=offset;
  }


  public static int getOffset(String string)
  {
     return valueOf(string.toLowerCase()).offset;
  }
}

class ParseException extends RuntimeException
{
  ParseException(String msg,Throwable cause) {super(msg,cause);}
  ParseException(String msg){super(msg);}
  ParseException(Throwable cause) {super(cause);}
}
