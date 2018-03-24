import ch.qos.logback.core.util.FileSize

def logPattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def USER_HOME = System.getProperty('user.home')
def BASE_PATH = "${USER_HOME}/logs/music_boom"
println(USER_HOME)

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

appender("FILE", RollingFileAppender) {
    file = "${BASE_PATH}/mb.log"
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "${BASE_PATH}/mb_%i.log"
        minIndex = 1
        maxIndex = 10
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = FileSize.valueOf("5MB")
    }

}

root(DEBUG, ["FILE", "CONSOLE"])