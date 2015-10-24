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

/** 
     An instance of BoundaryPattern matches, on each repetition, the left or
     right boundary (as specified) of some string, e.g., the start or end of a
     line, word or non-word.  The type of boundary to be matched (word, line,
     etc.) is specified by the {@link Boundary} used to instantiate the
     BoundaryPattern.
*/

public class BoundaryPattern extends Pattern
{
  private Boundary boundary;
  private Side side;

  public BoundaryPattern(Side side,Boundary boundary)
  {
     setBoundary(side,boundary);
  }

  /** @return this BoundaryPattern */
  public BoundaryPattern setBoundary(Side side,Boundary boundary)
  {
     this.side=side;
     this.boundary=boundary;
     altered();
     return this;
  }

  public Side getSide() {return side;}
  public Boundary  getBoundary() {return boundary;}

  Rope getInnerRope()
  {
     switch(side)
     {
        default:    throw new Bug();
        case left:  return new CharSequenceRope(boundary.getLeft());
        case right: return new CharSequenceRope(boundary.getRight());
     }
  }

  public BoundaryPattern copy()
  {
    BoundaryPattern ans=new BoundaryPattern(getSide(),getBoundary());
    copyTo(ans);
    return ans;
  }
}
