/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;

/**
 *
 * @author marlon
 */
public class Service3 extends AbstractVerticle {

    public static String ADDRESS = "service3";
    
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(ADDRESS, event -> {
            String message = (String)event.body();
            event.reply(message + "-[passed-through-service-3]");
        });
    }
}
