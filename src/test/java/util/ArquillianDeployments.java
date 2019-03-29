package util;

import business.ProfileService;
import data.ProfileDaoJPA;
import data.interfaces.ProfileDao;
import domain.Profile;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;


public class ArquillianDeployments {
//
//    @Deployment(name = "full-project", order = 1)
//    public static WebArchive createFullProjectDeployment() {
//        return ShrinkWrap.create(EmbeddedGradleImporter.class)
//                .forThisProjectDirectory()
//                .importBuildOutput().as(WebArchive.class);
//    }
}
