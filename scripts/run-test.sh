#!/bin/bash
# Запуск теста для текущего файла
# Использование: ./run-test.sh path/to/TestFile.java [methodName]
export JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/java-21-openjdk}"
FILE="$1"
METHOD="$2"
if [ -z "$FILE" ]; then
  echo "Укажите путь к файлу теста"
  exit 1
fi
# src/test/java/work/part0223/DebDownloadTest.java -> work.part0223.DebDownloadTest
CLASS=$(echo "$FILE" | sed -n 's|.*src/test/java/||p' | sed 's|/|.|g' | sed 's|\.java$||')
if [ -z "$CLASS" ]; then
  echo "Не удалось определить класс из пути: $FILE"
  exit 1
fi
cd "$(dirname "$0")/.."
if [ -n "$METHOD" ]; then
  ./gradlew test --tests "${CLASS}.${METHOD}"
else
  ./gradlew test --tests "$CLASS"
fi
