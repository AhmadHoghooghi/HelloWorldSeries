package ir.asta.tutorial.dl.manager;

import ir.asta.tutorial.dl.dao.AttachmentTypeDao;
import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import mockit.Mock;
import mockit.MockUp;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class AttachmentTypeManagerTest {

	//@BeforeClass
	public static void init() {

		new MockUp<AttachmentTypeDao>() {

			@Mock
			public AttachmentTypeEntity load(String id) {
				return null;
			}

		};

	}

	//@Test
	public void testSomeMethod() {

	}

}
