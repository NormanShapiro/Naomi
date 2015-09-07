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

  Thrown to indicate that an attempt has been made by one {@link Pattern} to
  use another Pattern more than once.

<p>
For example (attempting to match "hellohello"):

<pre>
    Pattern hello = new CharSequencePattern("hello");
    Pattern helloTwice = new ConcatenatePattern(hello,hello); // invalid
    Matcher matcher = helloTwice("farewell");
    matcher.find();			      // Throws ReuseException
</pre>

One way to do this would be:
<p>
<pre>
    Pattern hello = new CharSequencePattern("hello");
    Pattern hello2 = new CharSequencePattern("hello");
    Pattern helloTwice = new ConcatenatePattern(hello,hello2);
    Matcher matcher = helloTwice("farewell");
</pre>

though of course, it could also be done with:
<p>
<pre>
    Pattern helloTwice = new CharSequencePattern("hello");
    helloTwice.setMinAndMaxCount(2,2);
    Matcher matcher = helloTwice("farewell");
</pre>

Or even:
<p>
<pre>
    Pattern helloTwice = new CharSequencePattern("hellohello");
    Matcher matcher = helloTwice("farewell");
</pre>

*/
public class ReuseException extends RuntimeException
{
  ReuseException(String message) {super(message);}

  ReuseException(Pattern pattern)
  {
     this("Reuse of " +pattern.getClass());
  }
}
