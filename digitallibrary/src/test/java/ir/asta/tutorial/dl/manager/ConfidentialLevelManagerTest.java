package ir.asta.tutorial.dl.manager;

import ir.asta.tutorial.dl.dao.ConfidentialLevelDao;
import ir.asta.tutorial.dl.entities.ConfidentialLevelEntity;
import ir.asta.tutorial.dl.util.DlUtils;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(JMockit.class)
public class ConfidentialLevelManagerTest {

    @Tested
    ConfidentialLevelManager manager;

    @Injectable
    ConfidentialLevelDao dao;

    @Mocked
    DlUtils DlUtils;




    @Test
    public void getValidConfidentialLevelsForCurrentUserTestHappyPath() {
        new Expectations(manager) {{

            DlUtils.getCurrentUserConfidentialLevelIndex();
            result = 2L;

            manager.loadAll();
            ConfidentialLevelEntity cl1 = ConfidentialLevelEntity.builder().index(1L).build();
            ConfidentialLevelEntity cl2 = ConfidentialLevelEntity.builder().index(2L).build();
            ConfidentialLevelEntity cl3 = ConfidentialLevelEntity.builder().index(3L).build();
            result = Arrays.asList(cl1, cl2, cl3);



        }};
        List<ConfidentialLevelEntity> confidentialLevels = manager.getValidConfidentialLevelsForCurrentUser();
        assertThat(confidentialLevels.size(), is(equalTo(2)));
    }
}