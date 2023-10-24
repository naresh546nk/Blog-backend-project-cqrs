mvn clean install
echo "Building docker image .."
docker build -t naresh546/blogsite-eurekaserver:latest .
echo "pushing to docker hub"
docker push naresh546/blogsite-eurekaserver:latest
