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
 * 
   This class (which is analogous to but distinct from {@link
   java.util.regex.Matcher java.util.regex.Matcher}) is the 
   <a href="./package-info.html"> Naomi package's </a>
   engine that performs match operations on a {@link java.lang.CharSequence
   </code>character sequence<code>} by interpreting a {@link Pattern}.
 
 <p>

  A Matcher is created from a {@link Pattern} by invoking the Pattern's {@link
  Pattern#matcher matcher} method.  Once created, a Matcher can be used to
  perform three different kinds of match operations:
 
  <ul>
 
    <li><p> The {@link #matches matches} method attempts to match the entire
    input sequence against the pattern.  </p></li>
 
  <li><p>

    The {@link #lookingAt lookingAt} method attempts to match the input
    sequence, starting at the beginning of the {@link #region region}, against
    the pattern.

  </p></li>
 
    <li><p> The {@link #find find} method scans the input sequence looking for
    the next subsequence that matches the pattern.  </p></li>
 
  </ul>
 
  <p> Each of these methods returns a boolean indicating success or failure.
  More information about a successful match can be obtained by querying the
  state of the matcher.
 
  <p> A matcher finds matches in a subset of its input called the
  <i>region</i>. By default, the region contains all of the matcher's input.
  The region can be modified via the {@link #region region} method and queried
  via the {@link #regionStart regionStart} and {@link #regionEnd regionEnd}
  methods. The way that the region boundaries interact with some pattern
  constructs can be changed. See {@link #useAnchoringBounds
  useAnchoringBounds} and {@link #useTransparentBounds useTransparentBounds}
  for more details.
 
  <p> This class also defines methods for replacing matched subsequences with
  new strings whose contents can, if desired, be computed from the match
  result.  The {@link #appendReplacement appendReplacement} and {@link
  #appendTail appendTail} methods can be used in tandem in order to collect
  the result into an existing string buffer, or the more convenient {@link
  #replaceAll replaceAll} method can be used to create a string in which every
  matching subsequence in the input sequence is replaced.
 
  <p> The explicit state of a matcher includes the start and end indices of
  the most recent successful match.  It also includes the start and end
  indices of the input subsequence captured by each <a
  href="Pattern.html#cg">capturing group</a> in the pattern as well as a total
  count of such subsequences.  As a convenience, methods are also provided for
  returning these captured subsequences in string form.
 
  <p> The explicit state of a matcher is initially undefined; attempting to
  query any part of it before a successful match will cause an {@link
  IllegalStateException} to be thrown.  The explicit state of a matcher is
  recomputed by every match operation.
 
  <p> The implicit state of a matcher includes the input character sequence as
  well as the <i>append position</i>, which is initially zero and is updated
  by the {@link #appendReplacement appendReplacement} method.
 
  <p> A matcher may be reset explicitly by invoking its {@link #reset()}
  method or, if a new input sequence is desired, its {@link
  #reset(java.lang.CharSequence) reset(CharSequence)} method.  Resetting a
  matcher discards its explicit state information and sets the append position
  to zero.
 
  <p>
   Instances of this class are not safe for use by multiple concurrent threads.

</p>
 *
 * @author      Norman Shapiro
 * @author      Jeff Rothenberg
 */

public class Matcher
{
    private final Pattern pattern;
    private final java.util.regex.Matcher matcher;
    Matcher(Pattern pattern, java.util.regex.Matcher matcher)
    {
        this.pattern=pattern;
        this.matcher=matcher;
    }




    /**
     * Returns the start index of the previous match.  </p>
     *
     * @return  The index of the first character matched
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     */
    public int start() {return matcher.start();}


    /**
     * Returns the start index of the subsequence captured by other
     * during the previous match operation.
     *
        *
     * @param  other
     * @return  The index of the first character captured by other,
     *          or <tt>-1</tt> if the match was successful but
     *           other
     *          itself did not match anything
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     *
     * @throws  UnusedException if other was not used by the
     * Pattern that gave rise to this Matcher
   */


    public int start(Pattern other)
    {
        return matcher.start(pattern.getIndex(other));
    }

    /**
     * Returns the offset after the last character matched.  </p>
     *
     * @return  The offset after the last character matched
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     */
    public int end() { return matcher.end();}

    /**
     * Returns the offset after the last character of the subsequence
     * captured by other during the previous match operation.
     *
     *
     * @return  The offset after the last character captured by other,
     *          or <tt>-1</tt> if the match was successful
     *          but other itself did not match anything
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     *
     * @throws  UnusedException if other was not used by the
     * Pattern that gave rise to this Matcher
 */

    public int end(Pattern other)
    {
        return matcher.end(pattern.getIndex(other));
    }

    /**
     * Returns the input subsequence matched by the previous match.
     *
     * <p> For a matcher <i>m</i> with input sequence <i>s</i>,
     * the expressions <i>m.</i><tt>group()</tt> and
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>start(),</tt>&nbsp;<i>m.</i><tt>end())</tt>
     * are equivalent.  </p>
     *
     * <p> Note that some patterns, for example <tt>a*</tt>, match the empty
     * string.  This method will return the empty string when the pattern
     * successfully matches the empty string in the input.  </p>
     *
     * @return The (possibly empty) subsequence matched by the previous match,
     *         in string form
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     */
    public String group() { return matcher.group(0);}


    /**
     * Returns the input subsequence captured by the given Pattern
     *during the previous
     * match operation.
     *
     * <p> If the match was successful but the pattern  failed to match
     * any part of the input sequence, then <tt>null</tt> is returned. Note
     * that some {@link Pattern}s, match the empty string.
     * This method will return the empty string when such a {@link Pattern}
     * successfully
     * matches the empty string in the input.  </p>
     *
     *
     * @return  The (possibly empty) subsequence captured by the  group
     *          during the previous match, or <tt>null</tt> if pattern
     *          failed to match part of the input
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     *

     * @throws  UnusedException if pattern was not used by the
     * Pattern that gave rise to this Matcher
  */

    public String group(Pattern pattern)
    {
        this.pattern.checkUsed(pattern);
        return matcher.group(pattern.getNam());
    }


    /**
     * Returns the number of capturing groups in this matcher's pattern.
     *
     * <p> Group zero denotes the entire pattern by convention. It is not
     * included in this count.
     *
     * <p> Any non-negative integer smaller than or equal to the value
     * returned by this method is guaranteed to be a valid group index for
     * this matcher.  </p>
     *
     * @return The number of capturing groups in this matcher's pattern
     */
    int groupCount() { return matcher.groupCount();}

    /**
     * Attempts to match the entire region against the pattern.
     *
     * <p> If the match succeeds then more information can be obtained via the
     * <tt>start</tt>, <tt>end</tt>, and <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, and only if, the entire region sequence
     *          matches this matcher's pattern
     */
    public boolean matches() { return matcher.matches();}

    /**
     * Attempts to find the next subsequence of the input sequence that matches
     * the pattern.
     *
     * <p> This method starts at the beginning of this matcher's region, or, if
     * a previous invocation of the method was successful and the matcher has
     * not since been reset, at the first character not matched by the previous
     * match.
     *
     * <p> If the match succeeds then more information can be obtained via the
     * <tt>start</tt>, <tt>end</tt>, and <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, and only if, a subsequence of the input
     *          sequence matches this matcher's pattern
     */
    public boolean find() {return matcher.find();}

    /**
     * Resets this matcher and then attempts to find the next subsequence of
     * the input sequence that matches the pattern, starting at the specified
     * index.
     *
     * <p> If the match succeeds then more information can be obtained via the
     * <tt>start</tt>, <tt>end</tt>, and <tt>group</tt> methods, and subsequent
     * invocations of the {@link #find()} method will start at the first
     * character not matched by this match.  </p>
     *
     * @throws  IndexOutOfBoundsException
     *          If start is less than zero or if start is greater than the
     *          length of the input sequence.
     *
     * @return  <tt>true</tt> if, and only if, a subsequence of the input
     *          sequence starting at the given index matches this matcher's
     *          pattern
     */
    public boolean find(int start){return matcher.find(start);}


    /**
     * Attempts to match the input sequence, starting at the beginning of the
     * region, against the pattern.
     *
     * <p> Like the {@link #matches matches} method, this method always starts
     * at the beginning of the region; unlike that method, it does not
     * require that the entire region be matched.
     *
     * <p> If the match succeeds then more information can be obtained via the
     * <tt>start</tt>, <tt>end</tt>, and <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, and only if, a prefix of the input
     *          sequence matches this matcher's pattern
     */
    public boolean lookingAt() {return matcher.lookingAt();}


    /**
     * Implements a non-terminal append-and-replace step.
     *
     * <p> This method performs the following actions: </p>
     *
     * <ol>
     *
     *   <li><p> It reads characters from the input sequence, starting at the
     *   append position, and appends them to the given string buffer.  It
     *   stops after reading the last character preceding the previous match,
     *   that is, the character at index {@link
     *   #start()}&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.  </p></li>
     *
     *   <li><p> It appends the given replacement string to the string buffer.
     *   </p></li>
     *
     *   <li><p> It sets the append position of this matcher to the index of
     *   the last character matched, plus one, that is, to {@link #end()}.
     *   </p></li>
     *
     * </ol>
     *
     *
     * <p> This method is intended to be used in a loop together with the
     * {@link #appendTail appendTail} and {@link #find find} methods.  The
     * following code, for example, writes <tt>I say that $17 + $9 = $26
     *  everyday.
     *  </tt> to the standard-output stream: </p>
     *
     * <blockquote><pre>
     Pattern digits=new BuiltInPattern(CoreBuiltIn.digit);
     digits.setMaxCount(Pattern.UNBOUNDED);
     Pattern dollars=new CharSequencePattern(" dollars");
     Pattern amount=new ConcatenatePattern(digits,dollars);
     Matcher matcher=amount.matcher
        ("I say that 17 dollars + 9 dollars = 26 dollars everyday.");
     StringBuffer buffer=new StringBuffer();
     while(matcher.find())
        matcher.appendReplacement(buffer,"$"+matcher.group(digits));
     matcher.appendTail(buffer);
     System.out.println(buffer);
     *</pre></blockquote>.
     * @param  sb
     *         The target string buffer
     *
     * @param  replacement
     *         The  literal replacement string. There are no escape characters.
     *
     * @return  This matcher
     *
     * @throws  IllegalStateException
     *          If no match has yet been attempted,
     *          or if the previous match operation failed
     *
     */
    public Matcher appendReplacement(StringBuffer sb, String replacement)
    {
        replacement=java.util.regex.Matcher.quoteReplacement(replacement);
        matcher.appendReplacement(sb,replacement);
        return this;
    }

    /**
     * Implements a terminal append-and-replace step.
     *
     * <p> This method reads characters from the input sequence, starting at
     * the append position, and appends them to the given string buffer.  It is
     * intended to be invoked after one or more invocations of the {@link
     * #appendReplacement appendReplacement} method in order to copy the
     * remainder of the input sequence.  </p>
     *
     * @param  sb
     *         The target string buffer
     *
     * @return  The target string buffer
     */
    public StringBuffer appendTail(StringBuffer sb)
    {
        return matcher.appendTail(sb);
    }

    /**
     * Replaces every subsequence of the input sequence that matches the
     * pattern with the given replacement string.
     *
     * <p> This method first resets this matcher.  It then scans the input
     * sequence looking for matches of the pattern.  Characters that are not
     * part of any match are appended directly to the result string; each match
     * is replaced in the result by the replacement string.
     *
     *
     * <p> Invoking this method changes this matcher's state.  If the matcher
     * is to be used in further matching operations then it should first be
     * reset.  </p>
     *
     * @param  replacement
     *         The literal replacement string. There are no escape characters.
     *
     * @return  The string constructed by replacing each matching subsequence
     *          by the replacement string
     */
    public String replaceAll(String replacement)
    {
        replacement=java.util.regex.Matcher.quoteReplacement(replacement);
        return matcher.replaceAll(replacement);
    }

    /**
     * Replaces the first subsequence of the input sequence that matches the
     * pattern with the given replacement string.
     *
     * <p> This method first resets this matcher.  It then scans the input
     * sequence looking for a match of the pattern.  Characters that are not
     * part of the match are appended directly to the result string; the match
     * is replaced in the result by the replacement string.
     *
     * <p> Given the regular expression <tt>dog</tt>, the input
     * <tt>"zzzdogzzzdogzzz"</tt>, and the replacement string
     * <tt>"cat"</tt>, an invocation of this method on a matcher for that
     * expression would yield the string <tt>"zzzcatzzzdogzzz"</tt>.  </p>
     *
     * <p> Invoking this method changes this matcher's state.  If the matcher
     * is to be used in further matching operations then it should first be
     * reset.  </p>
     *
     * @param  replacement
     *  The literal replacement string. There are no escape characters.
     * @return  The string constructed by replacing the first matching
     *          subsequence by the replacement string.
     */
    public String replaceFirst(String replacement)
    {
       replacement=java.util.regex.Matcher.quoteReplacement(replacement);
       return matcher.replaceFirst(replacement);
    }

    /**
     * Sets the limits of this matcher's region. The region is the part of the
     * input sequence that will be searched to find a match. Invoking this
     * method resets the matcher, and then sets the region to start at the
     * index specified by the <code>start</code> parameter and end at the
     * index specified by the <code>end</code> parameter.
     *
     * <p>Depending on the transparency and anchoring being used (see
     * {@link #useTransparentBounds useTransparentBounds} and
     * {@link #useAnchoringBounds useAnchoringBounds}), certain constructs such
     * as anchors may behave differently at or around the boundaries of the
     * region.
     *
     * @param  start
     *         The index to start searching at (inclusive)
     * @param  end
     *         The index to end searching at (exclusive)
     * @throws  IndexOutOfBoundsException
     *          If start or end is less than zero, if
     *          start is greater than the length of the input sequence, if
     *          end is greater than the length of the input sequence, or if
     *          start is greater than end.
     * @return  this matcher
     * @since 1.5
     */
    public Matcher region(int start, int end)
    {
        matcher.region(start,end);
        return this;
    }

    /**
     * Reports the start index of this matcher's region. The
     * searches this matcher conducts are limited to finding matches
     * within {@link #regionStart regionStart} (inclusive) and
     * {@link #regionEnd regionEnd} (exclusive).
     *
     * @return  The starting point of this matcher's region
     * @since 1.5
     */
    public int regionStart()
    {
        return matcher.regionStart();
    }

    /**
     * Reports the end index (exclusive) of this matcher's region.
     * The searches this matcher conducts are limited to finding matches
     * within {@link #regionStart regionStart} (inclusive) and
     * {@link #regionEnd regionEnd} (exclusive).
     *
     * @return  the ending point of this matcher's region
     * @since 1.5
     */
    public int regionEnd() { return matcher.regionEnd();}

    /**
     * Queries the transparency of region bounds for this matcher.
     *
     * <p> This method returns <tt>true</tt> if this matcher uses
     * <i>transparent</i> bounds, <tt>false</tt> if it uses <i>opaque</i>
     * bounds.
     *
     * <p> See {@link #useTransparentBounds useTransparentBounds} for a
     * description of transparent and opaque bounds.
     *
     * <p> By default, a matcher uses opaque region boundaries.
     *
     * @return <tt>true</tt> iff this matcher is using transparent bounds,
     *         <tt>false</tt> otherwise.
     * @see java.util.regex.Matcher#useTransparentBounds(boolean)
     * @since 1.5
     */
    public boolean hasTransparentBounds(){return matcher.hasTransparentBounds();}

    /**
     * Sets the transparency of region bounds for this matcher.
     *
     * <p> Invoking this method with an argument of <tt>true</tt> will set this
     * matcher to use <i>transparent</i> bounds. If the boolean
     * argument is <tt>false</tt>, then <i>opaque</i> bounds will be used.
     *
     * <p> Using transparent bounds, the boundaries of this
     * matcher's region are transparent to lookahead, lookbehind,
     * and boundary matching constructs. Those constructs can see beyond the
     * boundaries of the region to see if a match is appropriate.
     *
     * <p> Using opaque bounds, the boundaries of this matcher's
     * region are opaque to lookahead, lookbehind, and boundary matching
     * constructs that may try to see beyond them. Those constructs cannot
     * look past the boundaries so they will fail to match anything outside
     * of the region.
     *
     * <p> By default, a matcher uses opaque bounds.
     *
     * @param  b a boolean indicating whether to use opaque or transparent
     *         regions
     * @return this matcher
     * @see java.util.regex.Matcher#hasTransparentBounds
     * @since 1.5
     */
    public Matcher useTransparentBounds(boolean b)
    {
        matcher.useTransparentBounds(b);
        return this;
    }

    /**
     * Queries the anchoring of region bounds for this matcher.
     *
     * <p> This method returns <tt>true</tt> if this matcher uses
     * <i>anchoring</i> bounds, <tt>false</tt> otherwise.
     *
     * <p> See {@link #useAnchoringBounds useAnchoringBounds} for a
     * description of anchoring bounds.
     *
     * <p> By default, a matcher uses anchoring region boundaries.
     *
     * @return <tt>true</tt> iff this matcher is using anchoring bounds,
     *         <tt>false</tt> otherwise.
     * @see java.util.regex.Matcher#useAnchoringBounds(boolean)
     * @since 1.5
     */
    public boolean hasAnchoringBounds() {return matcher.hasAnchoringBounds();}


    /**
     * Sets the anchoring of region bounds for this matcher.
     *
     * <p> Invoking this method with an argument of <tt>true</tt> will set this
     * matcher to use <i>anchoring</i> bounds. If the boolean
     * argument is <tt>false</tt>, then <i>non-anchoring</i> bounds will be
     * used.
     *
     * <p> Using anchoring bounds, the boundaries of this
     * matcher's region match anchors such as ^ and $.
     *
     * <p> Without anchoring bounds, the boundaries of this
     * matcher's region will not match anchors such as ^ and $.
     *
     * <p> By default, a matcher uses anchoring region boundaries.
     *
     * @param  b a boolean indicating whether or not to use anchoring bounds.
     * @return this matcher
     * @see java.util.regex.Matcher#hasAnchoringBounds
     * @since 1.5
     */
    public Matcher useAnchoringBounds(boolean b)
    {
        matcher.useAnchoringBounds(b);
        return this;
    }

    /**
     * <p>Returns the string representation of this matcher. The
     * string representation of a <code>Matcher</code> contains information
     * that may be useful for debugging. The exact format is unspecified.
     *
     * @return  The string representation of this matcher
     * @since 1.5
     */
    public String toString() {return matcher.toString();}

    /**
     * <p>Returns true if the end of input was hit by the search engine in
     * the last match operation performed by this matcher.
     *
     * <p>When this method returns true, then it is possible that more input
     * would have changed the result of the last search.
     *
     * @return  true iff the end of input was hit in the last match; false
     *          otherwise
     * @since 1.5
     */
    public boolean hitEnd() { return matcher.hitEnd();}

    /**
     * <p>Returns true if more input could change a positive match into a
     * negative one.
     *
     * <p>If this method returns true, and a match was found, then more
     * input could cause the match to be lost. If this method returns false
     * and a match was found, then more input might change the match but the
     * match won't be lost. If a match was not found, then requireEnd has no
     * meaning.
     *
     * @return  true iff more input could change a positive match into a
     *          negative one.
     * @since 1.5
     */
    public boolean requireEnd() { return matcher.requireEnd();}


    /**
     * Resets this matcher.
     *
     * <p> Resetting a matcher discards all of its explicit state information
     * and sets its append position to zero. The matcher's region is set to the
     * default region, which is its entire character sequence. The anchoring
     * and transparency of this matcher's region boundaries are unaffected.
     *
     * @return  This matcher
     */
    public Matcher reset()
    {
        matcher.reset();
        return this;
    }

    /**
     * Resets this matcher with a new input sequence.
     *
     * <p> Resetting a matcher discards all of its explicit state information
     * and sets its append position to zero.  The matcher's region is set to
     * the default region, which is its entire character sequence.  The
     * anchoring and transparency of this matcher's region boundaries are
     * unaffected.
     *
     * @param  input
     *         The new input character sequence
     *
     * @return  This matcher
     */
    public Matcher reset(CharSequence input)
    {
        matcher.reset(input);
        return this;
    }



}
