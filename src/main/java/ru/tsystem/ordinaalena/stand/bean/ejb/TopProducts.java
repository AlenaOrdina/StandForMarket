package ru.tsystem.ordinaalena.stand.bean.ejb;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.log4j.Logger;
import ru.tsystem.ordinaalena.stand.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class TopProducts implements Serializable {

    private static final Logger logger=Logger.getLogger(TopProducts.class);

    private List<Product> tops=new ArrayList<>();

    public TopProducts() {
    }

    @PostConstruct
    public void init(){
        updateTopProducts();
        logger.info("Application was successfully started and has got current top products list");
    }

    public List<Product> getTops() {
        return tops;
    }

    public void setTops(List<Product> tops) {
        this.tops = tops;
    }

    public void updateTopProducts(){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8081/advertising/stand");

        logger.info("Connection was opened.");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        logger.info("Stand have got response without any errors.");


        List list = response.getEntity(ArrayList.class);
        try {
            tops = parseListOfProducts(list);
            logger.info("Stand application successfully has parsed list of top products and has set it as current.");
        } catch (Exception e) {
            logger.info("During the parsing process some critical situation was occurred.", e);
        }
    }

    private List<Product> parseListOfProducts(List<Map<String,String>> list){
        List<Product> tops=new ArrayList<>();
        for (Map<String,String> product:list){
            Long id=null;
            String title=null;
            String price=null;
            for (Map.Entry<String,String> attribute:product.entrySet()){
                if (attribute.getKey().equals("id")){
                    id=Long.parseLong(attribute.getValue());
                }
                if (attribute.getKey().equals("title")){
                    title=attribute.getValue();
                }
                if (attribute.getKey().equals("price")){
                    price=attribute.getValue();
                }

            }
            Product tmp=new Product(id,title,price);
            tops.add(tmp);}
            return tops;

        }
    }

