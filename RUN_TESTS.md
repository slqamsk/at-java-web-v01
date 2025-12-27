# Инструкция по запуску тестов

## Проблема с Java

Если возникает ошибка `Toolchain installation does not provide the required capabilities: [JAVA_COMPILER]`, 
это означает, что установлен только JRE, а не JDK. Нужно установить JDK.

## Установка JDK (если нужно)

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

После установки проверьте:
```bash
javac -version
```

## Способы запуска тестов

### 1. Запуск всех тестов в классе POMFlightsTests

```bash
./gradlew test --tests "work.part07.POMFlightsTests"
```

### 2. Запуск конкретного теста

```bash
./gradlew test --tests "work.part07.POMFlightsTests.test01SuccessfulLogin"
```

### 3. Запуск всех тестов в пакете

```bash
./gradlew test --tests "work.part07.*"
```

### 4. Запуск всех тестов в проекте

```bash
./gradlew test
```

### 5. Запуск с выводом в консоль

```bash
./gradlew test --tests "work.part07.POMFlightsTests" --info
```

### 6. Запуск только упавших тестов (повторный запуск)

```bash
./gradlew test --tests "work.part07.POMFlightsTests" --rerun-tasks
```

## Просмотр результатов

После запуска тестов результаты будут в:
- **HTML отчет**: `build/reports/tests/test/index.html`
- **Allure отчет**: `build/allure-results/` (затем запустите `allure serve build/allure-results`)

## Запуск Allure отчета

```bash
# Если установлен Allure CLI
allure serve build/allure-results

# Или через Gradle
./gradlew allureReport
./gradlew allureServe
```

## Настройка Java для Gradle (если нужно)

Если Gradle не находит Java, можно указать явно:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
./gradlew test --tests "work.part07.POMFlightsTests"
```

Или добавить в `~/.bashrc`:
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

## Запуск через IDE (IntelliJ IDEA / VS Code)

1. Откройте файл `POMFlightsTests.java`
2. Нажмите правой кнопкой на класс или метод
3. Выберите "Run" или "Debug"

## Список тестов в POMFlightsTests

1. `test01SuccessfulLogin` - Успешный логин с корректными данными
2. `test02WrongPassword` - Неуспешный логин с неверным паролем
3. `test03EmptyFields` - Вход с пустыми полями
4. `test04LockedOutUser` - Вход заблокированного пользователя
5. `test05ValidSearch` - Поиск с валидными данными
6. `test06NoDate` - Поиск без указания даты
7. `test07FlightsNotFound` - Отображение сообщения "Рейсов не найдено"
8. `test08PassportValidation` - Валидация паспорта
9. `test09SuccessRegistration` - Успешная регистрация
10. `test10EnterKeyInForms` - Работа с клавишей Enter в формах

