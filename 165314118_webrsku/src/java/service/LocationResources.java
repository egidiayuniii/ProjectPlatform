/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasien;

import com.google.gson.Gson;
import helper.LocationHelper;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pojos.Location;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("Location")
public class LocationResources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PasienService
     */
    public LocationResources() {
    }

    /**
     * Retrieves representation of an instance of pasien.PasienService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getLocation() {       
        LocationHelper helper = new LocationHelper();
        List<Location> list = helper.bacaSemuaLokasi();
        Gson gson = new Gson();
        return Response.status(200)        
        
                .entity(gson.toJson(list))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET,POST,HEAD,OPTIONS,PUT")
                .header("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers")
                .header("Access-Exposed-Headers",
                        "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header("Access-Support-Credentials",
                        "true")
                .header("Access-Control-Max-Age", "20")
                .header("Access-Preflight-Maxage", "20")
                .build();
    }
    
    @POST
    @Path("addLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewLocation(String data){
        Gson gson = new Gson();
        Location location = gson.fromJson(data, Location.class);
        LocationHelper helper = new LocationHelper();
        helper.addNewLocation(location.getLat(), 
                              location.getLng(), 
                              location.getName());
        
        return Response
                .status(200)
                .entity(location)
                .build();
    }

    /**
     * PUT method for updating or creating an instance of PasienService
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    
    
}
