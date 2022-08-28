# Drone Tracker

### Assumptions

* Loggers were made `static final` with intent that they are class variables and consume lesser computer resources,
  because it cost a single variable reference for all instances of the class
* MySQL used as SQL dialect for **h2** `schema.sql` and `data.sql`
* We don't upload images as blob data type to database.
* Medication images were stored in the server as jpg files, and we store only relative path to image.
* I have treated Medicine as item rather than item in a inventory, thus making drone and medicine relationship many to many
* But I have only consider the one to many implementation from drone to medication, because of no requirement
* I only consider IDLE and LOADING as Medicine Loadable status

### Instructions

* Please use import `Musala Soft.postman_collection.json` collection for Postman testing.
* Battery Status checking Scheduler works within 1 minute frame

### Swagger

* http://localhost:8080/drone-tracker/swagger-ui/index.html

### Actuator

* http://localhost:8080/drone-tracker/actuator