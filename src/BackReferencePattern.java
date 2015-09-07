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

import java.util.List;

/**

An instance of this {@link Pattern} matches the same string matched by some
previously matched Pattern (specified by the <code>referent</code> argument of
the BackReferencePattern Constructor).

<p>
Example:
<p>
<pre>
    Pattern vowel=new ExplicitCharClass("aeiou");
    Pattern back=new BackReferencePattern(vowel);
    Pattern doubleVowel=new ConcatenatePattern(vowel,back);
</pre>

Here, doubleVowel matches (for example) "ee" and "ii" but not "ei".

*/

public class BackReferencePattern extends Pattern//@ implements MultiPatterner
{
  private Pattern referent;

/**
  @param referent The Pattern to be matched as a back-reference
*/

  public BackReferencePattern(Pattern referent) {setReferent(referent);}

  /** @return this BackReferencePattern */
  public BackReferencePattern setReferent(Pattern referent)
  {this.referent=referent; altered();return this;}

  public Pattern getReferent() { return referent;}

  Rope getInnerRope()
  {
     return new CharSequenceRope("\\k<"+referent.getNam()+">");
  }

  BackReferencePattern checkReferent(List<Pattern> kids)
  {
     if(this.equals(referent))
        throw new BackReferenceException(this + ": self referring referent");
     int referentIndex=kids.indexOf(referent);
     if(referentIndex<0)
        throw new BackReferenceException
           (this +" has dangling reference to " + referent);
     if( referentIndex> kids.indexOf(this))
        throw  new BackReferenceException
           (this + " has forward reference to " + referent);
    return this;
  }

/**
  Thrown when a BackReferencePattern has an inappropriate <code>referent</code>.
*/
  public static class BackReferenceException extends RuntimeException
  {
     BackReferenceException(String message)
     {
        super(message);
     }
  }

  public BackReferencePattern copy()
  {
     BackReferencePattern ans=new BackReferencePattern(referent.copy());
     super.copyTo(ans);
     return ans;
  }


}
