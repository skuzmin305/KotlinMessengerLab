package patterns.observer

open class Observable {
    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) { //добавить наблюдателя
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) { // удалить наблюдателя
        observers.remove(observer)
    }

    fun notifyObservers(eventType: EventType, data: Any?) { //уведомить всех наблюдателей
        for (observer in observers) {
            observer.onEvent(eventType, data)
        }
    }
}