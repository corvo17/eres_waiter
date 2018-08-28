package com.eres.waiter.waiter.viewpager.helper;

public enum NotificationTypees
{
    Unknown,
    ERESStarted,// Система ERES стартовала !
    ERESFinished,// Система ERES завершает работу, верните смартфоны !
    OrderSendToKitchen, // заказ отправлен на кухню
    OrderChangedInWaiter, // официант изменил заказ и отправил на кухню
    OrderProblemsInKithen, // кухня предложила изменить заказ -- проблемы в кухне
    OrderRejectedInKithen, // кухня заказ отвергла
    OrderAcceptedInKitchen,// кухня приняла заказ на исполнение
    OrderSentToCashier, // счет отправлен на кассу
    OrderSuccessfullyClosed, // счет успешно закрыт
    OrderUnSuccessfullyClosed, // счет неуспешно закрыт
    CompleteKitchen,// кухня выполнила заказ
    OrderIsCanceled,// заказ отменен
    OrderIsClosed,// заказ закрыт
    OrderTransfer,// заказ передан другому официанту
    TableIsServiced, // Стол или часть стола взять на обслужывание
    TableIsNotServiced,// Стол необслужывается
    TableBooked,// Стол забронирован
    TableIsFree,// Стол свободен
    HallBooked,// Зал забронирован
    HallIsFree,// Зал свободен
    HallClosed,// Зал закрыт
    KitchenOpen,// кухня открылась
    KitchenClosed, // кухня закрылась
    MenuHasChanged, // меню изменился
    WaiterStartedWork,// официант начал работу
    WaiterFinishedWork,// официант закончил работу
    WaiterToManager,// официант вызывается к менеджеру
    WaiterToTable,// официант вызывается ко столу
    WaiterCheckPlease,// официант счет пожалуйста
    AttentionNeedsHelp, // внимание, требуется помощь
    AttentionManagerHere,// Внимание! Менеджер на борту!
    MenuChanged//Меню изменился
}
