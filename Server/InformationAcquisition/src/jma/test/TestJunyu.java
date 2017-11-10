package jma.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import jma.Configuration;
import jma.handlers.CheckJunyu3PhotoHandler;
import jma.thirdpartyservices.junyu.AlternativeUrlsContainer;
import jma.thirdpartyservices.junyu.JYChecker;

import org.dom4j.DocumentException;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestJunyu {
  public static void main(String[] args) throws NoSuchAlgorithmException, IOException, DocumentException {
    StartupConfig.initialize();
    Logger.initialize();
    Configuration.readConfiguration();
    PersistenceConfig.initialize();
    HttpClientConfig.initialize();

    new CheckJunyu3PhotoHandler().execute("50baf87a-907f-e411-98e3-ac853da49bec");

   // System.out.println(JYChecker.callJunYu("50baf87a-907f-e411-98e3-ac853da49bec", new AlternativeUrlsContainer(), 0));
   // System.out.println(JYChecker.callJunYu("50baf87a-907f-e411-98e3-ac853da49bec", new AlternativeUrlsContainer(), 1));
  }
}
