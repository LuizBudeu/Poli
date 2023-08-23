package br.usp.ime.mac0321.aula07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestaFajuta {

	@Test
	void testeSemExceção() {
		try {
			Fajuta.calcula(7);
		}
		catch (ProprioErro e) {
			fail();
		}
	}
	
	@Test
	void testaProprioErro(){
		assertThrows(
			ProprioErro.class,
			() -> {Fajuta.calcula(300);}
		);
	}

	
}
