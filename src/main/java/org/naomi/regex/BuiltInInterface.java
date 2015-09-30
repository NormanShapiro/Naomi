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
     This Interface applies to all of the BuiltIn Enums listed below, each of
     which defines a set of constant values that specify a different
     pre-defined set of characters to be matched.  Any of these BuiltIn Enum
     constants can be used to instantiate a {@link BuiltInCharClass}
     that matches any of the characters in the corresponding pre-defined set.

<p>
     The BuiltIn Enums are:
<pre>
    {@link CoreBuiltIn}
    {@link LocaleBuiltIn}
    {@link UniBlockBuiltIn}
    {@link UniScriptBuiltIn}
</pre>
     See each of these Enums and {@link BuiltInCharClass} for further explanation.
<p>

     Note also that the method {@link Utilities#find Utilities.find} allows a
     user (among other things) to find BuiltIn constants in any {@link
     BuiltInCharClass}.

*/

public interface BuiltInInterface
{
  /** @return a string, which when enclosed by '['  ']' is  a regular
  expression which matches a single character.
  For limitations on the regular expression see {@link RegExpPattern}.
  */
  CharSequence getPreRegularExpression();
}
