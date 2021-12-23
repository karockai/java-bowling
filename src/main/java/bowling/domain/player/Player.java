package bowling.domain.player;

import java.util.Objects;

public class Player {
    private final Name name;

    private Player(String name) {
        this.name = Name.from(name);
    }

    public static Player from(String name) {
        return new Player(name);
    }

    public String name() {
        return name.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}