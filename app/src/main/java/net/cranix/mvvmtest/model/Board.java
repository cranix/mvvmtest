package net.cranix.mvvmtest.model;

public class Board {

    public final String title;

    public Board(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Board{" +
                "title='" + title + '\'' +
                '}';
    }
}
