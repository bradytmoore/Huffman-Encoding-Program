public class Name {
    private String name = "";
    private Double rank;
    private String gender = "";


    public Name() {
    }

    public Name(String name, Double rank, String gender) {
        this.name = name;
        this.rank = rank;
        this.gender = gender;
    }

    public Name(Name n) {
        name = n.getName();
        rank = n.getRank();
        gender = n.getGender();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){

        return getName();
    }
}
