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

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;
import java.nio.file.*;

/** This class governs the display of regular expressions
(see {@link Pattern#getRegularExpression getRegularExpression} and
{@link Pattern#setPretty setPretty}), to generate expressions which are more
human readable&mdash;or, more precisely, less unreadable.
*/
public class Pretty
{
  private static Pattern whitePattern;

  /** generates a new Pretty with
  {@link #pretty} == true,
   {@link #indenter} == 6 spaces
   and {@link #comments} == true.
  */
  public Pretty()
  {
     pretty=true;
     indenter =Utilities.ditto(6," ",null);
     comments=true;
  }

 /** generates a new Pretty with
  {@link #pretty} == prettyArg,
   {@link #indenter} == 6 spaces
   and {@link #comments} == true.
  */
  public Pretty(boolean prettyArg)
  {
     this();
     pretty=prettyArg;
  }

  public Pretty(Pretty other)
  {
     other.pretty=pretty;
     other.indenter=indenter;
     other.comments=comments;
  }

  /** When true, {@link Pattern#getRegularExpression getRegularExpression}
  attempts to generate a more human readable expression; when false, all
  other fields are ignored and
  {@link Pattern#getRegularExpression getRegularExpression} generates
  a dense expression. The default is false.
  */
  public boolean pretty;

  /** The indentation per level. The default is 6 spaces.*/
  public CharSequence indenter;

  /** When true, terse comments are generated; when false no comments are
  generated. The default is true.
  */
  public boolean comments;

  private static Pattern getWhitePattern()
  {
     if(whitePattern==null)
        whitePattern=new BuiltInCharClass(CoreBuiltIn.white).setMinAndMaxCount(0,null);
      return whitePattern;
 }

  Pretty check()
  {
     if(!pretty) return this;
     if(!getWhitePattern().matches(indenter))
        throw new BadPrettyException
           (" indenter=='"+indenter+"' is not all white");
     return this;
   }

  /** Thrown when an instance of Pretty has a bad field*/
  public static class BadPrettyException extends RuntimeException
  {
     BadPrettyException(String msg)
     {
        super("Bad Pretty: "+ msg);
     }
  }
}
