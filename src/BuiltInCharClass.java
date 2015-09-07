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

     An instance of BuiltInCharClass is a {@link Pattern} which, on each
     repetition, matches any single character in the pre-defined set of
     characters denoted by its instantiating BuiltIn Enum constant. The internal
     "Not" flag in each instance can be set to make the class instead match
     all characters <i>except</i> those in the pre-defined set denoted by its
     instantiating BuiltIn Enum constant; this has the effect of matching the
     complement of the specified set of characters.

<p>

A BuiltInCharClass can be instantiated with a constant from any of the enums:
<br/>
<pre>
    {@link CoreBuiltIn}
    {@link LocaleBuiltIn}
    {@link UniBlockBuiltIn}
    {@link UniScriptBuiltIn}
</pre>

*/
 public class BuiltInCharClass extends ComplementableCharClass
 {
  private BuiltInInterface builtIn;

  public BuiltInCharClass(BuiltInInterface builtIn)
  {
     setBuiltIn(builtIn);
  }

  public BuiltInCharClass setBuiltIn(BuiltInInterface builtIn)
  {
     this.builtIn=builtIn;
     altered();
     return this;
  }

  public BuiltInInterface getBuiltIn() {return builtIn;}

  BuiltInCharClass fetchInnerString(StringBuilder ans)
  {
     if(isNot())ans.append("^");
     ans.append(builtIn.getPreRegularExpression());
     return this;
  }

  public BuiltInCharClass copy()
  {
    BuiltInCharClass ans=new BuiltInCharClass(getBuiltIn());
    copyTo(ans);
    return ans;
  }
}
