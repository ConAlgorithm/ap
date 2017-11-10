package network.relationship.service;

import java.util.HashMap;
import java.util.Map;

import network.relationship.CatfishNeo4jConfiguration;
import network.relationship.domain.Application;
import network.relationship.domain.ApplicationHasMobileRelation;
import network.relationship.domain.IDomain;
import network.relationship.domain.Mobile;
import network.relationship.domain.User;
import network.relationship.domain.UserHasApplicationRelation;
import network.relationship.domain.UserHasMobileRelation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceManager {

    private ApplicationContext _context;

    private Map<String, String> _serviceMap;

    private static ServiceManager serviceManager = new ServiceManager();

    private ServiceManager() {
        _context = _getContext();
        _serviceMap = _getServiceMap();
    }

    private ApplicationContext _getContext() {
//        _context = new ClassPathXmlApplicationContext("spring-conf.xml");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CatfishNeo4jConfiguration.class);
        //ctx.scan("Catfish.NetworkRelationshipPlugin");
        ctx.refresh();
        _context = ctx;
        return _context;
    }

    private Map<String, String> _getServiceMap() {
        _serviceMap = new HashMap<String, String>();
        _serviceMap.put(Application.class.getName(), "applicationService");
        _serviceMap.put(User.class.getName(), "userService");
        _serviceMap.put(Mobile.class.getName(), "mobileService");
        _serviceMap.put(UserHasApplicationRelation.class.getName(),
                "userHasApplicationRelationService");
        _serviceMap.put(UserHasMobileRelation.class.getName(),
                "userHasMobileRelationService");
        _serviceMap.put(ApplicationHasMobileRelation.class.getName(),
                "applicationHasMobileRelationService");
        return _serviceMap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends IDomain> IDomainService<T> getService(
            Class<T> serviceClass) {
        if (serviceManager._serviceMap.containsKey(serviceClass.getName())) {
            return (IDomainService<T>) serviceManager._context.getBean(
                    serviceManager._serviceMap.get(serviceClass.getName()));
        }
        return null;
    }
}
