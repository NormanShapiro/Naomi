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
     This Enum defines a set of constants, each of which specifies a
     Unicode "script" of characters to be matched (see below).  Each
     of these Enum constants can be used to instantiate a {@link BuiltInCharClass}
     that matches any of the characters in the corresponding Unicode script.
     For example,
<p>
<pre>
      Pattern Arabic = new BuiltInCharClass(ScriptBuiltIn.ARABIC);
</pre>
     matches (on each repetition) one Unicode character in the ARABIC script.

@see <a href="http://en.wikipedia.org/wiki/Script_(Unicode)"> Unicode script</a>
*/
public enum UniScriptBuiltIn implements BuiltInInterface
{
//(
  ARABIC,
  ARMENIAN,
  AVESTAN,
  BALINESE,
  BAMUM,
  BATAK,
  BENGALI,
  BOPOMOFO,
  BRAHMI,
  BRAILLE,
  BUGINESE,
  BUHID,
  CANADIAN_ABORIGINAL,
  CARIAN,
  CHAM,
  CHEROKEE,
  COMMON,
  COPTIC,
  CUNEIFORM,
  CYPRIOT,
  CYRILLIC,
  DESERET,
  DEVANAGARI,
  EGYPTIAN_HIEROGLYPHS,
  ETHIOPIC,
  GEORGIAN,
  GLAGOLITIC,
  GOTHIC,
  GREEK,
  GUJARATI,
  GURMUKHI,
  HAN,
  HANGUL,
  HANUNOO,
  HEBREW,
  HIRAGANA,
  IMPERIAL_ARAMAIC,
  INHERITED,
  INSCRIPTIONAL_PAHLAVI,
  INSCRIPTIONAL_PARTHIAN,
  JAVANESE,
  KAITHI,
  KANNADA,
  KATAKANA,
  KAYAH_LI,
  KHAROSHTHI,
  KHMER,
  LAO,
  LATIN,
  LEPCHA,
  LIMBU,
  LINEAR_B,
  LISU,
  LYCIAN,
  LYDIAN,
  MALAYALAM,
  MANDAIC,
  MEETEI_MAYEK,
  MONGOLIAN,
  MYANMAR,
  NEW_TAI_LUE,
  NKO,
  OGHAM,
  OL_CHIKI,
  OLD_ITALIC,
  OLD_PERSIAN,
  OLD_SOUTH_ARABIAN,
  OLD_TURKIC,
  ORIYA,
  OSMANYA,
  PHAGS_PA,
  PHOENICIAN,
  REJANG,
  RUNIC,
  SAMARITAN,
  SAURASHTRA,
  SHAVIAN,
  SINHALA,
  SUNDANESE,
  SYLOTI_NAGRI,
  SYRIAC,
  TAGALOG,
  TAGBANWA,
  TAI_LE,
  TAI_THAM,
  TAI_VIET,
  TAMIL,
  TELUGU,
  THAANA,
  THAI,
  TIBETAN,
  TIFINAGH,
  UGARITIC,
  UNKNOWN,
  VAI,
  YI,
//)
  ;
  private String string;

  public CharSequence getPreRegularExpression()
  {
     if(string==null)
        string="\\p{Is"+name()+"}";
     return string;
  }

  public String toString() {return getClass().getSimpleName()+"."+name();}
}
