statusListener(OnConsoleStatusListener)

def appenderName = null
def jettyLogs = System.getProperty("jetty.logs")

logger("org.springframework.web", INFO, [appenderName])
logger("org.springframework.security", INFO, [appenderName])