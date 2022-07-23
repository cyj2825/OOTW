package com.example.ootw.spinner

interface SecondarySpinnerObserver {
    fun subscribe(observable: SecondarySpinnerObservable)
    fun unsubscribe(observable: SecondarySpinnerObservable)
    fun notifyPosition(position: Int)
}