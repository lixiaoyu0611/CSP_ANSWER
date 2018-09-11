import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    int m, n;
    String s = "", layer = "";
    boolean key;
    Map<String,String> Json = new HashMap<String,String>();

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        
        while(n-- > 0){
            s = sc.nextLine();
            s = s.replace(" ", "");
            handle();
        }
        
        while(m-- > 0){
            s = sc.nextLine();
            if(!Json.containsKey(s))
                System.out.println("NOTEXIST");
            else System.out.println(Json.get(s));
        }
        
        sc.close();
        return;
        
    }

    public void handle() {
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            switch (c){
                case '{' :
                    Json.put(layer, "ob");
                    key = true;
                    break;
                case '}' :
                    if(!layer.equals("")){
                        int j;
                        for(j = layer.length() -1; j >= 0; j--){
                            if(layer.charAt(j) == '.')
                                break;
                        }
                        if( j < 0 )
                            layer = "";
                        else
                            layer = layer.substring(0, j);
                    }
                    break;

                case '"' :
                    String temp = "";
                    for(i = i + 1; i < s.length(); i++){
                        if(s.charAt(i) == '\\'){
                            i++;
                            temp += s.charAt(i);
                        }else if(s.charAt(i) == '"')
                            break;
                        else
                            temp += s.charAt(i);
                    }
                    if(key){
                        if(!layer.equals(""))
                            layer += ".";
                        layer += temp;
                    }else{
                        Json.put(new String(layer), "STRING " + temp);
                        int j = layer.lastIndexOf(".");
                        if(j < 0)
                            layer = "";
                        else
                            layer = layer.substring(0,j);
                    }
                    // key 为true 表示键值为空，否则表示layer内容为键值     
                    break;

                case ':' :
                    key = false;
                    break;

                case ',' :
                    key = true;
                    break;
            }
        }
        return;
    }
}