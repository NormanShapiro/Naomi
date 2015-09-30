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

abstract class Patterns extends Pattern
{
  private List<Pattern> patterns=new ArrayList<Pattern>();
  abstract String getDelimiter();

  Patterns(Object... obs) {add(obs);}

  /** For each object, if object is a pattern add it, otherwise
 add a new CharSequencePattern(object.toString())*/
  public Patterns add(Collection<?> objects)
  {
     for(Object ob:objects)
        addOne(ob);
      return this;
  }

  /** For each object, if object is a pattern add it, Otherwise
 add a new CharSequencePattern(object.toString())*/
  public Patterns add(Object ... object)
  {
     for(Object ob: object)
        addOne(ob);
     return this;
  }

  private Patterns addOne(Object ob)
  {
     Pattern pattern;
     if(ob instanceof Pattern)
        pattern=(Pattern)ob;
     else
        pattern=new CharSequencePattern(ob.toString());
     patterns.add(pattern);
     altered();
     return this;
  }

  public Patterns clear()
  {
     for(Pattern pattern:patterns)
        pattern.removeAlterationListener(this);
      patterns.clear();
     altered();
     return this;
   }

  Rope getInnerRope()
  {
    List<RopeClient> list=new ArrayList<RopeClient>();
    for(Pattern pattern:patterns)
     list.add(pattern);
    return new Ropes(list,getDelimiter());
  }

  List<Pattern> computeKids()
  {
     List<Pattern> ans=new ArrayList<Pattern>();
     ans.add(this);
     for(Pattern pattern:patterns)
        ans.addAll(pattern.getKids());
     return ans;
  }

  List<Pattern> getPatterns() {return patterns;}

  public Patterns copyTo(Pattern other)
  {
     ((Patterns)other).patterns=new ArrayList<Pattern>(patterns);
     super.copyTo(other);
     return this;
  }

}
