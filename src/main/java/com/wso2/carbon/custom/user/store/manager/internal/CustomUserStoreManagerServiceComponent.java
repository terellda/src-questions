package com.wso2.carbon.custom.user.store.manager.internal;

import com.wso2.carbon.custom.user.store.manager.CustomUserStoreManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.user.core.UserStoreManager;
import org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;

//import java.util.Hashtable;

/**
 * @scr.component name="com.wso2.carbon.custom.user.store.manager" immediate="true"
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */

public class CustomUserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(CustomUserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    @Activate
    protected void activate(ComponentContext ctxt) {
        //Hashtable<String, String> props = new Hashtable<String, String>();
        try {
            CustomUserStoreManager ohsuCustomUserStoreManager = new CustomUserStoreManager();
            ctxt.getBundleContext().registerService(UserStoreManager.class.getName(), ohsuCustomUserStoreManager, null);
            log.info("ohsuCustomUserStoreManager bundle activated successfully..");
        } catch (Throwable storeError) {
            log.error("ERROR when activating ohsuCustomUserStoreManager", storeError);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("ohsuCustomUserStoreManager bundle is deactivated");
        }
    }

    protected void setRealmService(RealmService realmService) {
        realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }

    /*
    protected void setRealmService(RealmService realmService) {
        log.debug("Setting the Realm Service");
        CustomUserStoreManagerServiceComponent.realmService = realmService;
    }


    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        CustomUserStoreManagerServiceComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
    */
}
