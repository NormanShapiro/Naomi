The power of regular expressions without regular expressions.

Naomi is an alternative to regular expressions that uses modern object-oriented
constructs to define modular, parameterized patterns and subpatterns. This makes
complex pattern matching programs easier to write, read, understand, verify,
modify, maintain, extend, and reuse. Naomi can be thought of as an API for
generating and using regular expressions: it can do everything they can do.
However, it can also be used without any knowledge of regular expressions; in
this way it can be thought of as an API to the pattern matching capabilities that
underlie regular expressions, making it unnecessary for programmers to learn or
deal with regular expressions themselves.

Although skilled programmers can write regular expressions that solve a wide
range of problems, regular expressions quickly become "write only" for all but
the simplest tasks. That is, once they have aged for a while, no one other than
their authors (and often not even they) can understand them well enough to
verify, modify, debug, or maintain them without considerable effort. Analogous
low-level programming formalisms, such as machine code and assembly language,
have been replaced by higher-level, more readable and modular languages to
produce programs that have proven easier and more cost-effective to debug,
verify, maintain, reuse, and extend. In a similar fashion, Naomi is a means of
"taming" complex regular expressions, as well as offering an easier alternative
for those who are unfamiliar with them.

Naomi allows the user to create object-oriented patterns that consist of
nameable, modular, recursively composable subpatterns, each of which has
attributes that specify its cardinality, case-sensitivity, anchoring, and pattern
matching behavior, including backtracking and greediness. Naomi provides
constructs for creating user-defined character classes and for combining these
with pre-defined named character classes and other subpatterns into higher-level
patterns, while allowing backreferencing, alternation, etc. Because patterns in
Naomi are objects, it is easy to create class/subclass hierarchies of
user-defined types of patterns for specific applications or purposes.

Although regular expressions are highly compact and terse, this virtue becomes a
vice for complex patterns. Coupled with the extensive use of metacharacters and
escape sequences, this makes even moderately complex regular expressions
effectively unreadable for all but the most experienced and practiced regular
expression programmers. Newer features that go beyond the original regular
expression formalism--such as namable groups, built-in names for common character
classes, comments, and free white space--make regular expressions less terse. But
their use is not enough to render complex regular expressions easily readable.
These extensions are analogous to replacing binary machine language by assembly
language coding.

Naomi pattern specifications are generally far more verbose than regular
expressions, but in return they are far easier to read and understand (and
therefore to modify and maintain), since they provide structure and modularity.
As an additional benefit, they also eliminate the clutter of metacharacters and
escape sequences that obfuscate regular expressions.

Naomi's advantages over bare regular expressions become apparent only for larger
scale pattern matching tasks, such as parsing the e-mail date-time specification
of RFC 2822, as shown in src/DateTime.java. That example illustrates the
obscurity of regular expressions and Naomi's strengths.

To learn more about Naomi, start by reading the documentation at
http://normanshapiro.github.io/Naomi/ for Pattern and its subclases and for
Matcher. For some tutorial examples see src/SimpleExamples.java. For an
illustration of Naomi's power see src/DateTime.java.




