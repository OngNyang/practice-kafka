#!/bin/bash

TOPIC="test-topic"
BROKER="kafka1:9092"
MESSAGE="Hello Kafka - $(date)"

echo "[INFO] Kafka에 메시지를 전송합니다: '$MESSAGE'"

docker exec kafka1 /opt/bitnami/kafka/bin/kafka-console-producer.sh \
  --bootstrap-server "$BROKER" \
  --topic "$TOPIC" \
  --producer-property "acks=1" \
  --producer-property "compression.type=none" \
  <<< "$MESSAGE"

echo "[INFO] 전송 완료."
