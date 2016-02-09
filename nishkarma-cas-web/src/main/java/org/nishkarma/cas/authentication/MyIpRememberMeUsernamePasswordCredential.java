package org.nishkarma.cas.authentication;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jasig.cas.authentication.RememberMeUsernamePasswordCredential;

public class MyIpRememberMeUsernamePasswordCredential extends RememberMeUsernamePasswordCredential {

    /**
	 * 
	 */
	private static final long serialVersionUID = 803210388728321194L;
	
	private boolean ipProtect;

	public boolean isIpProtect() {
		return ipProtect;
	}

	public void setIpProtect(boolean ipProtect) {
		this.ipProtect = ipProtect;
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(ipProtect)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyIpRememberMeUsernamePasswordCredential other = (MyIpRememberMeUsernamePasswordCredential) obj;
        if (this.ipProtect != other.ipProtect) {
            return false;
        }
        return true;
    }	
}
