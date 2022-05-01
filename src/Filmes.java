import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Filmes {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("apiKey")))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        String[] arrayMovies = parseMovies(json);
        String[] arrayAttributes = parseAttributes(arrayMovies);
        System.out.println(json);
        for (String title : getAttribute(arrayAttributes, "title")) {
            System.out.println(title);
        }
        for (String url : getAttribute(arrayAttributes, "image")) {
            System.out.println(url);
        }
        for (String rating : getAttribute(arrayAttributes, "imDbRating\"")) {
            System.out.println(rating);
        }
        for (String year : getAttribute(arrayAttributes, "year")) {
            System.out.println(year);
        }
    }

    public static String[] parseMovies(String json){
       return json.split("},");
    }

    public static String[] parseAttributes(String[] movies){
        String attribute = Arrays.toString(movies);
            return attribute.split(",");
        }

    public static List<String> getAttribute(String[] attributes, String find) {
        List<String> att = new ArrayList<>();
        for (String attribute : attributes) {
            if (attribute.contains(find)) {
                int i = attribute.indexOf(find);
                att.add(attribute.substring(i));
            }
        }
        return att;
    }
}
