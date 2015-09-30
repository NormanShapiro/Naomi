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

/** Instances of Rope are not safe for use by multiple threads.
/**
@serial exclude
*/

abstract class Rope
{
  String startEncloser;
  String endEncloser;
  List<Rope> kids;
  boolean coreEreKids;
  CharSequence startComment="";
  CharSequence endComment="";

  Rope
  (String startEncloser,String endEncloser,List<Rope> kids,boolean coreEreKids)
  {
     this.startEncloser=startEncloser;
     this.endEncloser=endEncloser;
     this.kids=kids;
     this.coreEreKids=coreEreKids;
  }

  Rope() {}


  StringBuilder toString(StringBuilder ans,Pretty pretty,String indent)
  {
     if(ans==null)
        ans=new StringBuilder();
     start(ans,pretty,indent);
     if(coreEreKids)
     {
        core(ans,pretty,indent);
        doKids(ans,pretty,indent);
     }
     else
     {
        doKids(ans,pretty,indent);
        core(ans,pretty,indent);
     }
     end(ans,pretty,indent);
     return ans;
  }


  private void start(StringBuilder ans,Pretty pretty,String indent)
  {
     if(startEncloser==null)
        return;
     if(!pretty.pretty)
     {
        ans.append(startEncloser);
     }
     else
     {
        String comment;
        if(pretty.comments && startComment.length()>0)
           comment=" #"+startComment;
         else
           comment="";
        ans.append(indent + startEncloser+comment);
        Utilities.assureEndsWithNewLine(ans);
     }
   }

  void doKids(StringBuilder ans,Pretty pretty,String indent)
  {
     if(kids==null)
        return;
     for(Rope kid:kids)
     {
        if(!pretty.pretty)
        {
           kid.toString(ans,pretty,"");
        }
        else
        {
           kid.toString(ans,pretty,indent+getKidIndentIncrement(kid,pretty));
           Utilities.assureEndsWithNewLine(ans);
        }
     }
  }

  private void end(StringBuilder ans,Pretty pretty,String indent)
  {
     if(endEncloser==null)
        return;
     if(!pretty.pretty)
        ans.append(endEncloser);
     else
     {
        Utilities.assureEndsWithNewLine(ans);
        ans.append(indent+endEncloser);
        if(pretty.comments && endComment.length() >0)
           ans.append("#"+endComment);

        Utilities.assureEndsWithNewLine(ans);
      }
   }

  Rope core(StringBuilder ans,Pretty pretty,String indent){return this;}

  CharSequence  getKidIndentIncrement(Rope kid, Pretty pretty)
  {return pretty.indenter;}
}
