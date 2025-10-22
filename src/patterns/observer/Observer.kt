package patterns.observer

interface Observer {
    fun onEvent(eventType: EventType, data: Any?)
}