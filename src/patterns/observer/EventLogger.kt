package patterns.observer

class EventLogger : Observer {
    override fun onEvent(eventType: EventType, data: Any?) {
        val time = java.time.LocalTime.now()
        println("[$time] [$eventType]: $data")
    }
}