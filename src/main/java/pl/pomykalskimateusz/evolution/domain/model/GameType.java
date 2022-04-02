package pl.pomykalskimateusz.evolution.domain.model;

public enum GameType {
    FREE("Free game"),
    CASH("Cash game");

    private final String name;

    GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
