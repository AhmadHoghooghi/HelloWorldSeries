package ir.asta.tutorial.dl.manager;

import ir.asta.tutorial.dl.dao.ContentDao;
import ir.asta.tutorial.dl.dto.ContentStatusChangeDto;
import ir.asta.tutorial.dl.dto.ContentStatusChangeResultDto;
import ir.asta.tutorial.dl.entities.*;
import ir.asta.tutorial.dl.util.DlUtils;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.util.db.SequenceGenerator;
import mockit.*;

import mockit.integration.junit4.JMockit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@RunWith(JMockit.class)
public class ContentManagerTest {

    @Tested
    ContentManager contentManager;

    @Injectable
    ContentDao contentDao;
    @Injectable
    SequenceGenerator sequenceGenerator;
    @Injectable
    ConfidentialLevelManager confidentialLevelManager;

    @Mocked
    DlUtils DlUtils;

    @BeforeClass
    public static void init() {
        new MockUp<ContentDao>() {

            @Mock
            public ContentEntity load(String id) {
                return null;
            }

        };
    }

    @Test
    public void beforeMergeIsPassedWhenUserHasProperConfidentialLevel() {
        new Expectations() {{
            DlUtils.getCurrentUserConfidentialLevelIndex();
            result = 3L;

            DlUtils.isCurrentUserAuthorizedToChangeContentStatus();
            result = true;
        }};

        ConfidentialLevelEntity cl = ConfidentialLevelEntity.builder().index(3L).build();

        // LibraryEntity library = LibraryEntity.builder().
        ContentTypeEntity contentType = ContentTypeEntity.builder().id(1L).build();
        LibraryEntity library = LibraryEntity.builder().contentType(contentType).build();
        ContentEntity entity = ContentEntity.builder()
                .confidentialLevel(cl)
                .contentType(contentType)
                .library(library)
                .build();
        contentManager.beforeMerge(entity, true);
    }

    @Test(expected = LocalizedException.class)
    public void beforeMergeThrowsExceptionWhenUserHasNotProperConfidentialLevel() {
        new Expectations() {{
            DlUtils.getCurrentUserConfidentialLevelIndex();
            result = 2L;
        }};

        ConfidentialLevelEntity cl = ConfidentialLevelEntity.builder().index(3L).build();
        ContentEntity entity = ContentEntity.builder().confidentialLevel(cl).build();
        contentManager.beforeMerge(entity, true);
    }

    @Test
    public void testChangeStatusHappyPath(){
        String idInProcess = ContentStatus.IN_PROCESS.toString();
        String idVerified = ContentStatus.VERIFIED.toString();
        String idRejected = ContentStatus.REJECTED.toString();

        new NonStrictExpectations(contentManager){{
            //
            contentManager.load(idInProcess);
            result = ContentEntity.builder().id(idInProcess).status(ContentStatus.IN_PROCESS).build();
            contentManager.load(idVerified);
            result = ContentEntity.builder().id(idVerified).status(ContentStatus.VERIFIED).build();
            contentManager.load(idRejected);
            result = ContentEntity.builder().id(idRejected).status(ContentStatus.REJECTED).build();

            contentManager.update((ContentEntity)any);
        }};


        ContentStatusChangeDto changeDTO = new ContentStatusChangeDto();
        String[] pkArray = {idInProcess,idVerified,idRejected};
        changeDTO.setPkArray(Arrays.asList(pkArray));
        changeDTO.setNewStatus(ContentStatus.VERIFIED);

        ContentStatusChangeResultDto resultDto = contentManager.changeStatus(changeDTO);

        assertThat(resultDto.getSuccessfulChanges(), is(equalTo(1)));
        assertThat(resultDto.getFailedChanges(), is(equalTo(2)));

    }

    @Test
    public void myFirstIntegrationTest(){

    }

}
