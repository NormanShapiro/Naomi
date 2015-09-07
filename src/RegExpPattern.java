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

  A RegExpPattern  matches the strings matched by a given regular
  expression. Its use constitutes a resort to the equivalent of assembly
  language. It exists to support legacy applications.

<blockquote>

<b>Note: The regular expression should not use any named capturing groups
whose names consist of an 'a' followed by digits.</b>

</blockquote>

Bug: If the regular expression contains any capturing groups, then {@link
Matcher#start(Pattern)} and {@link Matcher#end(Pattern)} may not work properly.
This bug will be removed when this package is compiled for java 1.8 or later.
*/

public class RegExpPattern extends Pattern
{
  private String regExp;

  public RegExpPattern() {this(null);}

  /** @param regExp A  regular expression as described in java.util.regex.Pattern
 */
  public RegExpPattern(String regExp)
  {
     this.regExp=regExp;
  }

  /**
  @param regExp A  regular expression as described in java.util.regex.Pattern
  @return this RegExpPattern
  */
  public RegExpPattern setRegularExpression(String regExp )
  {
     this.regExp=regExp;
     altered();
     return this;
  }

  public String getRegExp() {return regExp;}

  Rope getInnerRope()
  {
     return new CharSequenceRope(regExp);
  }

  public RegExpPattern copy()
  {
     RegExpPattern ans=new RegExpPattern(getRegExp());
     copyTo(ans);
     return ans;
  }
}
