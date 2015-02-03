package ru.yesdo.graph;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by lameroot on 21.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractConfigTest extends TestCase {

    /*
    protected boolean contains(Collection collection, Object object) {
        collection.stream().filter(a -> a.)


        assertTrue(foundActivity1.getParents().stream()
                .filter(a -> a.getTitle().equals(activityLevel1Num1.getTitle())).findFirst().get().getTitle().equals(activityLevel1Num1.getTitle()));

    }
    */
}
