package com.example.ootw.spinner

interface ClothesItemSpinnerObserver {
    fun subscribe(observable: ClothesItemSpinnerObservable)
    fun unsubscribe(observable: ClothesItemSpinnerObservable)
    fun notifyPosition(position: Int)
}