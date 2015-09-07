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

     An instance of IntervalCharClass is a {@link Pattern} which, on each
     repetition, matches any single character that lies inclusively (in the
     normal collating sequence) between two specified characters. The internal
     "Not" flag in each instance can be set to make the class instead match
     all characters <i>except</i> those in the pre-defined set defined by the
     instantiating character interval; this has the effect of matching the
     complement of the specified set of characters.

*/

public class  IntervalCharClass extends ComplementableCharClass
{
  char left;
  char right;

  public IntervalCharClass(char left,char right)
  {
     setLeft(left);
     altered();
     setRight(right);
  }

  public IntervalCharClass setLeft(char left)
  {
     this.left=left;
     altered();
     return this;
  }

  public IntervalCharClass setRight(char right)
  {
     this.right=right;
     return this;
  }

  public char getLeft() {return left;}
  public char getRight() {return right;}

  IntervalCharClass fetchInnerString(StringBuilder ans)
  {
     char[] leftChars={left};
     char[] rightChars={right};
     CharSequence leftString=quote(new String(leftChars));
     CharSequence rightString=quote(new String(rightChars));
     if(isNot()) ans.append("^");
     ans.append(leftString+"-"+rightString);
     if(right < left)
        throw new BadIntervalException(left,right);
     return this;
   }

  public IntervalCharClass copy()
  {
     IntervalCharClass ans= new IntervalCharClass(getLeft(),getRight());
     copyTo(ans);
     return ans;
  }

/**
Thrown when the character values <code>right</code> < <code>left</code>
(in the normal collating sequence).
*/
  public static class BadIntervalException extends RuntimeException
  {
     BadIntervalException(String message)
     {
        super(message);
     }

     BadIntervalException(char left, char right)
     {
        this("'"+ left + "' - '"+right+"'");
     }

   }

}
