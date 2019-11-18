/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 *
 * @author marlon
 */
public class CallbacksVertxStarter extends AbstractVerticle {
    
    public static void main(String... args) {
        
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Service1());
        vertx.deployVerticle(new Service2());
        vertx.deployVerticle(new Service3());
        vertx.deployVerticle(new CallbacksWebVerticle());
    }
}
