The power of regular expressions without regular expressions.

Naomi is an alternative to regular expressions that uses modern object-oriented
programming techniques to express patterns in a way that is far more modular and
easier to write, read, understand, modify, maintain, and reuse. To do this, Naomi
allows the user to create object-oriented patterns that consist of nameable,
modular, recursively composable subpatterns, each of which has attributes that
specify its cardinality, case-sensitivity, anchoring, and pattern matching
behavior, including backtracking and greediness. Naomi provides constructs for
creating user-defined character classes and for combining these with pre-defined
named character classes and other subpatterns into higher-level patterns, while
allowing backreferencing, alternation, etc. Because patterns in Naomi are
objects, it is easy to create class/subclass hierarchies of user-defined types of
patterns for specific applications or purposes.

Naomi pattern specifications are generally far more verbose than regular
expressions, but in return they are far easier to read and understand (and
therefore to modify and maintain).

To learn more about Naomi, start by reading, at
http://normanshapiro.github.io/Naomi/, the documentation for Pattern and its
subclases and for Matcher. See also src/SimpleExamples.java (for an introduction
to Naomi which does not show its power) and src/DateTime.java (to see the power
of Naomi).

