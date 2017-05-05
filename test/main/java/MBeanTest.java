import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Log;
import org.junit.Test;
import org.mbean.MBeanContainer;

import java.lang.management.ManagementFactory;

/**
 * Created by Insigma US on 5/5/2017.
 */
public class MBeanTest {

    @Test
    public void testMBean() {
        Server server = new Server();
        MBeanContainer mbContainer=new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.getContainer().addEventListener(mbContainer);
        mbContainer.addBean(Log.getLog());
    }
}
