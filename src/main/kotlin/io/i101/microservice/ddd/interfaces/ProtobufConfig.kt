package io.i101.microservice.ddd.interfaces

import kotlinx.serialization.protobuf.ProtoBuf
import org.reactivestreams.Publisher
import org.springframework.boot.web.codec.CodecCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ResolvableType
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.http.MediaType
import org.springframework.http.codec.HttpMessageDecoder
import org.springframework.http.codec.HttpMessageEncoder
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.util.MimeType
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Configuration
class ProtobufConfig {
    @Bean
    fun protobufCodecCustomizer(): CodecCustomizer {
        return CodecCustomizer {
            it.customCodecs().decoder(ProtobufDecoder<Any>())
            it.customCodecs().encoder(ProtobufEncoder())
        }
    }
}

class ProtobufDecoder<T>: HttpMessageDecoder<T> {
    private val mimeType = MimeType("application", "x-protobuf")

    override fun canDecode(elementType: ResolvableType?, mimeType: MimeType?): Boolean {
        return this.mimeType == mimeType
    }

    override fun decode(inputStream: Publisher<DataBuffer>?, elementType: ResolvableType?, mimeType: MimeType?, hints: MutableMap<String, Any>?): Flux<T> {
        TODO("not implemented")
    }

    override fun decodeToMono(inputStream: Publisher<DataBuffer>?, elementType: ResolvableType?, mimeType: MimeType?, hints: MutableMap<String, Any>?): Mono<T> {
        TODO("not implemented")
    }

    override fun getDecodableMimeTypes(): List<MimeType> {
        return listOf(mimeType)
    }

    override fun getDecodeHints(actualType: ResolvableType?, elementType: ResolvableType?, request: ServerHttpRequest?, response: ServerHttpResponse?): Map<String, Any> {
        return emptyMap()
    }
}

class ProtobufEncoder: HttpMessageEncoder<Any> {
    private val encodableMimeType = MimeType("application", "x-protobuf")
    private val streamingMimeType = MediaType("application", "stream+x-protobuf")

    override fun canEncode(elementType: ResolvableType?, mimeType: MimeType?): Boolean {
        return true
    }

    override fun encode(inputStream: Publisher<out Any>?, bufferFactory: DataBufferFactory?, elementType: ResolvableType?, mimeType: MimeType?, hints: MutableMap<String, Any>?): Flux<DataBuffer> {
        return Flux.from(inputStream).map {
            return@map bufferFactory?.wrap(ProtoBuf.dump(it))
        }
    }

    override fun getEncodableMimeTypes(): List<MimeType> {
        return listOf(encodableMimeType)
    }

    override fun getStreamingMediaTypes(): List<MediaType> {
        return listOf(streamingMimeType)
    }
}
