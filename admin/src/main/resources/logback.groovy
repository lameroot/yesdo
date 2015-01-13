statusListener(OnConsoleStatusListener)

def appenderName = null
def jettyLogs = System.getProperty("jetty.logs")

if (jettyLogs != null && !jettyLogs.empty) {
	appenderName = 'ROLLING'
	appender("ROLLING", RollingFileAppender) {
		file  = "${jettyLogs}console-log-file.log"
		encoder(PatternLayoutEncoder) {
			Pattern = "%d{HH:mm:ss} %-4relative [%thread] %-5level %logger{35} - %msg%n"
		}
		rollingPolicy(TimeBasedRollingPolicy) {
			FileNamePattern = "${jettyLogs}console-log-file.%d{yyyy-MM-dd}.gz"
			maxHistory = 30
		}
	}
} else {
	appenderName = 'STDOUT'
	appender('STDOUT', ConsoleAppender) {
		encoder(PatternLayoutEncoder) {
			pattern = "%d{HH:mm:ss} %-4relative [%thread] %-5level %logger{35} - %msg%n"
		}
	}
}

println "Setting appender ${appenderName}"

logger("org.springframework.web", INFO, [appenderName])
logger("org.springframework.security", INFO, [appenderName])
//logger("org.jooq.tools", DEBUG, [appenderName])
//logger("ru.rbs.admin.web.controller", DEBUG, [appenderName])
//logger("ru.rbs.admin.web.polling.PollingRequestMappingHandlerMapping", ERROR, [appenderName])
//logger("ru.rbs.admin.config", DEBUG, [appenderName])
//logger("ru.rbs.admin.repository", DEBUG, [appenderName])
//logger("ru.rbs.admin.security", DEBUG, [appenderName])
//logger("ru.rbs.admin.service", DEBUG, [appenderName])

//root(DEBUG, [appenderName])