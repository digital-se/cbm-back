deploy:
	docker-compose -f docker-compose.yml up -d -V --remove-orphans --build