package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WindowTest {

	@BeforeEach
	void setup() {
		Window window= new Window();
	}


	@Test
	public void main() {
		String[] passedArgs = null;
		Window.main(passedArgs);
	}

	@Test
	void shouldShowSimpleAssertion() {
		Assertions.assertEquals(1, 1);
	}

	@Test
	void notNull(){
		assertNotNull(true, String.valueOf(true));
	}


}
