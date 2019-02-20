package ir.asta.tutorial.dl.manager;

import ir.asta.tutorial.dl.dao.AttachmentDao;
import ir.asta.tutorial.dl.entities.AttachmentEntity;
import mockit.Mock;
import mockit.MockUp;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class AttachmentManagerTest {

	//@BeforeClass
	public static void init() {

		new MockUp<AttachmentDao>() {

			@Mock
			public AttachmentEntity load(String id) {
				return null;
			}

		};

	}

	//@Test
	public void testSomeMethod() {

	}

}
