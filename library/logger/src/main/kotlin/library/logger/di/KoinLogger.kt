package library.logger.di

import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.logger.MESSAGE
import library.logger.infrastructure.logD
import library.logger.infrastructure.logE
import library.logger.infrastructure.logI

fun KoinApplication.logger(level: Level): KoinApplication = logger(KoinLogger(level))

private class KoinLogger(level: Level = Level.INFO) : org.koin.core.logger.Logger(level) {
    override fun log(level: Level, msg: MESSAGE) {
        if (isAt(level)) {
            when (level) {
                Level.DEBUG -> logD(msg)
                Level.INFO -> logI(msg)
                Level.ERROR -> logE(msg)
            }
        }
    }
}
