public enum Things {
    THING_ONE("One",1), THING_TWO("Two",2), THING_THREE("Three",3);
    Things(String s, int i){
        number = i;
        name = s;
    }

    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

}
