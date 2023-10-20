mvn clean install
echo "login to docker hub "
docker login
echo "Building docker image .."
docker build -t naresh546/blogsite-blog-replica:latest .
echo "pushing to docker hub"
docker push naresh546/blogsite-blog-replica:latest




