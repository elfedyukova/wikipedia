# Проект автоматизации мобильных приложений

Этот проект был создан в рамках курса "Автоматизатор мобильных приложений".

## О курсе

Курс "Автоматизатор мобильных приложений"
https://www.learnqa.ru/java

## Об этом проекте

В рамках этого проекта будут созданы автотесты для https://ru.wikipedia.org/wiki/

* Android
* IOS
* Mobile Web

## Технологии

* Java - основной язык программирования
* Selenium - инструмент для автоматизации тестирования веб приложений
* Appium - инструмент для автоматизации тестирования мобильных приложений

## Дополнительная информация

### Работа с эмулятором

- Установка Android Studio
- Установка эмулятора
- Запуск эмулятора из командной строки

````
emulator @Nexus5
````

- Установка apk приложения на телефон
- Получение списка пакетов всех приложений установленных на устройстве

````
shell pm list packages
````

##### Пример:

````
$ adb shell pm list packages | grep "wiki"
````

##### Вывод:

````
package:org.wikipedia
````

- Получение активности на открытом экране приложения

````
adb shell dumpsys window windows >> Desktop\activity.txt
````

- org.wikipedia.page.PageActivity , org.wikipedia.main.MainActivity
- Appium Inspector. Подключиться к эмулятору задав нужные капабили

### Подготовка окружения

- Проверка установки Node.js и npm(node package manager)

````
node -v
npm -v
````

- Appium 2.0 или новее (консольная утилита без графического интерфейса):

1. Установка appium

````
npm install -g appium@next
````

2. Проверка версии

````
appium --version
````

3. Установка драйверов для Android и iOS

````
appium driver install uiautomator2
appium driver install xcuitest
````

4. Запуск appium

````
appium
````

5. Установка Appium Inspector. Для установки необходимо перейти по
   ссылке https://github.com/appium/appium-inspector/releases и выбрать из списка версию для установки. 

