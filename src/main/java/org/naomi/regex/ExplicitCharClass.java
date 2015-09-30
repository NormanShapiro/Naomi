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

     An instance of ExplicitCharClass is a {@link Pattern} which, on each
     repetition, matches any single character in the {@link String} or other
     {@link CharSequence} used to the instantiate the ExplicitCharClass. If the
     resulting set (which is <i>not</i> a {@link java.util.Set java.util.Set})
     contains more than a few thousand characters, performance may be adversely
     impacted. The internal "Not" flag in each instance can be set to make the
     class instead match all characters <i>except</i> those in the pre-defined
     set denoted by its instantiating {@link CharSequence}; this has the effect
     of matching the complement of the specified set of characters.

<p> Only one individual character of the resulting set will be matched by each
repetition of the ExplicitCharClass pattern. The order of the characters in the
set and any duplicates in it are ignored, so, for example:

<p>
<pre>
	pat1 = new ExplicitCharClass("abc");
	pat1.setMinAndMaxCount(1,3);

	pat2 = new ExplicitCharClass("bcab");
	pat2.setMinAndMaxCount(1,3);
</pre>
	both match "a", "b", "aa", "aaa", "ba", "cab", etc.
<p>
     To match an entire sequence of characters in order, use a
     {@link CharSequencePattern} instead.

*/

public class ExplicitCharClass extends ComplementableCharClass
{
  private CharSequence charSequence;

  public ExplicitCharClass(CharSequence charSequence)
  {
     setCharSequence(charSequence);
  }

  public ExplicitCharClass setCharSequence(CharSequence charSequence)
  {
     this.charSequence=charSequence;
     altered();
     return this;
  }

  public CharSequence getCharSequence() {return charSequence;}

  ExplicitCharClass fetchInnerString(StringBuilder ans)
  {
     if(isNot())ans.append("^");
     ans.append(quote(charSequence));
     return this;
  }

 public ExplicitCharClass copy()
 {
    ExplicitCharClass ans= new ExplicitCharClass(getCharSequence());
    copyTo(ans);
    return ans;
 }

}
