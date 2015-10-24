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

public class DelegateCharClass extends CharClass implements AlterationListener
{
  private CharClass delegate;

  public DelegateCharClass()
  {
  }

  public DelegateCharClass(CharClass delegate)
  {
     setDelegate(delegate);
  }

  public DelegateCharClass setDelegate(CharClass delegate)
  {
     if(this.delegate==delegate)
        return this;
     if(delegate != null)
        delegate.removeAlterationListener(this);
     this.delegate=delegate;
     delegate.addAlterationListener(this);
     altered();
     return this;
  }

  //@Rope getInnerRope() {return delegate.getInnerRope();}

  public CharClass copy()
  {
     CharClass ans=new DelegateCharClass((CharClass)delegate.copy());
     copyTo(ans);
     return ans;
  }

  public List<Pattern> computeKids()
  {
    List<Pattern> ans=new ArrayList<Pattern>();
    for(Pattern kid:delegate.getKids())
    {
        if(kid==delegate)
           ans.add(this);
        else
           ans.add(kid);
     }
     return ans;
  }

  public CharClass getComplement()
  {
     return new DelegateCharClass(delegate.getComplement());
  }

  CharClass fetchInnerString(StringBuilder stringBuilder)
  {
    delegate.fetchInnerString(stringBuilder);
    return this;
  }

}
