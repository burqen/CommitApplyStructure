import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestClass {


	@Test
	public void handleOnly1Apply() {
		CommitApplyStructure struct = new CommitApplyStructure();
		struct.commit();
		struct.apply(0);
		assertEquals(0, struct.lead());
	}

	@Test
	public void shouldHandleMoreThan1Apply()
	{
		CommitApplyStructure struct = new CommitApplyStructure();
		for( int i = 0; i < 10; i++)
		{
			struct.commit();
			struct.apply(i);
			assertEquals(i, struct.lead());
		}
	}
	
	@Test
	public void shouldHandleApplyOutOfOrder() {
		CommitApplyStructure struct = new CommitApplyStructure();
		for (int i = 0; i < 4; i++) {
			struct.commit();
		}
		struct.apply(0);
		assertEquals(0, struct.lead());
		struct.apply(2);
		assertEquals(0, struct.lead());
		struct.apply(1);
		assertEquals(2, struct.lead());
	}
	
	@Test
	public void shouldHandleNoLeader() {
		CommitApplyStructure struct = new CommitApplyStructure();
		assertEquals(-1, struct.lead());
	}
	
	@Test
	public void shouldHandleApplyNotYetCommited() {
		CommitApplyStructure struct = new CommitApplyStructure();
		struct.apply(0);
		assertEquals(-1, struct.lead());
	}
}
