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

//@import java.util.regex.Pattern;
import java.util.*;

/** 

    An instance of any subclass of this abstract class is a {@link Pattern}
    which, on each repetition, matches any single character in the set of
    characters that define that instance.

<p>

    That is, a CharClass is defined by a set of characters (though it is
    <i>not</i> a {@link java.util.Set java.util.Set}), any one of which can be
    matched in the target string on each repetition of the Pattern (the number
    of repetitions being controlled by private but user-settable MinCount
    and MaxCount values, whose defaults are both 1). For example:

<p>
<pre>
	pat1 = new ExplicitCharClass("abc");
	pat1.setMinAndMaxCount(1,1);	// redundantly restating the defaults
</pre>

    matches only "a" or "b" or "c" whereas
<p>
<pre>
	pat1to4 = new ExplicitCharClass("abc");
	pat1to4.setMinAndMaxCount(1,4);
</pre>
    matches "a", "b", "aa", "aaa", "ba", "abc", "cbb", "aabb", "abcb", etc.
<p>
    That is, pat1to4 matches anywhere from 1 to 4 characters in a row, where
    each character must be one of "a", "b", or "c".
<p>

    CharClass allows the user to define subsets of characters that are to be
    matched, where these subsets can be derived from pre-defined ("BuiltIn")
    sets of characters, strings of characters (interpreted as sets--as in the
    pat1 and pat1to4 examples above), ranges of characters, Java Collections
    or Iterators, or the unions, intersections, or complements of other
    CharClasses. The subclasses {@link IntersectCharClass} and {@link
    UnionCharClass}, the CharClass method {@link #getComplement}, and an
    internal "Not" flag (in all CharClass subclasses except IntersectCharClass
    and UnionCharClass) allow the creation of CharClasses that are arbitrary
    boolean combinations of other CharClasses.

<p>
    Note that a CharClass is quite different from a {@link CharSequencePattern},
    which matches an entire sequence of characters, such as the string "abc"
    or "Naomi" (once per repetition).

*/
public  abstract class CharClass extends Pattern
{
  CharClass() {}

  abstract CharClass fetchInnerString(StringBuilder stringBuilder);

  /** @return A CharClass containing exactly those characters not contained in
  this CharClass*/
  abstract  public CharClass getComplement();

  CharClass fetchString(StringBuilder stringBuilder)
  {
     stringBuilder.append('[');
     fetchInnerString(stringBuilder);
     stringBuilder.append(']');
     return this;
  }

  static void join
     (String delim,StringBuilder ans,Collection<CharClass> classes,boolean inner)
  {
     boolean first=true;
     for(CharClass charClass:classes)
     {
        if(!first && delim != null)
           ans.append(delim);
        if(inner)
           charClass.fetchInnerString(ans);
         else
           charClass.fetchString(ans);
         first=false;
     }
     //@return this;
  }

  public String toString()
  {
     StringBuilder ans=new StringBuilder();
     fetchString(ans);
     return ans.toString();
  }

  static StringBuilder quote(CharSequence in)
  {
     StringBuilder ans=new StringBuilder();
     for(int i=0;i<in.length();++i)
     {
        char cur=in.charAt(i);
        switch(cur)
        {
           default:
              ans.append(cur);
           break;
           case '-':
           case '^':
           case '$':
           case '(':
           case ')':
           case '[':
           case ']':
           case '\\':
              ans.append('\\');
              ans.append(cur);
              break;
        }
    }
    return ans;
  }

  Rope getInnerRope()
  {
     StringBuilder ans=new StringBuilder();
     fetchString(ans);
     return new CharSequenceRope(ans);
  }

  Collection<CharClass> complementCollection(Collection<CharClass> collection)
  {
     List<CharClass> ans=new ArrayList<CharClass>();
     for(CharClass charClass:collection)
        ans.add(charClass.getComplement());
     return ans;
  }

}
