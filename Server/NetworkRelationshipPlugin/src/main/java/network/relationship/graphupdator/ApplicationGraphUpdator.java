package network.relationship.graphupdator;

import network.relationship.domain.Application;
import network.relationship.domain.ApplicationHasMobileRelation;
import network.relationship.domain.IDomain;
import network.relationship.domain.Mobile;
import network.relationship.domain.User;
import network.relationship.domain.UserHasApplicationRelation;
import network.relationship.domain.UserHasMobileRelation;
import network.relationship.service.ServiceManager;

import java.util.LinkedList;
import java.util.List;

import catfish.base.Logger;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;

public class ApplicationGraphUpdator implements IGraphUpdator {

    private String _appId;

    public ApplicationGraphUpdator(String appId) {
        super();
        this._appId = appId;
    }

    private <T extends IDomain> T createIfNotExists(Class<T> tClass, T t) {
        return ServiceManager.getService(tClass).createIfNotExists(t);
    }

    public static <T extends IDomain> Boolean exists(Class<T> tClass, T t) {
        return ServiceManager.getService(tClass).exists(t);
    }

    public static <T extends IDomain> Iterable<T> queryAppNodes(String appId, Class<T> tClass, T t) {

        return ServiceManager.getService(tClass).queryAppNodes(appId,t);
    }

    public static <T extends IDomain> Iterable<T> queryUserNodes(String appId, Class<T> tClass, T t) {
        return ServiceManager.getService(tClass).queryUserNodes(appId,t);
    }

    public long update(ApplicationRelatedInfo appDirectGraph) {
        Logger.get().info("Start updating graph by AppId : " + _appId);

        Application app = createIfNotExists(Application.class,
                appDirectGraph.app);

        User user = createIfNotExists(User.class, appDirectGraph.user);

        createIfNotExists(UserHasApplicationRelation.class,
                new UserHasApplicationRelation(user, app));

        Mobile userMobile = createIfNotExists(Mobile.class,
                appDirectGraph.userMobile);

        createIfNotExists(UserHasMobileRelation.class,
                new UserHasMobileRelation(user, userMobile));

        for (Mobile mobile : appDirectGraph.contactMobileList) {
            Mobile tmp = createIfNotExists(Mobile.class, mobile);

            createIfNotExists(ApplicationHasMobileRelation.class,
                    new ApplicationHasMobileRelation(app, tmp));

        }
        

        Logger.get().info("End updating graph by AppId : " + _appId);
        return app.getId();
    }

    public void getAppNodeList(Application app, List<Application> list){
        Iterable<Application> res= queryAppNodes(_appId, Application.class, app);
        if(res.iterator().hasNext()){
            res.forEach( x -> list.add(x));
        }
    }

    public void getUserNodeList(User user, List<User> list){
        Iterable<User> res= queryUserNodes(_appId, User.class, user);
        if(res.iterator().hasNext()){
            res.forEach( x -> list.add(x));
        }
    }
    
    public static ApplicationRelatedInfo createApplicationDirectGraph(String appId) {
        
        ApplicationRelatedInfo appDirectGraph = new ApplicationRelatedInfo();
        
        appDirectGraph.app = new Application(appId);
        
        EndUserExtensionObject endUser =new EndUserExtentionDao(appId).getSingle();
        if (endUser != null) {
            appDirectGraph.user = new User(endUser.IdName, endUser.IdNumber);
        }
        
        ContactDao contactDao = new ContactDao(appId); 
        
        ContactObject userMobile = contactDao.getSingle();
        if (userMobile != null) {
            appDirectGraph.userMobile = new Mobile(userMobile.Content);
        }
        
        List<Mobile> contactPersonMobiles = new LinkedList<Mobile>();
        List<ContactObject> contactPersonContacts = 
                ContactDao.getContactPersonContacts(appId);
        if (contactPersonContacts != null && !contactPersonContacts.isEmpty()) {
            for(ContactObject contact : contactPersonContacts) {
                if (contact != null) {
                    contactPersonMobiles.add(new Mobile(contact.Content));
                }
            }
        }
        appDirectGraph.contactMobileList = contactPersonMobiles;
        
        return appDirectGraph;
    }
    
    public static class ApplicationRelatedInfo {

        public Application app;
        
        public User user;
        
        public Mobile userMobile;
        
        public List<Mobile> contactMobileList;
    }
}