###Задание:
Написать манифесты для k8s
###Описание/Пошаговая инструкция выполнения домашнего задания:
1. Написать манифесты для деплоя в k8s для этого сервиса
2. Манифесты должны описывать сущности: Deployment, Service, Ingress. 
3. В Deployment могут быть указаны Liveness, Readiness пробы.
4. Количество реплик должно быть не меньше 2. Image контейнера должен быть указан с Dockerhub. 
5. Хост в ингрессе должен быть arch.homework. В итоге после применения манифестов GET запрос на http://arch.homework/health должен отдавать {“status”: “OK”}. 
6. На выходе предоставить 
- ссылку на github c манифестами. Манифесты должны лежать в одной директории, так чтобы можно было их все применить одной командой kubectl apply -f . 
- url, по которому можно будет получить ответ от сервиса (либо тест в postmanе).

Обратите внимание, что при сборке на m1 при запуске вашего контейнера на стандартных платформах будет ошибка такого вида:
standard_init_linux.go:228: exec user process caused: exec format error
Для сборки рекомендую указать тип платформы linux/amd64:
docker build --platform linux/amd64 -t tag

Более подробно можно прочитать в статье: https://programmerah.com/how-to-solve-docker-run-error-standard_init_linux-go219-exec-user-process-caused-exec-format-error-39221/
***
####Used commands:
- в DockerDesktop -> enable Kubernetes
- проверяем кластер -> kubectl config get-contexts
- brew install helm
- использование nginx ingress controller:

    
    kubectl create namespace m && helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && helm repo update && helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml

- проверяем создание namespaces -> kubectl get namespaces
- заходим в директорию с манифестами -> kubectl apply -f .
- дебажим и плачем kubectl logs <pod_name>