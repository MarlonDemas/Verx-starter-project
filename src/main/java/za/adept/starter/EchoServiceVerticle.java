/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.adept.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.Logger;

/**
 *
 * @author marlon
 */
public class EchoServiceVerticle extends AbstractVerticle {

    public static final String ADDRESS = "echo-service";
    private static final Logger LOG = LoggerFactory.getLogger(EchoServiceVerticle.class);

    @Override
    public void start() throws Exception {
        LOG.info("Starting echo-service");
        
        vertx.eventBus().consumer(ADDRESS, message -> {
            JsonObject messageBody = (JsonObject)message.body();
            LOG.info("Recieved message", messageBody);
            
            messageBody.put("passedThrough", "echo-service");
            LOG.info("Sending reply: " + messageBody);
            
            message.reply(messageBody);
        });
    }
    
}
