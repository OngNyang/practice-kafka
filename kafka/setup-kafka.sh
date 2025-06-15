#!/bin/bash

function info() {
  echo -e "\033[1;32m[INFO] $1\033[0m"
}

# 1. docker-compose up
info "Docker Compose로 Kafka 클러스터를 시작합니다..."
docker-compose up -d

# 2. 대기 시간 (브로커 초기화 기다림)
info "Kafka 컨테이너가 완전히 기동될 때까지 기다립니다 (20초)..."
sleep 20

# 3. kafka1 컨테이너 자동 감지
KAFKA_CONTAINER_NAME=$(docker ps --format '{{.Names}}' | grep kafka1 | head -n 1)

if [ -z "$KAFKA_CONTAINER_NAME" ]; then
  echo "[ERROR] kafka1 컨테이너를 찾을 수 없습니다."
  exit 1
fi

info "Kafka CLI 실행 대상 컨테이너: $KAFKA_CONTAINER_NAME"

# 4. 토픽 생성
info "Kafka 토픽 'test-topic'을 생성합니다..."
docker exec "$KAFKA_CONTAINER_NAME" /opt/bitnami/kafka/bin/kafka-topics.sh \
  --create \
  --topic test-topic \
  --bootstrap-server localhost:9092,localhost:9093 \
  --partitions 3 \
  --replication-factor 2 || true

# 5. 토픽 목록 확인
info "Kafka 토픽 목록을 확인합니다..."
docker exec "$KAFKA_CONTAINER_NAME" /opt/bitnami/kafka/bin/kafka-topics.sh \
  --list \
  --bootstrap-server localhost:9092