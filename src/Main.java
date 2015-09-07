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

import java.io.*;
import java.util.regex.*;

import org.naomi.regex.*;

public class Main
{
  private static void syntax(int exitStatus)
  {
     System.err.println("First argument must be 'examples', 'date' or 'test'");
     System.exit(exitStatus);
  }

  public static void main(String[] args)
  {
     try
     {
        if(args.length==0) syntax(0);
        String which=args[0];
        args[0]=null;
        if(which.equals("examples")) new SimpleExamples(args);
        else if (which.equals("date")) new DateTime(args);
        else if (which.equals("test")) new Test(args);
        else syntax(1);
     }
     catch(Throwable ex)
     {
        ex.printStackTrace();
        System.err.println(ex.getMessage());
        syntax(1);
     }
  }

  /* **********************************************************************
  public Main(String[] args) throws IOException
  {
	Pattern pattern1=new CharSequencePattern("abc");
	pattern1.setMinCount(0);
	pattern1.setMaxCount(1);//null means infinity here
	pattern1.setOptionValue(Option.insensitive,OptionValue.yes);
	pattern1.setName("pattern1");
	System.out.println("pattern1="+pattern1);

	Pattern pattern2=new ConcatenatePattern(pattern1,new CharSequencePattern("X"));
	System.out.println("pattern2="+pattern2);

if(true)
  return;
	Pattern greek1=new CharSequencePattern("alpha");
	Pattern greek2=new CharSequencePattern("beta");
	Pattern greek3=new CharSequencePattern("?");
	Pattern greek=new OrPattern(greek1,greek2,greek3);
	System.out.println("greek="+greek);

	CharClass notNorman=new CharSequenceCharClass(true,"Norman Shapiro");
	CharClass upper=new BuiltInCharClass(false,CoreBuiltIn.upper);
	CharClass upperButNotNorman_=new IntersectCharClass(false,notNorman,upper);
	Pattern upperButNotNorman=new CharClassPattern(upperButNotNorman_);

	Pattern greekPlus=new ConcatenatePattern("SirIssacNewton",greek,upperButNotNorman);
	System.out.println("greekPlus="+greekPlus);

	CharClass endClass=CharClassFactory.get("xyz");
	CharClass startClass=CharClassFactory.get('a','d');
	CharClass eitherClass=CharClassFactory.get(endClass,startClass);
	Pattern eitherPattern=PatternFactory.get(eitherClass);
	System.out.println("eitherPattern="+eitherPattern);
	eitherPattern.setOptionValue(Option.insensitive,OptionValue.yes);
	System.out.println("eitherPattern="+eitherPattern);
	Pattern patternA=new CharSequencePattern("a");
	patternA.setOptionValue(Option.insensitive,OptionValue.yes);
	System.out.println("patternA="+patternA);

	Pattern patternAny=PatternFactory.get(CoreBuiltIn.any);
	System.out.println("patternAny="+patternAny);
	patternAny.setMaxCount(null);//patternAny now matches one or more non line terminators
	System.out.println("patternAny="+patternAny);

         Pattern patternAll=PatternFactory.get(CoreBuiltIn.all);
	patternAll.setMaxCount(null);//patternAll now matches one or more characters
	System.out.println("patternAll="+patternAll);

	Pattern sign=new CharClassPattern("mantissaSign",new CharSequenceCharClass(false,"+-"));
	sign.setMinCount(0);
	Pattern digits=new CharClassPattern("mantissaDigits",new BuiltInCharClass(false,CoreBuiltIn.digit));
	digits.setMaxCount(null);
	Pattern point=new CharSequencePattern(".");
	Pattern fractionDigits=new CharClassPattern("fractionDigits",new BuiltInCharClass(false,CoreBuiltIn.digit));
	fractionDigits.setMaxCount(null);
	Pattern fraction=new ConcatenatePattern("fraction",point,fractionDigits);
	fraction.setMinCount(0);
	Pattern mantissa=new ConcatenatePattern("mantissa",sign,digits,fraction);

         Pattern e=new CharSequencePattern("e");
         e.setOptionValue(Option.insensitive,OptionValue.yes);
        	sign=new CharClassPattern("exponentSign",new CharSequenceCharClass(false,"+-"));
	sign.setMinCount(0);
	digits=new CharClassPattern("exponentDigits",new BuiltInCharClass(false,CoreBuiltIn.digit));
	digits.setMaxCount(null);
	Pattern exponent=new ConcatenatePattern("exponent",sign,digits);
	exponent.setMinCount(0);
	Pattern number=new ConcatenatePattern("number",mantissa,exponent);
	System.out.println("number="+number);

	Pattern vowel=new CharClassPattern(new CharSequenceCharClass(false,"aeiou"));
	System.out.println("vowel="+vowel);
	Pattern back=new BackReferencePattern(vowel);
	Pattern vowelVowel=new ConcatenatePattern(vowel,back);
	//@vowelVowel.getPattern();
	System.out.println("vowelVowel="+vowelVowel);

  }
************************************************** */


}

