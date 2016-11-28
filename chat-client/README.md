#### [Задание](https://github.com/sbt-java-school/artem-shaburov/tree/master/chat-core#Задание)

Основной интерфейс клиентской части - интерфейс Messenger. 
И его функциональная часть, реализация MessengerClient.
Для упрощения работы со стримами и сокетами, 
и клиент и сервер содержат HttpService - класс, 
реализующий пару методов для получения и посылки информации.
Отличительные черты клиента, а точнее интерфейса Messenger, 
это проверка был ли законнекчен юзер и если мы забыли вызвать коннект, 
то бросается ConnectionException.
Также, если отправить сообщение юзеру, которого нет на сервере, 
обратно вернется сообщение, что его нет и будет брошено исключение UserNotFoundException.