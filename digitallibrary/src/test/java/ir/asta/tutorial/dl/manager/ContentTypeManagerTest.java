package ir.asta.tutorial.dl.manager;

import ir.asta.tutorial.dl.dao.ContentTypeDao;
import ir.asta.tutorial.dl.entities.ContentTypeEntity;
import mockit.Mock;
import mockit.MockUp;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class ContentTypeManagerTest {

	//@BeforeClass
	public static void init() {

		new MockUp<ContentTypeDao>() {

			@Mock
			public ContentTypeEntity load(String id) {
				return null;
			}

		};

	}

	//@Test
	public void testSomeMethod() {

	}

}
