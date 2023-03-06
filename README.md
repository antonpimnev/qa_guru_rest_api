<a name="logo"></a>
## Учебный проект по автоматизации тестирования (API)

### Веб сайт <a target="_blank" href="https://reqres.in/">reqres.in</a>

<p align="center">
<img title="reqres.in" src="images/screens/logo.png">
</p>

## Содержание:

- <a href="#tools">Технологии и инструменты</a>
- <a href="#cases">Список проверок, реализованных в тестах</a>
- <a href="#launch">Запуск тестов (сборка в Jenkins)</a>
- <a href="#allure">Allure-отчет</a>
- <a href="#allure-testops">Интеграция с Allure TestOps</a>
- <a href="#telegram">Уведомление в Telegram о результатах запуска тестов</a>

<a id="tools"></a>
## Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/Rest-Assured.png" width="50" height="50"  alt="Rest-assured"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

<a id="cases"></a>
## Список проверок, реализованных в автотестах

- [x] Создание пользователя
- [x] Получение инфо пользователя по id
- [x] Проверка обновления данных пользователя
- [x] Удаление пользователя
- [x] Проверка получения списка пользователей
- [x] Проверка что пользователь не найден

<a id="launch"></a>
## Запуск тестов

###  Локальный запуск :
1. Запуск с командной строки:
```bash
gradle clean test
```
> Для запуска тестов в несколько потоков необходимо добавить параметр <code>-Dthreads={Количество потоков}</code>
>
> Например: <code>gradle clean test -Dthreads=2</code>
2. Получение отчёта:
```bash
gradle allureServe
```

###  Удаленный запуск (в Jenkins):
1. Открыть <a target="_blank" href="https://jenkins.autotests.cloud/job/C16-antonpimnev-diplom-api/">проект</a>

![This is an image](/images/screens/api-Jenkins-main.png)

2. Нажать **Собрать сейчас**
3. Результат запуска сборки можно посмотреть в отчёте Allure

<a id="allure"></a>
## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/C16-antonpimnev-diplom-api/3/allure/">Allure report</a>

###  Главная

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/api-allureRep_dashboard.png">
</p>

###  Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/api-allureRep_TK.png">
</p>

###  Графики

<p align="center">
<img title="Allure Graphics" src="images/screens/api-allureRep-Graphs.png">
</p>

<a id="allure-testops"></a>
## <img src="images/logo/Allure_TO.svg" width="25" height="25"  alt="Allure"/></a> Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/1981/dashboards">Allure TestOps</a>
### Cписок всех тест кейсов
<p align="center">
<img title="Allure Graphics" src="images/screens/api-allureTO_Suites.png">
</p>

### Dashboard с результатами тестирования
<p align="center">
<img title="Allure Graphics" src="images/screens/api-allureTO_dashboard.png">
</p>

### Пример отчёта выполнения одного из автотестов
<p align="center">
<img title="Allure Graphics" src="images/screens/api-allureTO_TK.png">
</p>

<a id="telegram"></a>
## <img src="images/logo/Telegram.svg" width="25" height="25"  alt="Allure"/></a> Уведомление в Telegram о результатах запуска тестов

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/api-telegramNotif.png" >
</p>

[Вернуться к оглавлению ⬆](#logo)
