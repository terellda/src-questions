package com.wso2.custom.usermgt;

import org.wso2.carbon.user.api.Property;
import org.wso2.carbon.user.core.UserStoreConfigConstants;
import org.wso2.carbon.user.core.jdbc.JDBCRealmConstants;

import java.util.ArrayList;

public class CustomUserStoreManagerConstants {

    public static final ArrayList<Property> MANDATORY_PROPERTIES = new ArrayList<Property>();
    public static final ArrayList<Property> OPTIONAL_PROPERTIES = new ArrayList<Property>();
    public static final ArrayList<Property> ADVANCED_PROPERTIES = new ArrayList<Property>();

    static
    {
        setMandatoryProperty(JDBCRealmConstants.DRIVER_NAME, "Driver Name", "", "Full qualified driver name");
        setMandatoryProperty(JDBCRealmConstants.URL, "Connection URL", "", "URL of the user store database");
        setMandatoryProperty(JDBCRealmConstants.USER_NAME, "User Name", "", "Username for the database");
        setMandatoryProperty(JDBCRealmConstants.PASSWORD, "Password", "", "Password for the database");

        setProperty(UserStoreConfigConstants.disabled, "Disabled", "false", UserStoreConfigConstants.disabledDescription);
        setProperty("ReadOnly", "Read Only", "true", "Indicates whether the user store of this realm operates in the user read only mode or not");
        setProperty(UserStoreConfigConstants.SCIMEnabled, "SCIM Enabled", "false", UserStoreConfigConstants.SCIMEnabledDescription);

        //SELECT spriden_id, gobtpac_pidm, gobtpac_pin, gobtpac_salt from spriden INNER JOIN gobtpac ON spriden_id=? and spriden_pidm = gobtpac_pidm and spriden_change_ind IS NULL
        setAdvancedProperty("SelectUserSQL", "Select User SQL", "SELECT spriden_id, gobtpac_pidm, gobtpac_pin, gobtpac_salt FROM" +
                " spriden INNER JOIN gobtpac ON spriden_id=? and spriden_pidm = gobtpac_pidm and spriden_change_ind IS NULL", "");

        setAdvancedProperty("UserFilterSQL", "User Filter SQL", "SELECT USER_NAME FROM TEST_USER WHERE USER_NAME LIKE" +
                " ? ORDER BY USER_NAME", "");

        setAdvancedProperty("IsUserExistingSQL", "Is User Existing SQL", "SELECT USER_NAME FROM TEST_USER WHERE " +
                "USER_NAME=? ", "");
    }

    private static void setProperty(String name, String displayName, String value, String description) {
        Property property = new Property(name, value, displayName + "#" + description, null);
        OPTIONAL_PROPERTIES.add(property);
    }

    private static void setMandatoryProperty(String name, String displayName, String value, String description) {
        Property property = new Property(name, value, displayName + "#" + description, null);
        MANDATORY_PROPERTIES.add(property);
    }

    private static void setAdvancedProperty(String name, String displayName, String value, String description) {
        Property property = new Property(name, value, displayName + "#" + description, null);
        ADVANCED_PROPERTIES.add(property);
    }
}
