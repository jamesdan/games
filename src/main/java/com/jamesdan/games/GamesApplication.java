package com.jamesdan.games;

import com.jamesdan.games.service.TwoZeroFourEight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GamesApplication implements CommandLineRunner {

	private final TwoZeroFourEight twoZeroFourEight;

	@Autowired
    public GamesApplication(TwoZeroFourEight twoZeroFourEight) {
        this.twoZeroFourEight = twoZeroFourEight;
    }

    public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Override
	public void run(String... args) {
        try {
            twoZeroFourEight.startGame();
        } catch (IOException e) {
			System.err.println("An error occurred while running the 2048 game: " + e.getMessage());
        }
    }
}
