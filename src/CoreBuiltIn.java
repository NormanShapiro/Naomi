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
package org.naomi.regex;

/**
     This Enum defines a set of constants, each of which specifies
     a pre-defined "Core" set of characters to be matched (as described
     below).  Each of these Enum constants can be used to instantiate a
     {@link BuiltInCharClass} that matches any of the characters in the
     corresponding pre-defined set.  For example,

<p>
<pre>
    Pattern digits = new BuiltInCharClass(CoreBuiltIn.digit);
</pre>
     matches (on each repetition) any single digit character, whereas
<p>
<pre>
    Pattern patternAny = new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
</pre>
     matches (on each repetition) any single character that is not a line
     terminator.

*/

public enum CoreBuiltIn implements BuiltInInterface
{
//(
  //Predefined character classes
    /** Any character*/ any("[a[^a]]"),
    //@ /**Any character except line terminators*/ anyButLineEnds("."),
    /**Any character except line terminators*/ anyButLineEnds
    (
        "[a[^a]&&[^\\r\\n]&&[^\\r]&&[^\\n]]"
     ),
    /**A digit*/ digit("\\d"),
    /**A non_digit*/ nonDigit("\\D"),
    /**A whitespace character*/ white("\\s"),
    /**A non_whitespace character*/ notWhite("\\S"),
    /**A word character*/ word("\\w"),
    /**A non_word character*/ nonWord("\\W"),
    /**A line feed, a carriage return ,or a carriage return followed immediately
    *by a linefeed*/
    lineEnd("(\\r\\n)|(\\n)|(\\r)"),

    /**A carriage return followed immediately by a linefeed*/
    crlf("(\\r\\n)"),

    /**A carriage return followed immediately by a linefeed*/
    microsoftLineEnd("(\\r\\n)"),

    /**A linefeed*/ unixLineEnd("(\\n)"),
    /**A linefeed*/lf("(\\n)"),
    /** A carriage return*/ cr("(\\r)"),
    /**A carriage return*/ appleLineEnd("(\\r)"),

   //POSIX character classes (US_ASCII only)

    /**A lower_case alphabetic character*/ lower("\\p{Lower}"),
    /**An upper_case alphabetic character*/ upper("\\p{Upper}"),
    /**All ASCII*/ ascii("\\p{ASCII}"),
    /**An alphabetic character*/ alpha("\\p{Alpha}"),
    alphnumeric ("\\p{Alnum}"), //An alphanumeric character:[\\p{Alpha}\\p{Digit}]
    /**Punctuation*/ punct("\\p{Punct}"),
    /**A visible character*/ visible("\\p{Graph}"),
    /**A printable character*/ print("\\p{Print}"),
    /**A space or a tab*/ blank("\\p{Blank}"),
    /**A control character*/ cntrl("\\p{Cntrl}"),
    /**A hexadecimal digit*/ hex("\\p{XDigit}"),
    /**An octal digit*/octal("[0-8]"),
    /**A plus or minus sign*/sign("[\\+\\-]"),
    /**A whitespace character*/ space("\\p{Space}"),

  //java.lang.Character classes (simple java character type)
    /**Equivalent to java.lang.Character.isLowerCase()*/ javaLower("\\p{javaLowerCase}"),
    /**Equivalent to java.lang.Character.isUpperCase()*/ javaUpper("\\p{javaUpperCase}"),
    /**Equivalent to java.lang.Character.isWhitespace()*/ javaWhite("\\p{javaWhitespace}"),
    /**Equivalent to java.lang.Character.isMirrored()*/ javaMirror("\\p{javaMirrored}"),

  //Classes for Unicode scripts, blocks, categories and binary properties
    /**An uppercase letter (category)*/ uniUpper("\\p{Lu}"),
    /**An alphabetic character (binary property)*/ uniAlpha("\\p{IsAlphabetic}"),
    /**A currency symbol*/ currency("\\p{Sc}"),

Eight_Bit("['\u0000'-'\u017F']"),
//)
;

  String string;

  CoreBuiltIn(String string)
  {
     this.string=string;
  }

  public CharSequence getPreRegularExpression()
  {
     if(string == null)
        string="\\p{In"+name()+"}";
     return string;
  }

  //@public String toString()
  //@{
     //@throw new UnsupportedOperationException();
  //@}
  public String toString() {return getClass().getSimpleName()+"."+name();}
}

