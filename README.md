## Тестирование калькулятора Yandex (тестовое задание)
Написать тесты для калькулятора Yandex на основе Selenium(PageObject), которые будут проверять следующие операции:  
- sqrt(144) = 12  
- cos(Pi/2) = 0  
- 1,5 * 100 = 150  

Калькулятор появляется после того как на странице http://www.yandex.ru/ в строку поиска ввести «калькулятор» и нажать «Найти».  
Поддержка кроссбраузерного запуска тестов не нужна, достаточно использовать ChromeDriver (или для другого браузера, на выбор).  

Нужно использовать:  
- TestNG / JUnit;  
- hamcrest;  
- паттерн PageObject;  

Для ознакомления (если останется время):  
- Allure  
- HTMLElements  

## Результаты
Использован TestNG, hamcrest, PageObject, HTMLElements, Allure.

_Скриншот отчета_  
![allure_report](https://github.com/pavelvic/ya-calc-ui-tests/blob/HTMLElements/_screenshots/allure_report.jpg)  

_Видео работы программы_  
[![Watch the video](https://img.youtube.com/vi/v9xNas3BdW8/0.jpg)](https://youtu.be/v9xNas3BdW8)  

_Затрачено времени_:  
- основное задание - 13.5 часов  
- доп. (Allure + HTMLElements) - 7 часов
