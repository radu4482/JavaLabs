package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@RestController
@RequestMapping("/RPS")
public class RockPaperScissors {

    PlayerRPS bot;

    public RockPaperScissors() {
        this.bot = new PlayerRPS();
    }

    public String choose(int value) {
        if (value == 0) return "Rock";
        else if (value == 1) return "Paper";
        else if (value == 2) return "Scissors";
        return null;
    }

    @PutMapping
    public ResponseEntity<String> test(@RequestParam int value) {
        this.bot.randomChoose();
        String theChoose = choose(value);
        if (theChoose == null) return new ResponseEntity<>("Err" + theChoose, HttpStatus.OK);
        if (bot.theChoose == "Rock") {
            if (theChoose == "Rock") return new ResponseEntity<>("TIE! " + "you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Paper")
                return new ResponseEntity<>("You won!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Scissors")
                return new ResponseEntity<>("You lost!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
        } else if (bot.theChoose == "Paper") {
            if (theChoose == "Rock")
                return new ResponseEntity<>("You lost!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Paper")
                return new ResponseEntity<>("TIE!" + "you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Scissors")
                return new ResponseEntity<>("You won!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
        } else {
            if (theChoose == "Rock")
                return new ResponseEntity<>("You won!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Paper")
                return new ResponseEntity<>("You lost!He choosed " + bot.theChoose + " you choosed " + theChoose, HttpStatus.OK);
            else if (theChoose == "Scissors")
                return new ResponseEntity<>("TIE!" + "you choosed " + theChoose, HttpStatus.OK);
        }
        return new ResponseEntity<>("Eend" + theChoose, HttpStatus.OK);
    }
}
