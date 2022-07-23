package com.example.ootw.spinner

interface ClothesCategorySpinnerObserver {
    fun subscribe(observable: ClothesCategorySpinnerObservable)
    fun unsubscribe(observable: ClothesCategorySpinnerObservable)
    fun notifyPosition(position: Int)
}