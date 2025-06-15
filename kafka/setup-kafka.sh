#!/bin/bash

function info() {
  echo -e "\033[1;32m[INFO] $1\033[0m"
}

TOPIC_NAME="test-topic"

info "Docker Compose로 Kafka 클러스터를 시작합니다..."
docker-compose up -d

info "컨테이너 시작 후 안정화 대기 중 (30초)..."
sleep 30

info "토픽 생성 시도 중..."
docker exec kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh \
  --create \
  --topic "$TOPIC_NAME" \
  --bootstrap-server kafka1:19092,kafka2:19093 \
  --partitions 3 \
  --replication-factor 2 \
  --if-not-exists

info "토픽 목록:"
docker exec kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh \
  --list \
  --bootstrap-server kafka1:19092