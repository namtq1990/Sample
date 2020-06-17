package library.coroutines.domain

interface CoUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}

suspend operator fun <O> CoUseCase<Unit, O>.invoke(): O = invoke(Unit)
