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
import static java.util.regex.Pattern.UNICODE_CASE;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;
import static java.util.regex.Pattern.COMMENTS;

/** A Pattern, matches certain strings, that is, it is a filter on strings.
<p>
     Pattern is the abstract class in the Naomi package that allows the user
     to define patterns to be matched in target strings by instances of
     Naomi's Matcher class.  An instance of any subclass of Pattern matches
     some strings.  This class is analogous to, but quite different from, the
     {@link java.util.regex.Pattern java.util.regex.Pattern} class, whereas
     {@link Matcher naomi.Matcher} is analogous to but distinct from the
     {@link java.util.regex.Matcher java.util.regex.Matcher} class.
<p>
     A Pattern can be composed of multiple sub-patterns, which can be combined
     in sequences (concatenation) or alternations (disjunction). A pattern may
     also specify various constraints, such as how often it is allowed to
     repeat and whether it is to be matched case-sensitively or not, as well
     as various behavioral parameters that determine how the pattern matching
     algorithm will work.
<p>
     Subclassses of Pattern provide additional functionality, such as:
<p>
  <div style="margin-left : 3em; margin-right : 2em; text-indent: -1em;">

  <b>*</b> Defining patterns that match common built-in subsets of characters
	     (e.g., white space or non-Latin alphabetics);

<p>
  <b>*</b> Defining patterns that match user-specified sequences of characters;

<p>
  <b>*</b> Specifying boundary conditions for a match (e.g., matching
	     substrings that occur at the start or end of a word or line);

<p>
  <b>*</b> Combining Patterns by concatenation or disjunction.

  </div>

<p>
     For example, subclasses of the abstract {@link CharClass} allow the user
     to define subsets of characters that are derived from: built-in
     (aka "BuiltIn") sets of characters, ranges of characters, strings of
     characters, Java Collections or Iterators, or the unions, intersections,
     or complements of other CharClasses.
<p>
     Instances of this class are not safe for use by multiple concurrent threads.

 * @author      Norman Shapiro
 * @author      Jeff Rothenberg
*/

public abstract class Pattern implements AlterationListener, RopeClient
{
/**

    This Integer constant (equal to <code>null</code>) can be supplied as an
    argument to {@link #setMaxCount setMaxCount} or {@link #setMinAndMaxCount
    setMinAndMaxCount} to specify that there is no upper bound on the number
    of repetitions of this pattern that will be matched.

*/
  public static final Integer UNBOUNDED=null;
  private static int COMPILE_FLAGS=UNICODE_CASE|UNICODE_CHARACTER_CLASS;

  private static int serialSource=0;
  private int serial=serialSource++;

  private java.util.regex.Pattern pattern;
  private String regularExpression;
  final private String name;
  private List<AlterationListener> listeners;
  private Count count=new Count();
  private Policy policy=Policy.greedy;
  private String moniker;
  private Pretty pretty=new Pretty(false);
  private String extraComment="";
  private boolean caseSensitive=true;
  private List<Pattern> kids;

  public Pattern()
  {
     name=computeName();
  }

  /** Copy this Pattern.
  @return  A Pattern identical to this Pattern AT THE TIME OF INVOCATION.
   Except that cached data will not be preserved, as if {@link #altered()}
   were invoked.
   Normally calls {@link #copyTo}.

  @return An instance of Pattern  initialized by this Pattern.
  */
  public abstract Pattern copy();

  /** Copies data from this to other.
  Normally called by {@link #copy}.
  Overriders should normally call super.copyTo().
  @return other
*/

  public Pattern copyTo(Pattern other)
  {
     if(listeners != null)
        other.listeners=new ArrayList<AlterationListener>(listeners);
     other.count=count;
     other.policy=policy;
     other.pretty=new Pretty(pretty);
     other.extraComment=extraComment;
     other.caseSensitive=caseSensitive;
     return other;
  }


  abstract Rope getInnerRope();

  String getNam() {return name;}

/**
  Normally, will not be used by most programmers.

  @return The regular expression corresponding to this Pattern. The expression
	  returned will usually be unfit for human consumption, but see {@link
	  #setPretty}

  @throws ReuseException

*/

  public String getRegularExpression()
  {
     if(regularExpression != null) return regularExpression;
     kosherKids();
     Rope rope=getRope();
     pretty.check();
     regularExpression=rope.toString(null,pretty,"").toString();
     return regularExpression;
  }

/**

    Creates a matcher that will match the given input against this pattern.
    It will not be particularly inefficient to repeatedly invoke this method
    for the same pattern.

 </p>

    @param input The character sequence to be matched

    @return A new matcher for this pattern

    @throws ReuseException

*/

  public Matcher matcher(CharSequence input)
  {
      return new Matcher(this,getPattern().matcher(input));
  }

  /** Equivalent to matcher.{@link Matcher#group }(this).*/
  public String getMatch(Matcher matcher)
  {
     return matcher.group(this);
  }

/**
  @return true if and only this pattern matches all of input

  @throws ReuseException
*/
  public boolean matches(CharSequence input) {return matcher(input).matches();}


/**
   Replaces every subsequence of the input sequence that matches the
   pattern with the given replacement string.

   @param  replacement
           The literal replacement string. There are no escape characters.

   @return  The string constructed by replacing each matching subsequence
               by the replacement string

   @throws ReuseException
*/

    public String replaceAll(CharSequence input,String replacement)
    {
        Matcher matcher=matcher(input);
        return matcher.replaceAll(replacement);

    }

/**
   @return the first subsequence of input matched by this pattern, or null
		if there is no such subsequence

   @throws ReuseException
*/

  public String getMatchedPart(CharSequence input)
  {
     Matcher matcher=matcher(input);
     if(matcher.find()) return matcher.group();
     else return null;
  }


/**
     Replaces the first subsequence of the input sequence that matches this
     pattern with the given replacement string.

     @param replacement The literal replacement string. There are no escape
			     characters.

     @return The string constructed by replacing the first matching
              subsequence by the replacement string.

     @throws ReuseException
*/

  public String replaceFirst(CharSequence input,String replacement)
  {
     return matcher(input).replaceFirst(replacement);
  }

  java.util.regex.Pattern getPattern() // 3 passes
  {
     if(pattern != null) return pattern;
     if(pretty.pretty)
     {
        pattern=java.util.regex.Pattern.compile
           (getRegularExpression(),COMPILE_FLAGS+COMMENTS);
     }
     else
     {
        pattern=java.util.regex.Pattern.compile
           (getRegularExpression(),COMPILE_FLAGS);
     }
     return pattern;
  }

  /**
     The default is Policy.greedy
     @return this Pattern

  */
  public Pattern setPolicy(Policy policy)
  {this.policy=policy;altered();return this;}

  public Policy getPolicy() {return policy;}

  /**
     The default is 1;  max==null means no maximum.
     @return this Pattern
  */
  public Pattern setMaxCount(Integer max)
  {count.setMax(max);altered();return this;}
  public Integer getMaxCount() {return count.getMax();}

  /** @return this Pattern*/
  public Pattern setMaxCountToUnbounded() {return setMaxCount(UNBOUNDED);}

  /**
     The default is 1.
     @return this Pattern
  */
  public Pattern setMinCount(int min) {count.setMin(min);altered();return this;}
  public int getMinCount() {return count.getMin();}

  /**   @return this Pattern */
  public Pattern setMinAndMaxCount(int min,Integer max)
  {
     setMinCount(min);
     return setMaxCount(max);
  }


/**
   Is public only because Java requires it to be so.
*/

  public Pattern altered(Pattern src)
  {
     pattern=null;
     kids=null;
     regularExpression=null;
     notifyAlterationListeners(src);
     return this;
  }

/**
   This should be called when any change is made or occurs which might affect
   which strings this Pattern matches.  It is called automatically whenever
   any method or subclass method whose name begins with "set", is called.
   When it is called, any data that have been cached for efficiency will be
   removed.

<p>

   The programmer must keep in mind that whenever a Pattern is instantiated,
   its instantiating arguments may be references to Objects that may
   subsequently be altered invisibly to Naomi. Whenever such alterations occur,
   the existing Pattern will not reflect the new values of the altered
   instantiating Objects, potentially invalidating the Pattern.  It will
   therefore need to be recompiled, as if it were being reinstantiated with
   the new values of its original arguments.  Calling <code>altered</code>
   marks a Pattern as requiring such recompilation, which will then be done
   automatically, as required, before the Pattern is applied again.  Changes
   to a Pattern that are made via one of Naomi's "set" methods will call
   <code>altered</code> automatically, but any other alteration requires the
   programmer to call it explicitly.

  @return this Pattern

</dl>
<pre>
<b>Example 1:</b>

	Set&lt;Character&gt; foobar;
	...
	Pattern pattern = new CollectionCharClass(foobar);
	Matcher matcher = pattern.matcher("xyz");
	...
	foobar.add('x');
	pattern.altered(); <b>// Omitting this line would be bug</b>
	Matcher matcher = pattern.matcher("xyz");

<b>Example 2:</b>

	StringBuilder foobar;
	...
	Pattern pattern = new CharSequencePattern(foobar);
	Matcher matcher = pattern.matcher("xyz");
	...
	foobar.append("x");
	pattern.altered(); <b>// Omitting this line would be bug</b>
	Matcher matcher = pattern.matcher("xyz");
</pre>

If the programmer does not intend to alter an instantiating argument, a way to
insure against accidentally altering it would be to copy it.

<p>
<pre>
This in example 1:

    Pattern pattern = new CollectionCharClass(new HashSet&lt;String&gt;foobar));

and in example 2:

    Pattern pattern = new CharSequencePattern(foobar.toString());
</pre>

  @return this Pattern

*/

  public Pattern altered() {altered(this);return this;}

  public Pattern notifyAlterationListeners(Pattern src)
  {
     if(listeners==null) return this;
     for(AlterationListener alterListener:listeners)
        alterListener.altered(src);
     return this;
  }

  public Pattern addAlterationListener(AlterationListener alterListener)
  {
    if(listeners==null) listeners=new ArrayList<AlterationListener>();
    listeners.add(alterListener);
    return this;
  }

  public Pattern removeAlterationListener(AlterationListener alterListener)
  {
     if(listeners != null) listeners.remove(alterListener);
     return this;
  }

  public String toString(Verbose verbose)
  {
     if(verbose==Verbose.no) return toString();
     StringBuilder sb=new StringBuilder();
     sb.append(toString());
     count.toString(sb,verbose);
     if(policy != Policy.greedy || verbose==Verbose.very)
        sb.append(" policy="+policy);
     if(isCaseSensitive()) sb.append(" case sensitive ");
     return sb.toString();
   }

   /**
     * <p>Returns the string representation of this pattern. The
     * string representation of a <code>Pattern</code> contains information
     * that may be useful for debugging. The exact format is unspecified.
     *But see {@link #setMoniker setMonkier}.
     * @return  The string representation of this pattern
  */
  public String toString()
  {
     StringBuilder ans=new StringBuilder();
     if(getMoniker() != null)
        ans.append(getMoniker()+ " ");
     ans.append(getClass().getSimpleName());
     return ans.toString();
  }

  private String computeName()
  {
   ;
     String ans=Integer.toString(serial);
     ans="a"+ans;
     return ans;
  }

  Pattern checkUsed(Pattern other)
  {
     if(!getKids().contains(other))
        throw new UnusedException(this,other);
     return this;
  }

  int getIndex(Pattern other)// 1 based
  {
     int ans=getKids().indexOf(other);
     if(ans<0)throw new UnusedException(this,other);
     return ans+1;
  }

  /** moniker may be any {@link String}.
  * Defaults to <tt>null</tt>.
  * If moniker != <tt>null</tt> then it will appear in
  * {@link #toString toString} the message of some exceptions, and, possibly,
  * in the comments of {@link #getRegularExpression}. Its intended use is for
  * debugging.
  */
  public Pattern setMoniker(String moniker)
  {
     this.moniker=moniker;
     return this;
  }

  /** The caller may modify the returned list.
  @return A {@link List}, in left to right order,
  of the Patterns recursively used by this Pattern. */
  public List<Pattern> getUsedPatterns()
  {
     return new ArrayList<Pattern>(getKids());
  }

  //Map<Pattern,Integer> getDepthMap() //depths are 0 based
  //{
      //final Map<Pattern,Integer>ans=new HashMap<Pattern,Integer>();
      //assignDepths(ans,0);
      //return ans;
   //}

  //Pattern assignDepths(Map<Pattern,Integer> ans, int myDepth)
  //{
     //ans.put(this,myDepth);
     //return this;
  //}


  public String getMoniker() {return moniker;}

  static class NameException extends RuntimeException
  {
     NameException(String message) {super(message);}
  }

  public boolean isPretty() {return pretty != null && pretty.pretty;}

/**

  Determines whether {@link #getRegularExpression} returns a more human
  readable, or, at least, a less human unreadable String. The default value
  produces a dense string.

  @return this Pattern.

*/
  public Pattern setPretty(Pretty pretty)
  {
     altered();
     this.pretty=pretty;
     return this;
  }

  /**Sets extra comments that will be generated by {@link #getRegularExpression}
  when {@link #isPretty} is true. May contain multiple lines. Empty lines are
  ignored so include white space in lines that should appear empty. Defaults to
  the empty String.

  BUG: Presently this method has no effect.
  @return this Pattern*/
  Pattern setExtraComment(String extraComment)
  {
     this.extraComment=extraComment;
     return this;
  }

  String getExtraComment() {return extraComment;}

    /**
     * Splits the given input sequence around matches of this pattern.
     *
     * <p> The array returned by this method contains each substring of the
     * input sequence that is terminated by another subsequence that matches
     * this pattern or is terminated by the end of the input sequence.  The
     * substrings in the array are in the order in which they occur in the
     * input.  If this pattern does not match any subsequence of the input then
     * the resulting array has just one element, namely the input sequence in
     * string form.
     *
     * <p> The <tt>limit</tt> parameter controls the number of times the
     * pattern is applied and therefore affects the length of the resulting
     * array.  If the limit <i>n</i> is greater than zero then the pattern
     * will be applied at most <i>n</i>&nbsp;-&nbsp;1 times, the array's
     * length will be no greater than <i>n</i>, and the array's last entry
     * will contain all input beyond the last matched delimiter.  If <i>n</i>
     * is non-positive then the pattern will be applied as many times as
     * possible and the array can have any length.  If <i>n</i> is zero then
     * the pattern will be applied as many times as possible, the array can
     * have any length, and trailing empty strings will be discarded.
     *
     * <p><tt>new
     * CharSequencePattern(string).split("boo:and:foo",limit)</tt>, for example,
     * yields the following results with these parameters:
     *
     * <blockquote><table cellpadding=1 cellspacing=0
     *              summary="Split examples showing regex, limit, and result">
     * <tr><th><P align="left"><i>string&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th><P align="left"><i>limit&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th><P align="left"><i>result&nbsp;&nbsp;&nbsp;&nbsp;</i></th></tr>
     * <tr><td align=center>:</td>
     *     <td align=center>2</td>
     *     <td><tt>{ "boo", "and:foo" }</tt></td></tr>
     * <tr><td align=center>:</td>
     *     <td align=center>5</td>
     *     <td><tt>{ "boo", "and", "foo" }</tt></td></tr>
     * <tr><td align=center>:</td>
     *     <td align=center>-2</td>
     *     <td><tt>{ "boo", "and", "foo" }</tt></td></tr>
     * <tr><td align=center>o</td>
     *     <td align=center>5</td>
     *     <td><tt>{ "b", "", ":and:f", "", "" }</tt></td></tr>
     * <tr><td align=center>o</td>
     *     <td align=center>-2</td>
     *     <td><tt>{ "b", "", ":and:f", "", "" }</tt></td></tr>
     * <tr><td align=center>o</td>
     *     <td align=center>0</td>
     *     <td><tt>{ "b", "", ":and:f" }</tt></td></tr>
     * </table></blockquote>
     *
     *
     * @param  input
     *         The character sequence to be split
     *
     * @param  limit
     *         The result threshold, as described above
     *
     * @return  The array of strings computed by splitting the input
     *          around matches of this pattern
     *
     *	  @throws ReuseException
     */
    public String[] split(CharSequence input, int limit)
    {
        return getPattern().split(input,limit);
    }

    /**
     * Splits the given input sequence around matches of this pattern.
     *
     * <p> This method works as if by invoking the two-argument {@link
     * #split(java.lang.CharSequence, int) split} method with the given input
     * sequence and a limit argument of zero.  Trailing empty strings are
     * therefore not included in the resulting array. </p>
     *
     * <p><tt>new CharSequencePattern(string).split("boo:and:foo")</tt>, for
     * example, yields the following results with these expressions:
     *
     * <blockquote><table cellpadding=1 cellspacing=0
     *              summary="Split examples showing regex and result">
     * <tr><th><P align="left"><i>string&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th><P align="left"><i>result</i></th></tr>
     * <tr><td align=center>:</td>
     *     <td><tt>{ "boo", "and", "foo" }</tt></td></tr>
     * <tr><td align=center>o</td>
     *     <td><tt>{ "b", "", ":and:f" }</tt></td></tr>
     * </table></blockquote>
     *
     *
     * @param  input
     *         The character sequence to be split
     *
     * @return  The array of strings computed by splitting the input
     *          around matches of this pattern
     *
     *	  @throws ReuseException
     */
    public String[] split(CharSequence input) {
        return split(input, 0);
    }

  public boolean isCaseSensitive() {return caseSensitive;}

  /** Defaults to true*/
  public Pattern setCaseSensitive(boolean caseSensitive)
  {
     if(this.caseSensitive == caseSensitive) return this;
     this.caseSensitive=caseSensitive;
     return altered();
  }


  /** Is public because Java requires*/
  public Rope getRope()
  {
     String comment=toString();
     Rope out=new PatternRope(getNam(),comment);
     Rope cse=new CaseSensitiveRope(isCaseSensitive());
     Rope cnt=new CountAndPolicyRope(count,getPolicy());
     Rope in=getInnerRope();
     NestedRope nest=new NestedRope(true,out,cse,cnt,in);
     Rope ans=nest.getRope();
     return ans;
  }

  List<Pattern> computeKids() {return Collections.singletonList(this);}

  public  List<Pattern> getKids()
  {
     if(kids==null) kids=computeKids();
      return kids;
  }

  Pattern kosherKids()
  {
     getKids();
     Set<Pattern> set=new HashSet<Pattern>();
     set.addAll(kids);
     if(set.size()< kids.size())
     {
        List<Pattern> copy=new ArrayList<Pattern>(kids);
        for(Pattern pattern: set)
           copy.remove(pattern);
        List<String> bads=new ArrayList<String>();
        for(Pattern pattern: copy)
           bads.add(pattern.toString());
        String msg=
           this + " uses " + Utilities.join(" and ",bads) + " muliple times";
        throw new ReuseException(msg);
     }
     for(Pattern pattern:kids)
     {
        if(pattern instanceof BackReferencePattern)
           ((BackReferencePattern)pattern).checkReferent(kids);
     }
     return this;
 }
}
