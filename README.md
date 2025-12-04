# Liferay demo server

## Docker
The docker setup creates the following services:

* mysql
* liferay

### Starting docker
1. Build the liferay docker image using `docker-compose build`, this prepares the image
2. Start the different docker containers using `docker-compose up`; note that the very first startup can take a while because liferay has to wait for the database to become available.
3. Using your browser go to http://localhost:8080/ and login with test@liferay.com and password ABCabc123. (you have to change the password after first login)
4. Reindex the search indexes (only needed after initial startup or the database changes)

When done you can stop everything using Ctrl+C or using the Docker Desktop App. The docker services are configured to restart automatically when you reboot unless they were stopped.

### Connecting to mysql from your host
Edit the compose.yaml and enable the ports section for the mysql service. This exposes the port 3306 to your host environment so you can use MySQL Workbench (or another db tool of your choice) to connect to the database. The username/password for the database is preconfigured in the compose.yaml file.

### Working with mailcatcher
Fake smtp is a tool that simulates an smtp server, you can access the mails that it intercepted by going to http://localhost:1080/

### Working with dev studio
Although hot deploy is not possible from dev studio to a docker container, you can continuously deploy your modules by running `gradlew deploy`, running that is possible from within dev studio.

To debug code, the docker container has been setup to allow a remote debugging session on port 8000. Create a configuration for 'remote java application' and run that after starting liferay, you should be able to set breakpoints in your own code as well as liferay's code.

### Reinitializing the database
To recreate mysql with clean db, stop the mysql container and remove the mysql volume so it is recreated when you restart the docker services.
`docker volume list`
`docker volume rm -f <name of volume>`

### When should you rebuild the liferay docker image?
You should rebuild the liferay docker image after changing files in the `configs/` folder, since these files are copied into the container at build time.
