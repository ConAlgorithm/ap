package network.relationship;

import network.relationship.domain.Application;
import network.relationship.domain.ApplicationHasMobileRelation;
import network.relationship.domain.Mobile;
import network.relationship.domain.User;
import network.relationship.domain.UserHasApplicationRelation;
import network.relationship.domain.UserHasMobileRelation;
import network.relationship.service.ApplicationHasMobileRelationService;
import network.relationship.service.ApplicationService;
import network.relationship.service.IDomainService;
import network.relationship.service.MobileService;
import network.relationship.service.UserHasApplicationRelationService;
import network.relationship.service.UserHasMobileRelationService;
import network.relationship.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public IDomainService<Application> applicationService() {
        return new ApplicationService();
    }
    
    @Bean
    public IDomainService<User> userService() {
        return new UserService();
    }
    
    @Bean
    public IDomainService<Mobile> mobileService() {
        return new MobileService();
    }
    
    @Bean
    public IDomainService<UserHasApplicationRelation> userHasApplicationRelationService() {
        return new UserHasApplicationRelationService();
    }
    
    @Bean
    public IDomainService<UserHasMobileRelation> userHasMobileRelationService() {
        return new UserHasMobileRelationService();
    }
    
    @Bean
    public IDomainService<ApplicationHasMobileRelation> applicationHasMobileRelationService() {
        return new ApplicationHasMobileRelationService();
    }
}
