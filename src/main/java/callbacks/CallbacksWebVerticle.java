/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 *
 * @author marlon
 */
public class CallbacksWebVerticle extends AbstractVerticle {
    
    private static final Logger LOG = LoggerFactory.getLogger(CallbacksWebVerticle.class);

    @Override
    public void start() throws Exception {
        LOG.info("Starting CallbacksWebVerticle");
        vertx.createHttpServer().requestHandler(new RequestHandler()).listen(8888);
    }

    private class RequestHandler implements Handler<HttpServerRequest> {

        @Override
        public void handle(HttpServerRequest request) {
            // Send a message to service 1.
            vertx.eventBus().send(Service1.ADDRESS, "message-to-service1", reply1 -> {
                String replyFromService1 = (String) reply1.result().body();
                
                // Send a message to service 2.
                vertx.eventBus().send(Service2.ADDRESS, "message-to-service2", reply2 -> {
                    String replyFromService2 = (String) reply2.result().body();
                    
                    //Send the result from both service calls to service 3.
                    String combinedResult = replyFromService1 + "-" + replyFromService2;
                    vertx.eventBus().send(Service3.ADDRESS, combinedResult, reply3 -> {
                        
                        // Send an http response with the result of service 3.
                        request.response().end("CallbackWebVerticle - response from Service 3: " + reply3.result().body());
                    });
                });
            });
        }
        
    }
}
