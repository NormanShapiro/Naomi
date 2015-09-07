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
    This Enum provides constant values for the 
{@link Pattern#setPolicy setPolicy(Policy policy)} method of
    {@link Pattern} and its subclasses, which controls various behavior and
    performance aspects of matching for a given pattern.
<p>

  These behavioral parameters are not interpreted by Naomi but are simply
  passed along to Java's pattern matching library, whose behavior depends
  on them in various ways that are beyond the scope of this documentation.

<p>

  Therefore, for an explanation of the effects of these values (when used in
  the {@link Pattern#setPolicy setPolicy} method) on the behavior and
  performance aspects of pattern matching, see this discussion of

 <a
 href="http://www.xyzws.com/Javafaq/what-are-differences-among-greedy-reluctant-and-possessive-quantifiers-in-java-patterns/206">
 quantifiers</a>.  Additional discussion of these aspects of pattern matching
 can be found in descriptions of the Perl language's use of regular expressions.



@see <a href="http://www.xyzws.com/Javafaq/what-are-differences-among-greedy-reluctant-and-possessive-quantifiers-in-java-patterns/206"> quantifiers</a>
@see <a href="http://search.cpan.org/~jhi/perl-5.8.0/pod/perlre.pod"> Perl
regular expressions</a>

*/
public enum Policy
{
  /** Is compatible with any any values for min and max count*/
  greedy("",false)
  {
     public Policy checkCounts(Pattern pattern) {return this;}
  },

  /** Requires that either min count != 1 and/or max count != 1.*/
  reluctant("?",false),


  /** Requires that either min count != 1 and/or max count != 1.*/
  possessive("+",false),

  /**Requires that min and max count both have their default value, 1.
  */
  positiveLookahead("=",true),

  /**Requires that min and max count both have their default value, 1.
  */
  negativeLookahead("!",true),

  /**Requires that min and max count both have their default value, 1.
  */
  positiveLookbehind("<=",true),

  /**Requires that min and max count both have their default value, 1.
  */
  negativeLookbehind("<!",true),

/**

    This value indicates that the given pattern should match independently
    of any larger pattern in which it may be embedded, thereby avoiding
    backtracking when you know you want a given subpattern to be matched;
    it requires that min and max count both have their default value, 1.
    The only reason to ever use {@link #independent}, is to
    optimize performance.

*/

  independent("?>",true),
  ;
  private final String string;
  private final boolean oneOneCount; //ether must be or not must be.

  Policy (String string,boolean oneOneCount)
  {
     this.string=string;
     this.oneOneCount=oneOneCount;
  }
  String getIntroduction(){return string;}

  /**Return whether or not this Policy == greedy*/
  public boolean isDefault(){return this==greedy;}

  /** Checks that the min and max count of pattern are compatible with this
  Policy.
  @throws BadCountException if pattern's min and max count are not compatible
  with this Policy.
  */

  public Policy checkCounts(Pattern pattern)
  {
     int min=pattern.getMinCount();
     Integer max=pattern.getMaxCount();
     if(oneOneCount)
     {
        if(min==1 && max.equals( new Integer(1))) return this;
     }
     else
     {
        if(min != 1 || ! new Integer(1).equals(max)) return this;
     }
     throw new BadCountException(pattern);

  }

  StringBuilder toString(StringBuilder sb,Verbose verbose)
  {
     if(sb==null) sb=new StringBuilder();
     switch(verbose)
     {
        case no: return sb;
        case yes: if(isDefault()) return sb; else break;
        case very: break;
     }
     sb.append("policy=" + this);
     return sb;
  }

  public String toString() {return getClass().getSimpleName()+"."+name();}

}
