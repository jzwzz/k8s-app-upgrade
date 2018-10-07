
cd ../build/libs/

echo "*$1.jar"

cp ../../k8s/Dockerfile .

docker build -t jzwzz/k8s-app-upgrade:$1 .
docker push jzwzz/k8s-app-upgrade:$1

