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
package  org.naomi.regex;

import java.util.Collections;

class CountAndPolicyRope extends Rope
{
  final private Count count;
  final private Policy policy;

  CountAndPolicyRope(Count count,Policy policy)
  {
     this.count=count;
     this.policy=policy;
     kids=null;
     if(isTrivial())
     {
        startEncloser=null;
        endEncloser=null;
     }
     else
     {
        startEncloser="(?:";
        endEncloser=")"+count.toString()+policy.getIntroduction();
        endComment=getEndComment();
     }
  }

  private boolean isTrivial() {return count.isDefault();}

  CharSequence getKidIndentIncrement(Rope kid, Pretty pretty)
  {
     if(isTrivial())
        return "";
     else
        return pretty.indenter;
  }

  CharSequence getEndComment()
  {
     CharSequence countComment=count.toString(null,Verbose.yes);
     CharSequence policyComment=policy.toString(null,Verbose.yes);
     if(policyComment.length() == 0)
        return countComment;
     else if(countComment.length() == 0)
        return policyComment;
     else
        return countComment+" "+policyComment;
  }


}
