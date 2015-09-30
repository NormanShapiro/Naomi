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
/** This class is package private. It is used by the setMinCount() and
setMacCount() methods of Pattern*/

package org.naomi.regex;

class Count
{
  int min;
  int max; //Integer.MAX_VALUE menas no max

  Count() {this(1,1);}
  Count(int min,Integer max) {setMin(min);setMax(max);}

  public String toString()
  {
     if(min==1 && max==1) return "";
     if(min==0 && max==1) return "?";
     if(min==0 && max==Integer.MAX_VALUE) return "*";
     if(min==1 && max==Integer.MAX_VALUE) return "+";
     if(min==max) return "{"+min+"}";
     if(max==Integer.MAX_VALUE) return "{"+min+",}";
     return "{"+min+","+max+"}";
  }

  public boolean isDefault() {return min==1 && max==1;}
  public int getMin() {return min;}
  public Integer getMax() {return max==Integer.MAX_VALUE? null:max;}
  public Count setMin(int min) {this.min=min; return this;}

  public Count setMax(Integer max)
  {
     this.max=max==null || max<=0 ? Integer.MAX_VALUE:max;
     return this;
  }

  StringBuilder toString(StringBuilder sb,Verbose verbose)
  {
     if(sb==null) sb=new StringBuilder();
     if(verbose==Verbose.no) return sb;
     if(min != 1 || verbose==Verbose.very)
        sb.append("min_count=" +min);
     if(max != 1 ||  verbose==Verbose.very)
     {
        String string;
        if(max==Integer.MAX_VALUE) string="Unbounded";
        else string=Integer.toString(max);
        if(sb.length() > 0)
           sb.append(" ");
        sb.append("max_count="+string);
     }
     return sb;
  }
}
