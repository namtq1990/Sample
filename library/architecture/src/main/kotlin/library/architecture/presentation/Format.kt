package library.architecture.presentation

interface Parser<in E, out D> {
    fun parse(external: E): D
}

interface Formatter<in D, out E> {
    fun format(domain: D): E
}

interface Format<D, E> : Parser<E, D>, Formatter<D, E>
