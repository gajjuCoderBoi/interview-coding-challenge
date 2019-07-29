
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class findpair {

    public static class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String toString() {
            return name + " " + price;
        }
    }

    public static class Pair {
        Item item1,item2;

        public Pair(Item item1, Item item2) {
            this.item1 = item1;
            this.item2 = item2;
        }
        public String toString(){return ""+item1+", "+item2;}

    }

    public static void main(String[] args) throws IOException {
        ArrayList<Item> items = new ArrayList<>();

        File file = new File(args[0]);
        //File file = new File("prices");

        Scanner in = new Scanner(System.in);


        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] pair = st.split(",");
            items.add(new Item(
                    pair[0],
                    Integer.parseInt(pair[1].replaceAll(" ",""))
            ));
        }

        int giftcard = Integer.parseInt(args[1]);
        //int giftcard = in.nextInt();

        int start = 0,end = items.size()-1;
        Pair pair = null;
        Pair bestpair = new Pair(items.get(start), items.get(start+1));
        boolean check = false;
        while (start<end){

            switch (comparePivots(items.get(start).price,items.get(end).price, giftcard)){
                case 0:{    //when gift card equals to pivot prices.
                    check=true;pair = new Pair(items.get(start), items.get(end));
                }break;
                case -1:{    //when gift card greater than pivot prices.
                    end--;
                }break;
                case 1:{   //when gift card less than pivot prices.
                    if(     (giftcard-items.get(start).price-items.get(end).price)
                            <=
                            (giftcard-bestpair.item1.price-bestpair.item2.price)){

                         pair = bestpair = new Pair(items.get(start), items.get(end));
                    }start++;
                }break;
            }
            if(check) {break;}
        }

        System.out.println(pair!=null ? pair : "Not Possible");

    }
    private static int comparePivots(int x, int y, int z){
        return Integer.compare(z - y - x, 0);
    }


}


