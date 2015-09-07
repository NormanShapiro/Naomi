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

class NestedRope //does not extend Rope
{
  // order is outer to inner
  final private List<Rope> ropes;
  final private boolean outerFirst;

  NestedRope(boolean outerFirst,List<Rope> ropes)
  {
     this.ropes=ropes;
     this.outerFirst=outerFirst;
  }

  NestedRope(boolean outerFirst,Rope ... rope)
  {
     this(outerFirst,Arrays.asList(rope));
  }

  Rope getRope()
  {
     if(ropes.size() ==0 ) throw new Bug();
     Rope ans;
     if(outerFirst)
        ans=ropes.get(0);
     else
        ans=ropes.get(ropes.size()-1);
     if(ropes.size() >= 2)
     {
        List<Rope> subList;
        if(outerFirst)
           subList=ropes.subList(1,ropes.size());
        else
           subList=ropes.subList(0,ropes.size()-1);
        NestedRope nested=new NestedRope(outerFirst,subList);
        if(ans.kids != null)
           throw new Bug();
        ans.kids= Collections.singletonList(nested.getRope());
     }
     return ans;
  }
}














