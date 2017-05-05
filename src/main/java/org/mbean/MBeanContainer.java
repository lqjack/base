package org.mbean;

import org.eclipse.jetty.util.component.Container;

import javax.management.MBeanServer;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Insigma US on 5/5/2017.
 */
public class MBeanContainer implements Container.Listener {

    private final Collection<Object> mbeans = new ArrayList<>();

    private final Collection<Container.Relationship> relationships = new ArrayList<>();

    MBeanServer mBeanServer;

    public MBeanContainer(MBeanServer platformMBeanServer) {
        mBeanServer = platformMBeanServer;
    }

    @Override
    public void addBean(Object o) {
        mbeans.add(o);
    }

    @Override
    public void removeBean(Object o) {
        mbeans.remove(o);
    }

    @Override
    public void add(Container.Relationship relationship) {
        relationships.add(relationship);
    }

    @Override
    public void remove(Container.Relationship relationship) {
        relationships.remove(relationship);
    }
}
