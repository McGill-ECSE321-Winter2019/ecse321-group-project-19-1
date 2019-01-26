package ca.mcgill.ecse321.bingo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class BingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting(){
		return "Hello we are group 19!";
	}
}
