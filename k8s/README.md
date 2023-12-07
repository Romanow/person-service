### Локальный запуск кластера k8s с использованием kind

```shell
# create local k8s cluster
$ kind create cluster --config kind.yml
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

# add helm repo
$ helm repo add romanow https://romanow.github.io/helm-charts/
$ helm repo update

$ echo "127.0.0.1    person-service.local" | sudo tee -a /etc/hosts

$ helm install postgres romanow/postgres --values=postgres/values.yaml
$ helm install person-service romanow/java-service --values=person-service/values.yaml

$ skaffold dev

$ curl http://person-service.local/manage/health
```
