/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.adept.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 *
 * @author marlon
 */
public class WebVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(WebVerticle.class);

    @Override
    public void start() throws Exception {
        LOG.info("Starting webVerticle");
        
        vertx.createHttpServer().requestHandler(request -> {
            JsonObject message = new JsonObject()
                    .put("text", "Hello World!")
                    .put("queryString", request.query());
            
            LOG.info("Sending message: "+message);
            vertx.eventBus().send(EchoServiceVerticle.ADDRESS, message, reply -> {
                
                JsonObject replyBody = (JsonObject) reply.result().body();
                LOG.info("Received reply: " + replyBody);
                
                request.response().end(replyBody.encodePrettily());
            });
        }).listen(8888);
    }
    
}
