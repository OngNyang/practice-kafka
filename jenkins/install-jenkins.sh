# 커스텀 이미지 빌드
docker build -t my-jenkins .

sleep 10  # (옵션: 약간의 대기 시간)

# 커스텀 이미지로 실행
docker run -d \
  --name jenkins \
  -p 9090:8080 \
  -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  my-jenkins   # ✅ 이 부분이 'jenkins/jenkins:lts'가 아니라 'my-jenkins'여야 함

