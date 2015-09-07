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

import java.util.Collection;

/**

    An instance of CollectionCharClass is a {@link Pattern} which, on each
    repetition, matches any single character in the {@link Collection} of {@link
    Character}s used to instantiate the CollectionCharClass. Any duplicates in
    the {@link Collection} are ignored. If the collection contains more than a
    few thousand characters, performance may be adversely impacted. The internal
    "Not" flag in each instance can be set to make the class instead match all
    characters <i>except</i> those in the pre-defined set denoted by its
    instantiating Collection; this has the effect of matching the complement of
    the specified set of characters.

<p>
Example:
<pre>
  // Define a <a href="http://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html">java.util.HashMap</a> and add some characters to it
  //   (the keys of the map are the literal character values)


     Map&lt;Character,String&gt; char2Name = new HashMap&lt;&gt;();
     char2Name.put(' ' , "SPACE");
     char2Name.put('$' , "DOLLAR");
     char2Name.put('(', "LEFT");

  // Create a CharClass of the characters in the <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Set.html">java.util.Set</a> char2Name.keySet()

     CharClass namedChars = new CollectionCharClass(char2Name.keySet());
</pre>

*/

public class CollectionCharClass extends ComplementableCharClass
{
  private Collection<Character>  collection;

  public CollectionCharClass(Collection<Character> collection)
  {
     setCollection(collection);
  }

  CollectionCharClass fetchInnerString(StringBuilder ans)
  {
     StringBuilder sub=new StringBuilder();
     if(isNot()) sub.append("^");
     for(Character character:collection)
        sub.append(character);
     ans.append(quote(sub));
     return this;
  }

  public Collection<Character>  getCollection() {return collection;}

  public CollectionCharClass setCollection(Collection<Character> collection)
  {
     this.collection=collection;
     altered();
     return this;
  }

  public  CollectionCharClass copy()
  {
     CollectionCharClass ans=new CollectionCharClass(getCollection());
     copyTo(ans);
     return ans;
  }
}
