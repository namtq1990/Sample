package library.architecture.data

interface DomainConverter<in E, out D> {
    fun toDomain(external: E): D
}

interface ExternalConverter<in D, out E> {
    fun toExternal(domain: D): E
}

interface Converter<D, E> : DomainConverter<E, D>,
    ExternalConverter<D, E>
