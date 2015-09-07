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
package Jeff;

/***********************************************************************
9/23/2014

	Template for using Norm Shapiro's Java RE replacement
		"Naomi" (initially "Pattern")

	jgrPattern defines a complete pattern that matches (all of)
	    each target string (e.g., input file content). jgrPattern
	    is composed of named subpatterns (Java Matcher "groups")
	    that are later used to construct the replacement string,
	    with some groups presumably being replaced by other
	    content.

	jgrMatch sets itself to (adds to itself) the jgrPattern, and
		invokes a Java Matcher on it, with the target string
		being passed as the argument.

***********************************************************************/

import org.naomi.regex.*;
//@import java.util.regex.Matcher;
import java.util.*;
import java.io.*;

  /*****************************************************************
    Note: in order to do a search-and-replace within a file,
	must (in general) match the entire contents of the file
  ******************************************************************/

class jgrMatch extends ConcatenatePattern
 {

  private static void   p(String str) { System.out.print(str); }
  private static void pln(String str) { System.out.println(str); }

  jgrMatch(String target)	// Constructor
    {
     Pattern any1 = new BuiltInCharClass( CoreBuiltIn.any);
     any1.setMaxCount(UNBOUNDED);

     Pattern any2 = new BuiltInCharClass( CoreBuiltIn.any);
     any2.setMaxCountToUnbounded();

     Pattern meat = new CharSequencePattern( "xxxx");

     Pattern jgrPattern = new ConcatenatePattern(any1,meat,any2);

     add(jgrPattern);

     Matcher matcher = this.matcher(target);

     pln("RE:\n" + this.getRegularExpression() );

     if(matcher.matches())
	{
	  pln("\n jgrMatch: success! \n");

	  pln("Result after replacement:\n");

	  p(  matcher.group(any1)
		+ "ZQZ"
		+ matcher.group(any2)
	   );
	}
     else pln("\n jgrMatch: failed \n");
    }


  private static String readFile( String file ) throws IOException
   {
    BufferedReader reader = new BufferedReader( new FileReader (file));
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    while( ( line = reader.readLine() ) != null ) {
	stringBuilder.append( line );
	stringBuilder.append( ls );
    }

    return stringBuilder.toString();
   }

  public static void main(String[] args)
  {
     try
     {
	String buffer = readFile("testFile");
	pln("Buffer:\n|" + buffer + "|");

	new jgrMatch(buffer);
     }
     catch(Exception ex)
     {
        System.err.println(ex.getMessage());
        System.exit(1);
     }
  }

 }




