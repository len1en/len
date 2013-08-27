import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.either;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/junit.xml")
public class JUnitTest {
	@Autowired
	private ApplicationContext context;
	
	static ApplicationContext contextObject = null;
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

	@Test
	public void test1() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void test2() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		assertTrue(contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test
	public void test3() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
	}
}	
