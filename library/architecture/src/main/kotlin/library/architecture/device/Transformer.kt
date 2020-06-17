package library.architecture.device

interface DomainTransformer<in E, out D> {
    fun toDomain(external: E): D
}

interface ExternalTransformer<in D, out E> {
    fun toExternal(domain: D): E
}

interface Transformer<D, E> : DomainTransformer<E, D>, ExternalTransformer<D, E>
