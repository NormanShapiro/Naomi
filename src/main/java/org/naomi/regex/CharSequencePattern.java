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

     An instance of CharSequencePattern matches, on each repetition, the
     entire character sequence in the {@link String} or other {@link
     CharSequence} (e.g., {@link java.nio.CharBuffer} or {@link
     javax.swing.text.Segment} of a character array) used to instantiate the
     CharSequencePattern.  All such characters are treated literally, that is,
     there are no escape characters.  Note that an instance of this class
     matches the entire specified sequence of characters in order (once per
     repetition); for example:

<p>
<pre>
    pat = new CharSequenceCharPattern("abC");
    pat.setMinAndMaxCount(1,3);
</pre>
	matches only "abC" or "abCabC" or "abCabCabC"

<p>
    Note that a CharSequencePattern is quite different from a {@link CharClass},
    which, on each repetition, matches any single character in the set of
    characters that define the CharClass.

*/

public class CharSequencePattern extends Pattern
{
  private CharSequence charSequence;

  public CharSequencePattern(CharSequence charSequence)
  {this.charSequence=charSequence;}

  public CharSequencePattern(char ... ch) {this(new String(ch));}

  public CharSequence getCharSequence() {return charSequence;}

  /** @return this CharSequencePattern*/
  public CharSequencePattern setCharSequence(CharSequence charSequence)
  {this.charSequence=charSequence;altered();return this;}


  Rope getInnerRope()
  {
     String quote=java.util.regex.Pattern.quote(charSequence.toString());
     return new CharSequenceRope(quote);
   }

   public CharSequencePattern copy()
   {
     CharSequencePattern ans=new CharSequencePattern(getCharSequence());
     copyTo(ans);
     return ans;
   }
}
