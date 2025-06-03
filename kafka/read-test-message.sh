#!/bin/bash

TOPIC="test-topic"
BROKER="kafka1:9092"

echo "[INFO] Kafka에서 메시지를 읽어옵니다 (from-beginning)..."

docker exec kafka1 /opt/bitnami/kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server "$BROKER" \
  --topic "$TOPIC" \
  --from-beginning \
  --timeout-ms 3000
