import java.util.ArrayList;

public class Compress {

    private Name n;
    private String code;

    public Compress() {
    }

    public Compress(Name n, String code) {
        this.n = n;
        this.code = code;
    }

    public Compress(Compress c){
        code = c.getCode();
    }

    public String getName() {
        return n.getName();
    }

    public Double getRank(){
        return n.getRank();
    }

    public String getGender(){
        return n.getGender();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString(){

        return n.getName() + "t/" + this.code;
    }

    public static void bubbleSort(ArrayList<Compress> list){

        Compress tempbs;


        for(int x = list.size() - 1; x >= 0; x--){
            for(int i = 0; i <= x - 1; i++){
                if(list.get(i).code.length() > list.get(i + 1).code.length()){

                    tempbs = list.get(i);
                    list.get(i).equals(list.get(i+1));
                    list.get(i+1).equals(tempbs);

                }
            }
        }
        }






}
