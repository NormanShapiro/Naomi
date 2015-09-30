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

     An instance of EnumPattern matches, on each repetition, any of the
     strings corresponding to the constants of the {@link Enum} used to
     instantiate the EnumPattern.
<p>

     Note that this is quite different from {@link BuiltInCharClass}, which
     uses the constants defined by BuiltIn Enums as the names of sets of
     characters.  In contrast, an EnumPattern matches its instantiating Enum's
     constant values themselves, as string literals.

<p>
Example:

<pre>
    enum compass {north,east,south,west};
    EnumPattern compassPattern =
	new EnumPattern(compass.class); // Note: need ".class" here
</pre>

matches the strings "north", "east", "south", or "west".

*/
public class EnumPattern extends OrPattern
{
  private Class<? extends Enum<?>> enumeration;

  public EnumPattern(Class<? extends Enum<?>> enumeration)
  {
     setEnum(enumeration);
  }

  public Class<? extends Enum<?>> getEnum() {return enumeration;}

  public EnumPattern setEnum( Class<? extends Enum<?>> enumeration)
  {
     this.enumeration=enumeration;
     clear();
     Enum<?>[] constants=enumeration.getEnumConstants();
     if(constants==null)
        throw new Bug();
     add((Object[])constants);
     altered();
     return this;
  }

/**

    Returns the first constant of the Enum whose toString()constant of the Enum
    is case independently equal to string, or null, if none.

  @throws ReuseException 

*/

  public Enum<?> getValue(String string,boolean caseIndependent)
  {
     Enum<?>[] constants=enumeration.getEnumConstants();
     for(Enum<?> constant: constants)
     {
        if(caseIndependent)
        {
           if(constant.toString().equalsIgnoreCase(string))
              return constant;
        }
        else
        {
           if(constant.toString().equals(string))
              return constant;
        }
      }
     return null;
  }

/**

  Returns the first constant of the Enum whose toString() is matched
  by pattern or null, if none.

  @throws ReuseException 

*/
  public Enum<?> getValue(Pattern pattern)
  {
     for(Enum<?> constant: enumeration.getEnumConstants())
        if(pattern.matches(constant.toString()))
           return constant;
     return null;
  }

/**
   Returns the first constant of the Enum whose toString() is case
   independently equal to matcher.group(this)) or null, if none.

  @throws ReuseException 
*/

  public Enum<?> getValue(Matcher matcher,boolean caseIndependent)
  {
     String string=matcher.group(this);
     if(string==null || string.isEmpty())
        return null;
      else
        return getValue(string,caseIndependent);
  }

/**

  Returns the zero based ordinal of the first constant of the Enum whose
  toString() constant of the Enum is case independently equal to
  matcher.group(this) or null, if none.

*/

  public Integer getOrdinal(Matcher matcher,boolean caseIndependent)
  {
     Enum<?> value= getValue(matcher,caseIndependent);
     if(value==null)
        return null;
     else
         return value.ordinal();
   }

  /** return a list of all constants in which string is initial. Returned list is
  empty if no matches*/
  public List<Enum<?>>
     getInitialMatches(String string,boolean insensitive)
  {
    if(insensitive)
     string=string.toLowerCase();
    List<Enum <?>> ans=new ArrayList<>();
    for(Enum<?> value:enumeration.getEnumConstants())
    {
        if(insensitive)
        {
           if(value.toString().toLowerCase().indexOf(string)>=0)
              ans.add(value);
        }
        else
        {
           if(value.toString().indexOf(string)>=0)
              ans.add(value);
        }
     }
     return ans;
  }

  /** Should not be called by programmer; public only because java requires it.*/
  public Patterns clear() {return super.clear();}

  /** Should not be called by programmer; public only because java requires it.*/
  public Patterns add(Collection<?> objects) {return super.add(objects);}

  /** Should not be called by programmer; public only because java requires it.*/
  public Patterns add(Object ... object) {return super.add(object);}
}
