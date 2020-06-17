package library.architecture.domain

interface UseCase<in I, out O> {
    operator fun invoke(input: I): O
}

operator fun <O> UseCase<Unit, O>.invoke(): O = invoke(Unit)
