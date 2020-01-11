package com.example.reactives3demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author DAI Yamasaki
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inbox")
public class UploadResource {
    private final S3AsyncClient s3client;
    private final S3ClientConfigurarionProperties properties;

    @PostMapping
    public Mono<ResponseEntity<UploadResult>> uploadHandler(
            @RequestHeader HttpHeaders headers,
            @RequestBody Flux<ByteBuffer> body) {

        long length = headers.getContentLength();

        String fileKey = UUID.randomUUID().toString();
        Map<String, String> metadata = new HashMap<String, String>();
        MediaType mediaType = headers.getContentType();

        if (mediaType == null) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        CompletableFuture future = s3client
                .putObject(PutObjectRequest.builder()
                                .bucket(properties.getBucket())
                                .contentLength(length)
                                .key(fileKey.toString())
                                .contentType(mediaType.toString())
                                .metadata(metadata)
                                .build(),
                        AsyncRequestBody.fromPublisher(body));

        return Mono.fromFuture(future)
                .map((response) -> {
//                    checkResult(response);
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(new UploadResult(HttpStatus.CREATED, new String[] {fileKey}));
                });
    }

    @RequestMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = {RequestMethod.POST, RequestMethod.PUT})
    public Mono<ResponseEntity<String>> multipartUploadHandler(@RequestHeader HttpHeaders headers, @RequestBody Flux<Part> parts  ) {

        return parts
                .ofType(FilePart.class) // We'll ignore other data for now
//                .flatMap((part) -> saveFile(headers, s3config.getBucket(), part))
                .collect(Collectors.toList())
                .map((keys) -> ResponseEntity.status(HttpStatus.CREATED)
                        .body("hoge"));
    }
//    @RequestMapping(
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            method = {RequestMethod.POST, RequestMethod.PUT}
//    )
//    public Mono<ResponseEntity<UploadResult>> multipartUploadHandler(
//            @RequestHeader HttpHeaders headers,
//            @RequestBody Flux<ByteBuffer> body) {
//
//    }

}
