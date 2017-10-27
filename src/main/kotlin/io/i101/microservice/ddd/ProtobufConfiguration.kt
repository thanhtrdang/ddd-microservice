package io.i101.microservice.ddd

import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
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
class ProtobufConfiguration {
    @Bean
    fun protobufCodecCustomizer(): CodecCustomizer {
        return CodecCustomizer {
            it.customCodecs().decoder(ProtobufHttpMessageDecoder())
            it.customCodecs().encoder(ProtobufHttpMessageEncoder())
        }
    }
}

val protobufMimeType = MediaType("application", "x-protobuf")
val protobufMediaType = MediaType("application", "stream+x-protobuf")

class ProtobufHttpMessageDecoder: HttpMessageDecoder<Any> {
    override fun getDecodableMimeTypes(): List<MimeType> {
        return listOf(protobufMimeType)
    }

    override fun canDecode(elementType: ResolvableType, mimeType: MimeType?): Boolean {
        return protobufMimeType == mimeType
    }

    override fun getDecodeHints(actualType: ResolvableType, elementType: ResolvableType, request: ServerHttpRequest, response: ServerHttpResponse): Map<String, Any> {
        return emptyMap()
    }

    override fun decode(inputStream: Publisher<DataBuffer>, elementType: ResolvableType, mimeType: MimeType?, hints: MutableMap<String, Any>?): Flux<Any> {
        return Flux.empty()
    }

    override fun decodeToMono(inputStream: Publisher<DataBuffer>, elementType: ResolvableType, mimeType: MimeType?, hints: MutableMap<String, Any>?): Mono<Any> {
        return Mono.empty()
    }
}

class ProtobufHttpMessageEncoder: HttpMessageEncoder<Any> {
    override fun getStreamingMediaTypes(): List<MediaType> {
        return listOf(protobufMediaType)
    }

    override fun getEncodableMimeTypes(): List<MimeType> {
        return listOf(protobufMimeType)
    }

    override fun canEncode(elementType: ResolvableType, mimeType: MimeType?): Boolean {
        return protobufMimeType == mimeType || protobufMediaType == mimeType
    }

    //TODO - AbstractJackson2Encoder.encode - Support stream later
    override fun encode(inputStream: Publisher<out Any>, bufferFactory: DataBufferFactory, elementType: ResolvableType, mimeType: MimeType?, hints: MutableMap<String, Any>?): Flux<DataBuffer> {
        return Flux.from(inputStream).map {
            val encoder = it as ProtobufEncoder<*, *>
            return@map bufferFactory.wrap(encoder.encode())
        }
    }
}

class ProtobufDecoder<out M : Message<*, *>>(private val data: ByteArray, private val clazz: Class<M>) {
    fun decode(): M = ProtoAdapter.get(clazz).decode(data)
}

abstract class ProtobufEncoder<in E, out M : Message<*, *>> protected constructor(entity: E) {
    private val message: Message<*, *>

    init {
        this.message = encode(entity)
    }

    fun encode(): ByteArray = message.encode()

    protected abstract fun encode(entity: E): M
}