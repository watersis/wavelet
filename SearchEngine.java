import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
class Handler1 implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> s1 = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    s1.add(parameters[1]);
                    return parameters[1];
                }
        }
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            for(int i=0;i<s1.size();i+=1){
                if(s1.get(i).contains(parameters[1])){
                    result.add(s1.get(i));
                }
            }
            return String.format(s1);
        }
        else{
            return "404 Not Found!";
        }                 
        
            

        
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
