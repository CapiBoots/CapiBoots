
package com.example.CapiBoots.controladores.streaming;


import com.example.CapiBoots.servicios.streaming.ifxStreamingSrvc;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
public class StreamingCtrl {
    @Autowired
    private ifxStreamingSrvc streamingService;

    @GetMapping(value = "/stream/{id}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String id, @RequestHeader(name = "Range", defaultValue = "bytes=0-50")String range) {
       log.info(range);
        return streamingService.getVideo(id);

    }

}

