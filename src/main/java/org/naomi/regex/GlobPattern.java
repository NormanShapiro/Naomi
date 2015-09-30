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
/**

    A GlobPattern is a {@link Pattern} that behaves like a
    <a href="http://en.wikipedia.org/wiki/Glob_(programming)">
    Unix shell filename matching pattern</a>.  

<p>

    GlobPatterns are effectively (though not technically) a highly restricted
    subset of <a href="http://en.wikipedia.org/wiki/Regular_expression">
    regular expression ("RE")</a>.  They have their own syntax, which is
    distinct from (but may be confusingly similar to) RE syntax.  Although
    Naomi is intended to shield the programmer from RE syntax, GlobPattern
    reintroduces this restricted (and distinct) subset of that syntax as a
    convenience for matching relatively simple strings.

<p>

    For example, a GlobPattern may be used to match filenames, function names,
database field names, or any other set of strings that conform to fairly
simple patterns.  Or it may be used as the <code>pattern</code> argument to
the {@link Utilities#find Utilities.find} methods in order to find a desired
Enum constant in a given set of Enum classes (such as a specific character
class constant in the various BuiltIn Enum classes that implement Naomi's
{@link BuiltInInterface}).

*/

public class GlobPattern extends ConcatenatePattern
{
  private CharSequence globee;
  private Character escape='\\';
  private boolean ok=false;

  public GlobPattern() {}

/**

Construct a GlobPattern based on the pattern syntax specified by the given
{@link CharSequence}.

<p>

An asterisk ('*') in the <code>globee</code> argument matches any sequence of
characters. A '?'  matches any single character.  A set of characters enclosed
in square brackets with no spaces between them (e.g., '[abx]') or a range
denoted by two characters (appearing in ascending order in the collating
sequence) with a hyphen between them in square brackets (e.g., '[a-f]')
matches any one of the denoted characters. The escape character (which
defaults to '\') escapes the character that follows it, making it possible to
search for metacharacters, e.g., using \* or \[.

*/

  public GlobPattern(CharSequence globee)
  {
     setGlobee(globee);
  }

 public  GlobPattern setGlobee(CharSequence globee)
  {
     this.globee=globee;
     clear();
     return this;
  }

/**
  Default is \ (A value of null means no escape). 
*/
  public GlobPattern setEscape(Character escape)
  {
     this.escape=escape;
     clear();
     return this;
  }

  public Pattern altered()
  {
     ok=false;
     return super.altered();
  }

  public CharSequence getGlobee() {return globee;}
  public Character getEscape(){return escape;}


  java.util.regex.Pattern getPattern()
  {
     if(!ok)
        fix();
     return super.getPattern();
  }

  Rope getInnerRope()
  {
     if(!ok) fix();
     return super.getInnerRope();
  }

  private void fix()
  {
     new GlobParse(this).doit();
     ok=true;
  }

}

class GlobParse
{
  private final GlobPattern glob;
  private final String globee;
  private final int len;
  final Character escape;
  private final StringBuilder buf=new StringBuilder();
  int index=0; //index of first unseen char

  GlobParse(GlobPattern glob)
  {
     this.glob=glob;
     this.globee=glob.getGlobee().toString();
     this.escape=glob.getEscape();
     len=this.globee.length();

  }

  void doit()
  {
     while(next())
        ;
     update();
  }

  private void update()
  {
     if(buf.length()>0)
        glob.add(new CharSequencePattern(buf.toString()));
      buf.setLength(0);
  }

  boolean next()
  {
     if(index>=len)
        return false;
     char cur=globee.charAt(index++);
     if(cur=='?')
     {
        update();
        glob.add(new BuiltInCharClass(CoreBuiltIn.any).setMinCount(0));
     }
     else if(cur=='*')
     {
        update();
        glob.add(new BuiltInCharClass(CoreBuiltIn.any).setMinAndMaxCount(0,null));

     }
     else if(escape != null && escape.charValue()==cur)
     {
        if(index>=len)
           throw new GlobParseException("Terminating Escape Character");
         buf.append(globee.charAt(index++));
     }
     else if(cur=='[')
     {
        int ix=globee.indexOf("]",index);
        if(ix==index && index<len)//to account for []foobar]
           ix=globee.indexOf("]",index+1);
        if(ix<0)
           throw new GlobParseException("Unterminated '['");
        update();
        brace(ix);
     }
     else
        buf.append(cur);
     return true;
   }

   void updateBrace(List<CharClass> list)
   {
     if(buf.length()>0)
        list.add(new ExplicitCharClass(buf.toString()));
      buf.setLength(0);
   }

   void brace(int lastIndex)
   {
     update();
     List<CharClass> list=new ArrayList<CharClass>();
     glob.add(new UnionCharClass(list));

     int startIndex=index;
     for(;;)
     {
        char cur=globee.charAt(index++);
        if(cur==']' && index> startIndex+1)
        {
           updateBrace(list);
           return;
        }
        else if(cur=='-' && index>startIndex && index<lastIndex)
        {
           char one=buf.charAt(buf.length()-1);
           buf.setLength(buf.length()-1);
           updateBrace(list);
           char two=globee.charAt(index++);
           list.add(new IntervalCharClass(one,two));
        }
        else
           buf.append(cur);
     }
  }




}

class GlobParseException extends RuntimeException
{

  GlobParseException(String message)
  {
     super(message);
  }
}




