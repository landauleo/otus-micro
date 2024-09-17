###Задание:
Prometheus. Grafana
###Описание/Пошаговая инструкция выполнения домашнего задания:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max 
2. RPS 
3. Error Rate - количество 500ых ответов

Добавить в дашборд графики с метрикам в целом по сервису, взятые с nginx-ingress-controller:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
2. RPS
3. Error Rate - количество 500ых ответов

Настроить алертинг в графане на Error Rate и Latency

На выходе должно быть:
1. скриншоты дашборды с графиками в момент стресс-тестирования сервиса. Например, после 5-10 минут нагрузки 
2. json-дашборды

###Used resources:
Documentation micronaut: https://micronaut-projects.github.io/micronaut-micrometer/3.2.0/guide/index.html#metricsAndReportersPrometheus

http://arch.homework/prometheus

http://arch.homework/metrics

###Used commands:
```
./gradlew clean build (в корне проекта)

docker build --platform linux/amd64 -t ll-o-m .

docker tag ll-o-m myteayourmilk/ll-o-m:test

docker push myteayourmilk/ll-o-m:test
```

Добавляет новый репозиторий (хранилище, где находятся пакеты Helm (чарты), которые можно устанавливать в k8s) Helm 
чарта к локальному Helm клиенту:

```
cd  prometheus
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
```
Обновляет локальный кэш репозиториев Helm:
```
helm repo update
```

Kube-prometheus-stack — это сложный чарт, который устанавливает полный набор инструментов для мониторинга в 
Kubernetes, включая:

Prometheus для сбора и хранения метрик.

Alertmanager для управления алертами.

Grafana для визуализации метрик

```
helm install my-postgresql --values values.yml bitnami/postgresql

helm install stack --values prometheus.yml prometheus-community/kube-prometheus-stack
```

```
cd kubernetes
kubectl apply -f ./
```

Проверяем:
```
kubectl get all
```

добраться до Prometheus:
```
kubectl port-forward service/prometheus-operated  9090
http://localhost:9090
```
Далее на UI Status -> Targets
Смотрим, что Prometheus работает

Возвращаемся обратно в Home, тыкаем произвольные метрики типа 
```
http_server_requests_seconds_max
http_server_requests_seconds_count{uri=~"/user/.*"}
rate(http_server_requests_seconds_count{uri=~"/user/.*"}[1m])
```

добраться до Grafana:
```
kubectl port-forward service/stack-grafana  9000:80
http://localhost:9000
```
username: admin
password: prom-operator

когда хочешь начать с чистого листа:
```
helm uninstall stack
kubectl delete all --all
kubectl delete pvc --all
kubectl delete secret --all
kubectl delete configmap --all
```