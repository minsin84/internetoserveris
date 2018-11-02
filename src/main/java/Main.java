import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Labas");
        Spark.port(82);
        Spark.get("/home", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                URI path = Main.class.getClassLoader().getResource("home.html").toURI();
                String html = new String(Files.readAllBytes(Paths.get(path)), Charset.forName("UTF-8"));
                return html;
            }
        });
        Spark.post("/post", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String param = request.queryParams("vardas");
                return "Hello: " + param;
            }
        });


    }
}
