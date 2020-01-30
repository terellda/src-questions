package com.wso2.custom.usermgt.internal;

import com.wso2.custom.usermgt.OhsuBannerCustomUserStoreManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.service.RealmService;

import java.util.Hashtable;

public class CustomUserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(CustomUserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    protected void activate(ComponentContext ctxt) throws UserStoreException {
        Hashtable<String, String> props = new Hashtable<String, String>();

        OhsuBannerCustomUserStoreManager ohsuCustomUserStoreManager = new OhsuBannerCustomUserStoreManager();

        ctxt.getBundleContext().registerService(OhsuBannerCustomUserStoreManager.class.getName(), ohsuCustomUserStoreManager, props);

        log.info("ohsuCustomUserStoreManager bundle activated successfully..");
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("ohsuCustomUserStoreManager bundle is deactivated");
        }
    }

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
}
