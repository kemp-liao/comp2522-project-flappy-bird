package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BackgroundTest {

	Background background;
	PImage img;
	PVector position;
	float moveSpeed;
	Window window;

	@Test
	public void Background() {
		PImage img = null;
		PVector position = null;
		float moveSpeed = 0.0F;
		Window window = null;
		Background expected = null;
		Background actual = new Background(img, position, moveSpeed, window);

	}

	@Test
	void checkHeight(){
		Background background = new Background(img, position, moveSpeed, window);
		assertEquals(720, background.getHeight());

	}

	@Test
	void checkWidth(){
		Background background = new Background(img, position, moveSpeed, window);
		assertEquals(925, background.getWidth());

	}

	@Test
	void notNull(){
		assertNotNull(true, String.valueOf(true));
	}

	@Test
	void shouldShowSimpleAssertion() {
		Assertions.assertEquals(1, 1);
	}





}
