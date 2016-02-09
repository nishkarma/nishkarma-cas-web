/*
  Copyright 2012 - 2014 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.nishkarma.oauth.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * This class is the user profile for Google (using OAuth protocol version 2) with appropriate getters.<br />
 * It is returned by the {@link org.pac4j.oauth.client.Google2Client}.
 * <p />
 * <table border="1" cellspacing="2px">
 * <tr>
 * <th>Method :</th>
 * <th>From the JSON profile response :</th>
 * </tr>
 * <tr>
 * <th colspan="2">The attributes of the {@link org.pac4j.core.profile.CommonProfile}</th>
 * </tr>
 * <tr>
 * <td>String getEmail()</td>
 * <td>the <i>email</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFirstName()</td>
 * <td>the <i>givenName</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFamilyName()</td>
 * <td>the <i>familyName</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getDisplayName()</td>
 * <td>the <i>displayName</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getUsername()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <td>Gender getGender()</td>
 * <td>the <i>gender</i> attribute</td>
 * </tr>
 * <tr>
 * <td>Locale getLocale()</td>
 * <td>the <i>language</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getPictureUrl()</td>
 * <td>the <i>image.url</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getProfileUrl()</td>
 * <td>the <i>url</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getLocation()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <th colspan="2">More specific attributes</th>
 * </tr>
 * <tr>
 * <td>Date getBirthday()</td>
 * <td>the <i>birthday</i> attribute</td>
 * </tr>
 * <tr>
 * <td>List&lt;Google2Email&gt; getEmails()</td>
 * <td>the <i>emails</i> attribute</td>
 * </tr>
 * </table>
 *
 * @see org.pac4j.oauth.client.Google2Client
 * @author Jerome Leleu
 * @since 1.2.0
 */
public class MyGoogle2Profile extends OAuth20Profile {

    private static final long serialVersionUID = -7486869356444327783L;

    @Override
    protected AttributesDefinition getAttributesDefinition() {
    	return new MyGoogle2AttributesDefinition();
    }

    @Override
    public String getEmail() {
    	return (String) getAttribute(MyGoogle2AttributesDefinition.EMAIL);
    }

    @Override
    public String getDisplayName() {
        return (String) getAttribute(MyGoogle2AttributesDefinition.DISPLAY_NAME);
    }

}
