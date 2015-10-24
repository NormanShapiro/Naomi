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

import java.text.DecimalFormatSymbols;
import java.util.Locale;


/**

     This Enum defines a set of constants, each of which specifies a
     pre-defined "Locale" set of characters to be matched (as described
     below).  Each of these Enum constants can be used to instantiate a
     {@link BuiltInCharClass} that matches any of the characters in the
     corresponding pre-defined set.  For example,

<p>
<pre>
	Pattern digits = new BuiltInCharClass(LocaleBuiltIn.minus);
</pre>
     matches (on each repetition) the localized character for "minus".

@see #setLocale
*/

public enum LocaleBuiltIn implements BuiltInInterface
{
//(

//* This constant is Locale dependent; See {@link #setLocale}.*/
decimalSeparator
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getDecimalSeparator());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
groupingSeparator
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getGroupingSeparator());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
minus
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getMinusSign());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
monetaryDecimalSeparator
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getMonetaryDecimalSeparator());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
percent
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getPercent());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
perMill
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getPerMill());
  }
},

//* This constant is Locale dependent; See {@link #setLocale}.*/
zero
{
  public CharSequence getPreRegularExpression()
  {
     return esc(getSyms().getZeroDigit());
  }
},

//)
;
  private static  DecimalFormatSymbols decimalFormatSymbols;
  private static Locale locale=Locale.getDefault();

  /** Sets the Locale for Locale dependent constants.
  Defaults to the default Locale,*/
  static public void setLocale(Locale localeArg)
  {
     if(locale.equals(localeArg))
        return;
      locale=localeArg;
      decimalFormatSymbols=null;
  }

  private static DecimalFormatSymbols getSyms()
  {
     if(decimalFormatSymbols==null)
        decimalFormatSymbols=DecimalFormatSymbols.getInstance(locale);
      return decimalFormatSymbols;
  }

  private static String esc(char ch)
  {
     if(Character.isAlphabetic(ch)||Character.isDigit(ch))
        return Character.toString(ch);
     else
        return "\\"+ch;
   }


  public String toString() {return getClass().getSimpleName()+"."+name();}
}
