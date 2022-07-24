package com.example.ootw.spinner

interface PrimarySpinnerObserver {
    fun subscribe(observable: PrimarySpinnerObservable)
    fun unsubscribe(observable: PrimarySpinnerObservable)
    fun notifyPosition(position: Int)
}