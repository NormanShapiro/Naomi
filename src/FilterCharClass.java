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
     An instance of FilterCharClass is a {@link Pattern} which, on each
     repetition, matches any single character that lies inclusively (in the
     normal collating sequence) between {@link #getFirst()} and {@link
     #getLast()} and satisfies {@link #filter filter(Char)}.
     If the resulting set (which is <i>not</i> a {@link java.util.Set
     java.util.Set}) contains more than a few thousand characters, performance
     may be adversely impacted. The internal "Not" flag in each instance can be
     set to make the class instead match all characters <i>except</i> those in
     the pre-defined set denoted by {@link #getFirst()}, {@link #getLast()}
     and {@link #filter filter(Char)}; this has the effect of matching the
     complement of the specified set of characters.
<p>
Example:
<pre>
  // Since FilterCharClass is abstract, its body is
  //	provided here to define the required methods

     // Select ASCII characters between 'A' and 'z'
     // whose two least significant bits are both 1

    FilterCharClass bits = new FilterCharClass()
      {
        public char getFirst() {return 'A';}
        public boolean filter(char ch){return (ch&3) ==3;}
        public char getLast() {return 'z';}

        public FilterCharClass copy()
        {return this;}
      };

</pre>

*/


public abstract class FilterCharClass extends ComplementableCharClass
{
  public FilterCharClass() {}

  public abstract char getFirst();
  public abstract char getLast();
  public abstract boolean filter(char h);

  private CharSequence innerString=null;

  private CharSequence computeInnerString()
  {
     StringBuilder sub=new StringBuilder();
     if(isNot()) sub.append("^");
     for(char ch=getFirst();ch<=getLast();++ch)
        if(filter(ch))
           sub.append(ch);
     return innerString=sub;
  }

  /** call after any significant change */
  public FilterCharClass redact()
  {
     innerString=null;
     altered();
     return this;
  }

  FilterCharClass fetchInnerString(StringBuilder ans)
  {
     if(innerString==null)
        innerString=computeInnerString();
     ans.append(quote(innerString));
     return this;
  }
}
