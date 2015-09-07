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

import org.naomi.regex.*;
import java.util.*;


public class SimpleExamples
{
  SimpleExamples(String[] ignored)
  {

     //Matches the string,".*" No need to escape the '.' or the "*"
     Pattern star=new CharSequencePattern(".*");
     // Matches   "p","q","r","s" or "t"
     Pattern letter=new IntervalCharClass('p','t');
     // Matches 0 or more instances of    "p","q","r","s" or "t"
     letter.setMinCount(0); letter.setMaxCount(null); //null means infinity
     Pattern sharps=new CharSequencePattern("###");
     Pattern equal=new CharSequencePattern("=");
     // Mathes either "###" or "="
     Pattern or=new OrPattern(sharps,equal);
     // Matches ".*" followed by zero or more instances of
     // "p","q","r","s" or "t" followed by either "###" or "="
     Pattern cat=new ConcatenatePattern(star,letter,or);


     Pattern pattern1=new CharSequencePattern("abc");
     pattern1.setMinCount(0);
     pattern1.setMaxCount(1);//null means infinity here
     pattern1.setCaseSensitive(false);
     System.out.println("pattern1="+pattern1.getRegularExpression());

     Pattern pattern2=new ConcatenatePattern(pattern1,new CharSequencePattern("X"));

     Pattern greek1=new CharSequencePattern("alpha");
     Pattern greek2=new CharSequencePattern("beta");
     Pattern greek3=new CharSequencePattern("?");
     Pattern greek=new OrPattern(greek1,greek2,greek3);

     CharClass endClass=new ExplicitCharClass("xyz");
     CharClass startClass=new IntervalCharClass('a','d');
     Pattern eitherPattern=new UnionCharClass(endClass,startClass);
     //@Pattern eitherPattern=new CharClassPattern(eitherClass);
     eitherPattern.setCaseSensitive(false);
     Pattern patternA=new CharSequencePattern("a");
     patternA.setCaseSensitive(false);

     Pattern patternAny=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
     patternAny.setMaxCount(null);//patternAny now matches one or more non line terminators

     Pattern patternAll=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
     patternAll.setMaxCount(null);//patternAll now matches one or more characters

     Pattern sign=new ExplicitCharClass("+-");
     sign.setMinCount(0);
     Pattern digits=new BuiltInCharClass(CoreBuiltIn.digit);
     digits.setMaxCount(null);
     Pattern point=new CharSequencePattern(".");
     Pattern fractionDigits=new BuiltInCharClass(CoreBuiltIn.digit);
     fractionDigits.setMaxCount(null);
     Pattern fraction=new ConcatenatePattern(point,fractionDigits);
     fraction.setMinCount(0);
     Pattern mantissa=new ConcatenatePattern(sign,digits,fraction);

     Pattern e=new CharSequencePattern("e");
     e.setCaseSensitive(false);
     sign=new ExplicitCharClass("+-");
     sign.setMinCount(0);
     digits=new BuiltInCharClass(CoreBuiltIn.digit);
     digits.setMaxCount(null);
     Pattern exponent=new ConcatenatePattern(sign,digits);
     exponent.setMinCount(0);
     Pattern number=new ConcatenatePattern(mantissa,exponent);

     EnumPattern compassPattern=new EnumPattern(compass.class);

     Pattern uppers=new BuiltInCharClass(CoreBuiltIn.upper);
     uppers.setMaxCount(null);//infinity
     Pattern middle=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds);
     middle.setMaxCount(null);//infinity;
     BackReferencePattern end=new BackReferencePattern(uppers);
     ConcatenatePattern all=new ConcatenatePattern(uppers,middle,end);

     Pattern vowel=new ExplicitCharClass("aeiou");
     Pattern any=new BuiltInCharClass(CoreBuiltIn.anyButLineEnds).setMinAndMaxCount(0,null);
     any.setPolicy(Policy.reluctant);
     Pattern back=new BackReferencePattern(vowel);
     Pattern vowelToVowel=new ConcatenatePattern(vowel,any,back);
     System.out.println("vowelToVowel="+vowelToVowel.getRegularExpression());

     Pattern numeral=new BuiltInCharClass(CoreBuiltIn.digit);
     numeral.setMaxCount(Pattern.UNBOUNDED);
     Pattern dollars=new CharSequencePattern(" dollars");
     Pattern amount=new ConcatenatePattern(numeral,dollars);
     Matcher matcher=amount.matcher
        ("I say that 17 dollars + 9 dollars = 26 dollars everyday.");
     StringBuffer buffer=new StringBuffer();
     while(matcher.find())
        matcher.appendReplacement(buffer,"$"+matcher.group(numeral));
     matcher.appendTail(buffer);
     System.out.println(buffer);

     Pattern meows=new CharSequencePattern("cats");
     Pattern barks=new CharSequencePattern("dogs");
     Pattern either=new OrPattern(meows,barks);
     Matcher m=either.matcher("It rained cats and dogs all night.");
     StringBuffer sb = new StringBuffer();
     while(m.find())
     {
        String replacement;
        if(m.group(meows) != null)
           replacement="felines";
        else
           replacement="canines";
        m.appendReplacement(sb,replacement);
     }
     m.appendTail(sb);
     System.out.println(sb);

     Pattern maybeReadable=new CharSequencePattern("hello");
     maybeReadable.setPretty(new Pretty());
     //@maybeReadable.setExtraComment("one\ntwo\nthree");
     System.out.println("maybeReadable="+maybeReadable.getRegularExpression());

     CharClass upperCaseGreekCharacter=
     new IntersectCharClass
     (
        new BuiltInCharClass(UniBlockBuiltIn.Greek_extended),
        new BuiltInCharClass(CoreBuiltIn.javaUpper)
     );

     // illustrates chaining and BoundaryPattern
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

     //Select ASCII characters between 'A' and 'z' whose two least significant
     // bits are both 1
     FilterCharClass bits = new FilterCharClass()
     {
        public char getFirst() {return 'A';}
        public boolean filter(char ch){return (ch&3) ==3;}
        public char getLast() {return 'z';}

        public FilterCharClass copy()
        {return this;}
     };

     CharClass notBits=bits.getComplement();
     Pattern  atLeastOneNonZero=new IntersectCharClass
     (
        new BuiltInCharClass(CoreBuiltIn.ascii),
        notBits
     );

     Map<Character,String> char2Name=new HashMap<Character,String>();
     //... define other chars
     char2Name.put(' ' , "SPACE");
     //...define other chars
     char2Name.put('$' , "DOLLAR");
     //...define other chars
     char2Name.put('(', "LEFT");
     //...define other chars
     //... Code that uses char2Name
     CharClass namedChars=new CollectionCharClass(char2Name.keySet());


     //@class EvenIterator implements Iterator<Character>
     //@{
        //@Character last=null;

        //@public void remove() {throw new UnsupportedOperationException();}

        //@public Character next()
        //@{
           //@if(last==null)
              //@return last='B';
          //@else
          //@{
            //@char lst=(char)(last.charValue()+2);
            //@if(lst  >= '\177')
              //@throw new NoSuchElementException();
             //@return last = new Character(lst);
          //@}
       //@}

     //@public boolean hasNext()
     //@{
        //@if(last==null) return true;
        //@return last.charValue()<'\177'-2;
     //@}
   //@}

   //@Pattern even=new IteratorCharClass(new EvenIterator());
 }

  enum compass {north,east,south,west};


}
