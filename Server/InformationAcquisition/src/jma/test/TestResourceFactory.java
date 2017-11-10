package jma.test;

import grasscarp.user.model.User;

import org.springframework.util.Assert;

import thirdparty.config.YLZHConfiguration;
import jma.Configuration;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.cowfish.application.model.ApplicationModel;

public class TestResourceFactory {

  public static void main(String[] args) {
    StartupConfig.initialize();
    Logger.initialize();
    Configuration.readConfiguration();
    YLZHConfiguration.readConfiguration();
    HttpClientConfig.initialize();
    PersistenceConfig.initialize();

    ApplicationModel app = CLApplicationResourceFactory.getApplication("DCE50AA0-4D21-4B1D-AA8C-B1C5523F7D0A");
    Assert.notNull(app);

    User user = UserResourceFactory.getUser("C47423F3-5E8C-E511-ACD8-AC853D9F5508");
    Assert.notNull(user);
  }

}
