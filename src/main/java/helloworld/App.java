package helloworld;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Object, GatewayResponse> {

    public GatewayResponse handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Rss rss = (Rss) jaxbUnmarshaller.unmarshal(new URL("http://rss.allocine.fr/ac/cine/cettesemaine?format=xml"));
            Films films = new Films();
            films.setFilms(new ArrayList<>());
            int l = 10;
            for(Item i : rss.getChannel().getItems()){
                if(l-- <= 0){
                    break;
                }
                Film f = new Film();
                f.setTitre(i.getTitle());
                f.setCategorie(i.getDescription().substring(3, i.getDescription().indexOf(" ")));
                f.setDuree(i.getDescription().substring(i.getDescription().indexOf("(") + 1, i.getDescription().indexOf(")")));
                f.setDescription("<p>" + i.getDescription().substring(i.getDescription().indexOf(")") + 4).replace("<p></p>", ""));
                films.getFilms().add(f);
            }
            return new GatewayResponse(genson.serialize(films), headers, 200);
        } catch (Exception e) {
            return new GatewayResponse("{error: "+e.getMessage()+"}", headers, 500);
        }
    }
}
