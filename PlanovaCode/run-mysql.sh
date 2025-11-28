
set -e

echo "Starting MySQL container..."
docker compose up -d

echo "Waiting for MySQL to be healthy..."
until docker inspect --format='{{json .State.Health.Status}}' planova-mysql | grep -q "healthy"; do
  echo "Waiting for health check..."
  sleep 3
done

echo "MySQL is healthy. Container is up."
echo "You can run the Spring Boot application with profile 'mysql'."
