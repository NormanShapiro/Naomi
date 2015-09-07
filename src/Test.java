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
package Norm;
//@package org.naomi.regex;

import java.util.*;
import java.io.*;
import org.naomi.regex.*;

class Test
{

  enum compass {north,south};
  public Test(String[] args) throws IOException
  {
     String test=args.length>=2? args[1]: "";
     boolean no=false;

     if(true) try
     {
        Pattern pattern=new GlobPattern("p*");
        Enum<?> ans=Utilities.find
        (
           pattern,false,Action.last,Policy.class
        );
        A.p((" "+("ans") +" = " + (ans))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(true) try
     {
        Pattern pat=new GlobPattern("*vIs*").setCaseSensitive(false);
        BuiltInInterface ans=Utilities.find
        (
           pat,false,Action.last
        );
        A.p((" "+("ans") +" = " + (ans))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
        BuiltInInterface constant=
        Utilities.find
        (
           new GlobPattern("Vis*").setCaseSensitive(false),
          false,Action.exception
         );
        //@will set constant to  CoreBuiltIn.visbile.
        //@To create a CharClass:
        new BuiltInCharClass(constant);
     }
     catch(Exception ex) {ex.printStackTrace();}


     //@if(no) try
     //@{
        //@for(AnyBuiltIn builtIn: AnyBuiltIn.values())
        //@{
           //@Pattern pat=new BuiltInCharClass(builtIn);
           //@boolean yes=pat.matches("foo");
        //@}
     //@}
     //@catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
       String first=test.substring(0,1);
       BuiltInInterface builtIn=UniBlockBuiltIn.Hebrew;
       A.p((" "+("builtIn") +" = " + (builtIn))/*~*/+(" " + "Test.pj" + ":" + 84 + " "));
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/);
       Pattern complement=new ComplementCharClass(pattern);
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);
       boolean aye=pattern.matches(first);
       boolean nay=complement.matches(first);
       A.p((" "+("aye") +" = " + (aye))/*~*/+(" "+("nay") +" = " + (nay))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
       String first=test.substring(0,1);
       BuiltInInterface builtIn=UniBlockBuiltIn.Hebrew;
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/);
       Pattern complement=new ComplementCharClass(pattern);
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);
       boolean aye=pattern.matches(first);
       boolean nay=complement.matches(first);
       A.p((" "+("aye") +" = " + (aye))/*~*/+(" "+("nay") +" = " + (nay))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}

     //@if(no) try
     //@{
        //@AnyBuiltIn builtIn=AnyBuiltIn.percent;
        //@Pattern pat=new BuiltInCharClass(builtIn);
        //@A.p((" "+("pat.getRegularExpression()") +" = " + (pat.getRegularExpression()))/*~*/+(" " + "Test.pj" + ":" + 115 + " "));
        //@A.p((" "+("pat.matches(test)") +" = " + (pat.matches(test)))/*~*/);
     //@}
     //@catch(Exception ex) {ex.printStackTrace();}

     //@if(no) try
     //@{
        //@Enum<?> ans=Utilities.search
        //@(
           //@test,true,UniScriptBuiltIn.class,false,false
        //@);
        //@A.p((" "+("ans") +" = " + (ans))/*~*/);
        //@Pattern pat=new BuiltInCharClass((BuiltInInterface)ans);
        //@String x="x";
        //@A.p((" "+("pat.matches(x)") +" = " + (pat.matches(x)))/*~*/);


     //@}
     //@catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
       BuiltInInterface builtIn=UniBlockBuiltIn.Latin_1_supplement;
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/);
       Pattern complement=pattern.getComplement();
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);
       boolean aye=pattern.matches("x");
       boolean nay=complement.matches("x");
       A.p((" "+("aye") +" = " + (aye))/*~*/+(" "+("nay") +" = " + (nay))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
       BuiltInInterface builtIn=UniBlockBuiltIn.Latin_1_supplement;
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/);
       Pattern complement=pattern.getComplement();
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);
       boolean aye=pattern.matches("x");
       boolean nay=complement.matches("x");
       A.p((" "+("aye") +" = " + (aye))/*~*/+(" "+("nay") +" = " + (nay))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
       BuiltInInterface builtIn=UniBlockBuiltIn.Latin_1_supplement;
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/);
       Pattern complement=pattern.getComplement();
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);
       boolean aye=pattern.matches("x");
       boolean nay=complement.matches("x");
       A.p((" "+("aye") +" = " + (aye))/*~*/+(" "+("nay") +" = " + (nay))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
       CoreBuiltIn builtIn=CoreBuiltIn.valueOf(test);
       CharClass pattern=new  BuiltInCharClass(builtIn);
       A.p((" "+("pattern.getRegularExpression()") +" = " + (pattern.getRegularExpression()))/*~*/+(" "+("builtIn.name()") +" = " + (builtIn.name()))/*~*/);
       Pattern complement=pattern.getComplement();
       A.p((" "+("complement.getRegularExpression()") +" = " + (complement.getRegularExpression()))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}

    //tests GlobPattern
    if(no) try
     {
       Pattern pattern=new GlobPattern(test);
       Matcher matcher=pattern.matcher(args[2]);
       while(matcher.find())
           A.p((" "+("matcher.group()") +" = " + (matcher.group()))/*~*/);

     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        CharClass abc=new ExplicitCharClass("abc");
        CharClass cde=new ExplicitCharClass("cde");
        CharClass c=new IntersectCharClass(abc,cde);
        CharClass notC=c.getComplement().getComplement().getComplement();
        System.out.println("c= "+c.getRegularExpression());
        System.out.println("notC= "+notC.getRegularExpression());
     }
     catch(Exception ex) {ex.printStackTrace();}

    if(no) try
     {
        CharClass abc=new ExplicitCharClass("abc");
        CharClass cde=new ExplicitCharClass("cde");
        CharClass abcde=new UnionCharClass(abc,cde);
        CharClass not_abcde=abcde.getComplement().getComplement().getComplement();
        System.out.println("abcde= "+abcde.getRegularExpression());
        System.out.println("not_abcde=" +not_abcde.getRegularExpression());


     }
     catch(Exception ex) {ex.printStackTrace();}

    if(no) try
     {
        CoreBuiltIn lots=CoreBuiltIn.any;
        Pattern a1=new CharSequencePattern("a");
        Pattern any=new BuiltInCharClass(lots);
        //@Pattern any=new BuiltInCharClass(lots)
        any.setMinAndMaxCount(0,null);
        any.setPolicy(Policy.reluctant);
        Pattern a2=new CharSequencePattern("a");
        Pattern cat=new ConcatenatePattern(a1,any,a2);
        System.out.println("Pattern  ="+cat.getRegularExpression());
     }
     catch(Exception ex) {ex.printStackTrace();}

    if(no) try
     {
        CoreBuiltIn lots=CoreBuiltIn.any;
        Pattern a1=new CharSequencePattern("a");
        //@Pattern any=new BuiltInCharClass(lots);
        Pattern any=new BuiltInCharClass(lots);
        any.setMinAndMaxCount(0,null);
        any.setPolicy(Policy.reluctant);
        Pattern a2=new CharSequencePattern("a");
        Pattern cat=new ConcatenatePattern(a1,any,a2);
        System.out.println("CharClass="+cat.getRegularExpression());
     }
     catch(Exception ex) {ex.printStackTrace();}



     if(no) try
     {
        Pattern vowel=new ExplicitCharClass("aeiou");
        Pattern any=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
        //@Pattern any=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds)
        any.setMinAndMaxCount(0,null);
        any.setPolicy(Policy.reluctant);
        Pattern back=new BackReferencePattern(vowel);
        Pattern vowelToVowel=new ConcatenatePattern(vowel,any,back);
        System.out.println("Paattern   vowelToVowel="+vowelToVowel.getRegularExpression());

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
        Pattern vowel=new ExplicitCharClass("aeiou");
        //@Pattern any=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds)
        Pattern any=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
        any.setMinAndMaxCount(0,null);
        any.setPolicy(Policy.reluctant);
        Pattern back=new BackReferencePattern(vowel);
        Pattern vowelToVowel=new ConcatenatePattern(vowel,any,back);
        System.out.println("CharClass vowelToVowel="+vowelToVowel.getRegularExpression());

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
        //Select ASCII characters between 'A' and 'z' whose 2 least significant
        // bits are both 1
        Pattern bits=new FilterCharClass()
        {
           public char getFirst() {return 'A';}
           public boolean filter(char ch){return (ch&3) ==3;}
           public char getLast() {return 'z';}
           public FilterCharClass copy()
           {throw new UnsupportedOperationException();}
        };
        Norm.A.p((" "+("bits.getRegularExpression()") +" = " + (bits.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        //Select ISO Greek Extended Title Case Characters
        Pattern titleCase=new FilterCharClass()
        {
           public char getFirst() {return '\u1f00';}
           public boolean filter(char ch)
           {
              //@Norm.A.p((" "+("ch") +" = " + (ch))/*~*/+(" " + "Test.pj" + ":" + 308 + " "));
              return Character.isTitleCase(ch);
           }
           public char getLast() {return '\u1ffd';}

           public FilterCharClass copy()
           {throw new UnsupportedOperationException();}

        };
        Norm.A.p((" "+("titleCase.getRegularExpression()") +" = " + (titleCase.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
        Pattern word=new ConcatenatePattern
        (
           new BoundaryPattern(Side.left,Boundary.word)
           ,
           new BuiltInCharClass(CoreBuiltIn.any).
              setMaxCount(Pattern.UNBOUNDED).
              setPolicy(Policy.reluctant)
           ,
           new BoundaryPattern(Side.right,Boundary.word)
        );

        Norm.A.p((" "+("word.getRegularExpression()") +" = " + (word.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     //@if(no) try
     //@{
        //@CharClass upperCaseGreekCharacter=
        //@new IntersectCharClass
        //@(
           //@new BuiltInCharClass(UniBlockBuiltIn.Greek_And_Coptic)
           //@,
           //@new BuiltInCharClass(CoreBuiltIn.javaUpper)
        //@);
        //@Norm.A.p((" "+("upperCaseGreekCharacter.getRegularExpression()") +" = " + (upperCaseGreekCharacter.getRegularExpression()))/*~*/);
     //@}
     //@catch(Exception ex) {ex.printStackTrace();}

     if(no) try
     {
        Pattern a=new CharSequencePattern("");
        Pretty pretty=new Pretty();
        a.setPretty(pretty);
        pretty.indenter=" ";
        a.matcher("");
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        Pattern a=new CharSequencePattern("");
        Pattern b=new CharSequencePattern("");
        a.setMoniker("alpha");
        b.setMoniker("beta");
        Pattern cat=new ConcatenatePattern(b,a,a,b,a,a);
        cat.setMoniker("join");
        cat.matcher("");
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        Pattern a=new BuiltInCharClass(CoreBuiltIn.octal);
        Pattern b=new BackReferencePattern(a);
        Pattern cat=new ConcatenatePattern(b);
        Pretty pretty=new Pretty();
        cat.setPretty(pretty);
        System.err.println((" "+("cat.getRegularExpression()") +" = " + (cat.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        Pattern a=new CharSequencePattern("A");
        BackReferencePattern back=new BackReferencePattern(a);
        Pattern cat=new ConcatenatePattern(a,back);
        cat.setPretty(new Pretty());
        System.err.println((" "+("cat.getRegularExpression()") +" = " + (cat.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)
     try
     {
     Pattern vowel=new ExplicitCharClass("aeiou");
     Pattern any=new BuiltInCharClass
        (CoreBuiltIn.anyButLineEnds).setMinAndMaxCount(0,null);
     any.setPolicy(Policy.reluctant);
     Pattern back=new BackReferencePattern(vowel);
     Pattern vowelToVowel=new ConcatenatePattern(vowel,any,back);
     System.out.println("vowelToVowel="+vowelToVowel.getRegularExpression());
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)
     try
     {
        Pattern pat=new CharSequencePattern("hello");
        pat.setPolicy(Policy.reluctant);
        Matcher matcher=pat.matcher("");

     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)
     try
     {
        Pattern pat=new BuiltInCharClass(CoreBuiltIn.lineEnd);
        Pretty pretty=new Pretty();
        pretty.pretty=true;
        pat.setPretty(pretty);
        pat.setCaseSensitive(false);
        System.err.println((" "+("pat.getRegularExpression()") +" = " + (pat.getRegularExpression()))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no)try
     {
           String three="1 2";
           three=java.util.regex.Pattern.quote(three);
           System.err.println((" "+("three") +" = " + (three))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)
     try
     {
        Pattern cats=new CharSequencePattern("cats__");
        Pattern dogs=new CharSequencePattern("dogs");
        cats=new OrPattern(cats,"mice","cows","xxxxxxxxx");
        dogs=new OrPattern(dogs,"pigs","birds");
        Pattern either=
        new OrPattern
        (
           new CharSequencePattern("foobar"),
           new ConcatenatePattern(cats,""),
           "no sir",
           dogs
        );
        either=new OrPattern(either,"absolutely not");
        String string="It rained cats__ and dogs all night.";
        Matcher m=either.matcher(string);
        while(m.find())
        {
           int start=m.start(cats);
           int end=m.end(cats);
           System.err.println("cats " +(" "+("start") +" = " + (start))/*~*/+(" "+("end") +" = " + (end))/*~*/);

           start=m.start(dogs);
           end=m.end(dogs);
           System.err.println("dogs " +(" "+("start") +" = " + (start))/*~*/+(" "+("end") +" = " + (end))/*~*/);
        }
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)try
     {
        Pattern pat=new CharSequencePattern("x");
        pat.setPolicy(Policy.reluctant);
        pat.setCaseSensitive(true);
        System.err.println(pat.toString(Verbose.yes));
        System.err.println("-----------");
        System.err.println(pat.toString(Verbose.very));
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)try
     {
        Pattern a1=new CharSequencePattern("a");
        Pattern b1=new CharSequencePattern("b");
        Pattern ab2=new ConcatenatePattern(a1,b1);
        Pattern ab3=new ConcatenatePattern(ab2);
        ab3.setMoniker("My name is Herman Vreenegor");
        System.err.println((" "+("ab3") +" = " + (ab3))/*~*/+(" " + "Test.pj" + ":" + 490 + " "));

        Matcher matcher=ab3.matcher("ab");
        System.err.println((" "+("matcher.matches()") +" = " + (matcher.matches()))/*~*/+(" " + "Test.pj" + ":" + 493 + " "));
        System.err.println((" "+("matcher.group(a1)") +" = " + (matcher.group(a1)))/*~*/+(" " + "Test.pj" + ":" + 494 + " "));
        System.err.println((" "+("matcher.group(b1)") +" = " + (matcher.group(b1)))/*~*/+(" " + "Test.pj" + ":" + 495 + " "));
        System.err.println((" "+("matcher.group(ab2)") +" = " + (matcher.group(ab2)))/*~*/+(" " + "Test.pj" + ":" + 496 + " "));
        System.err.println((" "+("matcher.group(ab3)") +" = " + (matcher.group(ab3)))/*~*/+(" " + "Test.pj" + ":" + 497 + " "));
        System.err.println((" "+("matcher.start(ab3)") +" = " + (matcher.start(ab3)))/*~*/+(" " + "Test.pj" + ":" + 498 + " "));
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no)try
     {
        System.err.println("______________"+(" " + "Test.pj" + ":" + 505 + " "));
        Pattern meows=new CharSequencePattern("cats");
        Pattern barks=new CharSequencePattern("dogs");
        Pattern either=new OrPattern(meows,barks);
        System.err.println("______________"+(" " + "Test.pj" + ":" + 509 + " "));
        Matcher m=either.matcher("It rained cats and dogs all night.");
        System.err.println((" "+("either.getRegularExpression()") +" = " + (either.getRegularExpression()))/*~*/);
        System.err.println("______________"+(" " + "Test.pj" + ":" + 512 + " "));
        StringBuffer sb = new StringBuffer();
        while(m.find())
        {
           String replacement;
           if(m.group(meows) != null)
              replacement="felines";
           else
              replacement="canines";
           System.err.println("______________");
           m.appendReplacement(sb,replacement);
        }
        m.appendTail(sb);
        System.out.println(sb);
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no) try
     {
        Pattern a=new CharSequencePattern("a");
        Pattern aa=new ConcatenatePattern(a,a);
        Matcher matcher=aa.matcher("aa");
     }
     catch(Exception ex) {ex.printStackTrace();}


     if(no)try
     {
        Pattern a=new CharSequencePattern("a");
        Matcher m=a.matcher("aa");
        System.err.println((" "+("m.group(a)") +" = " + (m.group(a)))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}

     if(no)try
     {
        Pattern a=new CharSequencePattern("a");
        Pattern b=new CharSequencePattern("b");
        Matcher m=a.matcher("a");
        m.matches();
        System.err.println((" "+("m.group(b)") +" = " + (m.group(b)))/*~*/);
     }
     catch(Exception ex) {ex.printStackTrace();}


  }

}
