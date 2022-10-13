import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> result = new ArrayList<String>();
    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    result.add(parameters[1]);
                }
                return parameters[1];
        }
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            String s1="";
            for(int i=0;i<result.size();i+=1){
                if(result.get(i).contains(parameters[1])){
                    s1+=result.get(i)+" ";
                }
            }
            return s1;
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
