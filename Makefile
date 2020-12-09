FOLDER ?= e2e/scripts

################################################
### Stop all containers and prune all images ###
################################################
clean:
	docker stop $$(docker ps -q)
	docker rm $$(docker ps -a -q)
	docker rmi $$(docker images -q)

#######################################
### Build images and run containers ###
#######################################
up:
	docker-compose up

pull:
	docker-compose pull

start:
	make pull up
