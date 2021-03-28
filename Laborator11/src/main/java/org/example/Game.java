package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class Game {
    private final List<Player> players = new ArrayList<>();

    public Game() {
        players.add(new Player(this, "Alex"));
        players.add(new Player(this, "Radu"));
        players.add(new Player(this, "Ciub"));
    }


    //GET
    @GetMapping
    public List<Player> getPlayers() {
        return players;
    }

    @GetMapping("/players")
    public List<String> getPlayersName() {
        List<String> names = new ArrayList<>();
        for (Player p : players) names.add(p.getName());
        return names;
    }

    @GetMapping("/count")
    public int getPlayersNumber() {
        return players.size();
    }

    @GetMapping("/{id}")
    public Player getProduct(@PathVariable("id") int id) {
        return players.stream()
                .filter(p -> p.getId() == id).findFirst().orElse(null);
    }


    //POST
    @PostMapping
    public void createPlayer(@RequestParam String name) {
        players.add(new Player(this, name));
    }


    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id, @RequestParam String name) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Product not found", HttpStatus.NOT_FOUND); //or GONE
        }
        player.setName(name);
        return new ResponseEntity<>(
                "Product updated successsfully", HttpStatus.OK);
    }

    public Player findById(int id) {
        for (Player p : players)
            if (p.getId() == id)
                return p;
        return null;
    }


    public boolean ocupiedId(int Id) {
        for (Player p : players)
            if (p.Id == Id)
                return true;
        return false;
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Product not found", HttpStatus.GONE);
        }
        players.remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }


}
