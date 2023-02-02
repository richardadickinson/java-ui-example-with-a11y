package utils.Tests;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

public class EmbeddedJetty {

    private final Server jettyServer;

    public EmbeddedJetty(){
        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
        System.setProperty("org.eclipse.jetty.LEVEL", "OFF");
        jettyServer = new Server(getPort());
    }
    public int getPort(){ return 442; }

    public void start() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(Resource.newClassPathResource("/www"));
        jettyServer.setHandler(resourceHandler);
        jettyServer.start();
        Thread.sleep(5000);
    }

    public void stop() throws Exception {
        jettyServer.stop();
    }
}
