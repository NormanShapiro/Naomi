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

import java.util.*;

public class Utilities
{
   Utilities() {}

  static void assureEndsWithNewLine(StringBuilder ans)
  {
     int len=ans.length();
     if(len >0 && ans.charAt(len-1) == '\n')
        return;
      ans.append('\n');
   }

   static StringBuilder ditto(int count,CharSequence seq,StringBuilder ans)
   {
     if(ans==null) ans=new StringBuilder();
     while(count-->0)
       ans.append(seq);
     return ans;
   }

   static String join(String delimiter,Object[] strings)
   {
     if(strings==null)
        return "";
     int len=strings.length;
     String ans="";
     for(int i=0;i<len;++i)
     {
        if(i>0)
           ans += delimiter;
        ans += strings[i];
      }
      return ans;
    }

  static String join(String delimiter,List<String> strings)
  {
     return join(delimiter,strings.toArray());
  }

  static Object search_
  (
     Pattern pattern,List<? extends Object> objects,
     boolean nullOK,boolean firstOK
  )
  {
     //@Norm.A.p((" "+("pattern.getClass()") +" = " + (pattern.getClass()))/*~*/+(" " + "Utilities.pj" + ":" + 51 + " "));
     //@Norm.A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/+(" " + "Utilities.pj" + ":" + 52 + " "));
     //@Norm.A.p((" "+("objects") +" = " + (objects))/*~*/+(" " + "Utilities.pj" + ":" + 53 + " "));
     Object first=null;
     List<String> hits=new java.util.ArrayList<String>();
     for(Object ob:objects)
     {
        String cur=ob.toString();
       //@ Norm.A.p(cur+(" " + "Utilities.pj" + ":" + 59 + " "));
        if(!pattern.matches(cur))
           continue;
         //@Norm.A.p(cur+(" " + "Utilities.pj" + ":" + 62 + " "));
         if(firstOK)
           return ob;
         if(first == null)
           first=ob;
         hits.add(cur);
         //@Norm.A.p((" "+("cur") +" = " + (cur))/*~*/+(" "+("first") +" = " + (first))/*~*/+(" "+("hits") +" = " + (hits))/*~*/+(" " + "Utilities.pj" + ":" + 68 + " "));

     }
     if(first ==null)
     {
          if(nullOK)
              return null;
           else
              throw new SearchException("No Match");
     }
     else//firstOK must be false
     {
        if(hits.size() <=1 )//then hits.size()==1
           return first;
        else
           throw new SearchException("Multiple Matches: "+hits);
     }
  }

/**

Find an Enum constant matched by the specified {@link Pattern}.

<p>

     Although this method can find any constant name in any Enum, it is most
     useful for finding constants in large Enums or sets of Enums.  To
     find constants in specified BuiltIn Enum
     classes that implement Naomi's {@link BuiltInInterface}, use
	{@link #find(Pattern pattern,boolean nullOK,Action action)}.

<p>

     Although this method can find an Enum matching any Naomi {@link Pattern},
     the {@link GlobPattern} has been provided to allow the user to search for
     Enum names in a way that is analogous to searching for filenames in Unix.
<p>
	Note the use of ".class" in the examples below, to refer
	to class arguments.

  @param pattern An instance of {@link Pattern} to be matched by an enum constant

  @param nullOK If no constant is found, return null instead of throwing a
  {@link SearchException}

  @param action Action to take if multiple matches are found

  @param enums Enums whose constants are to be searched for <br/>

  @return null or a constant, matched by pattern, of one of the enums

  @throws ReuseException
  @throws SearchException

</dl>
<b>Examples:</b>
<pre>
    Pattern pattern=new GlobPattern("p*");
    Enum<?> ans =
      Utilities.find(pattern, false, Action.last, Policy.class);

</pre>

will return Policy.positiveLookbehind

<p>
     As an additional example:

<p>
<pre>
    Pattern glob=new GlobPattern("gre*").setCaseSensitive(false);
    BuiltInInterface const1 =
	Utilities.find(glob, true, Action.last, UniScriptBuiltIn.class);
</pre>

returns UniScriptBuiltIn.GREEK.

*/

  @SafeVarargs
  public static Enum<?> find
  (Pattern pattern,boolean nullOK,Action action,Class<? extends Enum<?>>...enums)
  {
     List<Enum<?>> hits=new ArrayList<Enum<?>>();
     for(Class<? extends Enum<?>> enm:enums)
     for(Enum<?> constant:enm.getEnumConstants())
     {
        if(!pattern.matches(constant.name()))
           continue;
        hits.add(constant);
        switch(action)
        {
           case first:    return hits.get(0);
           case last:     break;
           case nul:      if(hits.size()>= 2) return null; break;
           case exception:break;
        }
     }

     if(hits.size() == 0)
     {
        if(nullOK) return null;
        else throw new SearchException("No hits");
     }
     else if(hits.size()==1)
     {
        return hits.get(0);
     }
     else switch(action)
     {
       case first:     throw new Bug();
       case last:      return hits.get(hits.size()-1);
       case nul:       throw new Bug();
       case exception: throw new SearchException("Multiple Matches: "+hits);
     }
     throw new Bug();
  }

/**

  Find a BuiltIn Enum constant of
  {@link CoreBuiltIn}, {@link  LocaleBuiltIn},
  {@link UniBlockBuiltIn}, or {@link UniScriptBuiltIn} (searched in that order),
  matched by the specified {@link Pattern}.

<p>

     Although this method can find an Enum matching any Naomi {@link Pattern},
     the {@link GlobPattern} class has been provided to allow the user to
     search for Enum names in a way that is analogous to searching for
     filenames in Unix.

  @param pattern An instance of {@link Pattern} to be matched by an enum constant

  @param nullOK If no constant is found, return null instead of throwing a
  {@link SearchException}

  @param action Action to take if multiple matches are found
  @return null or a constant  matched by pattern, of one of the enums

  @throws ReuseException
  @throws SearchException

</dl>
<b>Example:</b>
<pre>
    BuiltInInterface const =
      Utilities.find(new GlobPattern("Vis*").setCaseSensitive(false),
		       false, Action.exception);
</pre>

will set const to CoreBuiltIn.visible.  To create a {@link CharClass} using
this constant:

<pre>
     new BuiltInCharClass(const);
</pre>

*/

  public static BuiltInInterface find
  (Pattern pattern,boolean nullOK,Action action)
  {
     return (BuiltInInterface)find
     (
        pattern,nullOK,action,
        //@AnyBuiltIn.class,
        CoreBuiltIn.class,
        LocaleBuiltIn.class,
        UniBlockBuiltIn.class,
        UniScriptBuiltIn.class
      );
  }

  /**Thrown when no or more than one Object is inappropriately found

  */
  public static class SearchException extends RuntimeException
  {
     SearchException(String reason)
     {
        super(reason);
     }
  }




}
