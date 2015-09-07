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
/**
Provides the power of regular expressions without regular expressions.

<blockquote>
Some people, when confronted with a problem, think, "I know, I'll use regular
expressions." Now they have two problems. 
<footer style="margin-left:70%;">- Jaimie Zawinski</footer>
</blockquote>
<p>
	Naomi is a package that enables Java programmers to search for and
	optionally replace textual patterns in documents or strings.  Simple
	tasks of this sort--such as systematically changing multiple filenames
	or modifying text in documents--are often performed using inline or
	one-shot scripts written using tools such as awk, sed, or Perl, all of
	which use (somewhat different) variants of 
<a href="http://en.wikipedia.org/wiki/Regular_expression"> regular expression ("RE")</a>

	syntax. The standard

	<a href="http://docs.oracle.com/javase/7/docs/api/java/util/regex/package-summary.html"> java.util.regex package</a>

	provides its own set of RE tools for use in Java.
	But performing complex searching and match-and-replace tasks or
	performing such tasks repetitively with minor variations can be quite
	difficult using REs, and RE syntax is inherently alien to Java's
	object-oriented style of programming.  Naomi offers a much more
	transparent way of attacking such problems that is more compatible
	with Java style and much better suited to larger problems.
<p>
	That is, Naomi provides the power of regular expressions without
	regular expressions.
<p>

	Although regular expressions offer a quick and terse way to perform
	simple matching and replacement operations, they are infuriatingly
	difficult to scale up to more complex problems.  Their terseness,
	arcane notation, reliance on the heavy use of escape characters, and
	lack of modularity and encapsulation make them difficult to write,
	read, understand, maintain, modify, reuse, and extend, for all but the
	simplest of problems.  Naomi's approach, though considerably less
	terse, offers far greater clarity, modularity, reusability, and
	extensibility.  Naomi is not intended as a replacement for regular
	expressions in simple cases but rather as an alternative for tackling
	more complex problems and for producing more transparent solutions.
<p>
	Even pattern matching problems that start out seeming to be
	simple have a way of turning out to be more complex than they first
	appeared--either because they evolve to cover additional cases or
	because we realize that our original solution was insufficiently
	general.  Naomi can therefore be a better approach even for simple
	problems, since they often turn into complex ones.
<p>

	Naomi provides two main classes, {@link org.naomi.regex.Pattern} and
	{@link org.naomi.regex.Matcher}, which are analogous to the
	corresponding classes in the

	<a
	href="http://docs.oracle.com/javase/7/docs/api/java/util/regex/package-summary.html">
	java.util.regex</a> package.

	These Naomi classes, along with the subclasses of its
{@link org.naomi.regex.Pattern}
	class and some other ancillary classes, provide a way to match
	patterns in strings without using regular expressions.

<p>
	Java programmers who are familiar with the use of regular expressions
	should have little trouble grasping the Naomi paradigm and reaping its
	advantages for complex pattern matching and replacement.  Those who
	have not been exposed to REs should find Naomi a kinder, gentler
	approach to dealing with textual pattern matching and replacement, in
	a way that scales well to large problems.

<h6>Bridges to and from regular expressions:</h6>

Naomi shields the user from having to deal with regular expressions. However,
for legacy applications, the class {@link org.naomi.regex.RegExpPattern}
provides a bridge from regular expressions, and the method {@link
org.naomi.regex.Pattern#getRegularExpression} provides a bridge to regular
expressions.

<h6>Chaining:</h6>

Almost all of the methods of Naomi that do not otherwise
return something useful return the object on which they were invoked.

@version 2015-03-09_17:55

@author Norman Shapiro
@author Jeff Rothenberg
*/

package org.naomi.regex;
