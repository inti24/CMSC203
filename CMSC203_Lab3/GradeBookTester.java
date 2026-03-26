import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
GradeBook s1;
GradeBook s2;
	@BeforeEach
	void setUp() throws Exception {
		//task3
		s1 = new GradeBook(5);
		s2 = new GradeBook(5);
		s1.addScore(2.0);
		s1.addScore(3.0);
		s2.addScore(5.0);
		s2.addScore(6.0);
		s2.addScore(7.0);
	}

	@AfterEach
	void tearDown() throws Exception {
		 s1 = null;
		 s2 = null;
		
	}

	@Test
	void testAddScore() {
		assertTrue(s1.toString().equals("2.0 3.0 "));
		assertTrue(s2.toString().equals("5.0 6.0 7.0 "));
		
	}

	@Test
	void testSum() {
		assertEquals(5,s1.sum(),.0001);
		assertEquals(18,s2.sum(),.0001);
	}

	@Test
	void testMinimum() {
		assertEquals(2,s1.minimum(),.001);
		assertEquals(5,s2.minimum(),.001);
	}

	@Test
	void testFinalScore() {
		assertEquals(3,s1.finalScore(),.001);
		assertEquals(13,s2.finalScore(),.001);
		
	}

	@Test
	void testGetScoreSize() {
		assertEquals(2,s1.getScoreSize());
		assertEquals(3,s2.getScoreSize());
	}

	@Test
	void testToString() {
		assertEquals("2.0 3.0 ",s1.toString());
		assertEquals("5.0 6.0 7.0 ",s2.toString());
	}

}
