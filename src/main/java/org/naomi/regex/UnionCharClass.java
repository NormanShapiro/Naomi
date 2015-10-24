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

     An instance of UnionCharClass is a {@link Pattern} which, on each repetition,
     matches any single character that is in any of the {@link CharClass}es in its
     instantiating {@link Collection} or list of {@link CharClass}es.

*/

public class UnionCharClass extends CharClass
{
  private Collection<CharClass> collection;

  public UnionCharClass(Collection<CharClass> collection)
  {
     setCollection(collection);
  }

  public UnionCharClass(CharClass ... charClass)
  {
     setCollection(charClass);
  }

  public UnionCharClass setCollection(Collection<CharClass> collection)
  {
     this.collection=collection;
     altered();
     return this;
  }

  public UnionCharClass setCollection(CharClass ... charClass)
  {
     setCollection(Arrays.asList(charClass));
     return this;
  }

  public  Collection<CharClass> getCollection() {return collection;}

  UnionCharClass fetchInnerString(StringBuilder ans)
  {
     join(null,ans,collection,false);
     return this;
  }

  public UnionCharClass copy()
  {
     UnionCharClass ans =new UnionCharClass(getCollection());
     copyTo(ans);
     return ans;
  }

  // De Morgan's laws http://en.wikipedia.org/wiki/De_Morgan%27s_laws
  public  IntersectCharClass getComplement()
  {return new IntersectCharClass(complementCollection(collection));}


}

