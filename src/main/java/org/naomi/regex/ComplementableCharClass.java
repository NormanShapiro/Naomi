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

abstract class ComplementableCharClass extends CharClass
{
  private boolean not=false;

  /** Determines whether the sense of membership will be reversed.
  Defaults to false.*/
  public ComplementableCharClass setNot(boolean not)
  {
     if(this.not==not) return this;
     this.not=not;
     altered();
     return this;
  }

  /** See {@link #setNot} */
  public boolean isNot() {return not;}


  /** See {@link #setNot} */
  public ComplementableCharClass reverseNot() {setNot(!isNot());return this;}

  public ComplementableCharClass getComplement()
  {
     ComplementableCharClass copy=(ComplementableCharClass)copy();
     copy.reverseNot();
     return copy;
  }

  public ComplementableCharClass copyTo(Pattern other)
  {
     ((ComplementableCharClass)other).setNot(isNot());
     return (ComplementableCharClass)super.copyTo(other);
  }

}
