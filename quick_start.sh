#!/bin/bash
# Скрипт для быстрого запуска тестов

echo "Проверка установки JDK..."
if ! command -v javac &> /dev/null; then
    echo "❌ JDK не установлен. Установите его командой:"
    echo "   sudo apt update && sudo apt install openjdk-21-jdk"
    exit 1
fi

echo "✅ JDK установлен: $(javac -version 2>&1)"
echo ""
echo "Запуск тестов POMFlightsTests..."
echo ""

# Установка JAVA_HOME если не установлен
if [ -z "$JAVA_HOME" ]; then
    export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
fi

# Запуск всех тестов в классе
./gradlew test --tests "work.part07.POMFlightsTests" --no-daemon

echo ""
echo "Результаты тестов сохранены в:"
echo "  - HTML: build/reports/tests/test/index.html"
echo "  - Allure: build/allure-results/"
echo ""
echo "Для просмотра Allure отчета выполните:"
echo "  allure serve build/allure-results"

