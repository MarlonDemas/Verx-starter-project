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
public class Service1 extends AbstractVerticle {

    public static String ADDRESS = "service1";
    
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(ADDRESS, event -> event.reply("[reply-from-service1]"));
    }

}
